package com.dcdevelopers.cybersaathi.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.dcdevelopers.cybersaathi.data.models.UserProgress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.TimeUnit

class ProgressRepository(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("cybersaathi_prefs", Context.MODE_PRIVATE)

    private val _userProgress = MutableStateFlow(loadProgress())
    val userProgress: StateFlow<UserProgress> = _userProgress.asStateFlow()

    init {
        checkAndUpdateStreak()
    }

    private fun loadProgress(): UserProgress {
        val name = prefs.getString("display_name", "Learner") ?: "Learner"
        val xp = prefs.getInt("xp", 0)
        val level = calculateLevel(xp)
        val streak = prefs.getInt("streak", 1)
        val lastEpochDay = prefs.getLong("last_active_epoch_day", 0L)
        val lessons = prefs.getStringSet("completed_lessons", emptySet()) ?: emptySet()
        val labs = prefs.getStringSet("completed_labs", emptySet()) ?: emptySet()
        val badges = prefs.getStringSet("earned_badges", emptySet()) ?: emptySet()
        val theme = prefs.getString("theme_pref", "CYBER_DARK") ?: "CYBER_DARK"
        val isFirstLaunch = prefs.getBoolean("is_first_launch", true)
        val lastOpened = prefs.getString("last_opened_lesson", null)

        // Load quiz scores
        val quizScoresMap = mutableMapOf<String, Int>()
        prefs.all.forEach { (key, value) ->
            if (key.startsWith("quiz_score_") && value is Int) {
                val quizId = key.removePrefix("quiz_score_")
                quizScoresMap[quizId] = value
            }
        }

        return UserProgress(
            displayName = name,
            xp = xp,
            level = level,
            streak = streak,
            lastActiveEpochDay = lastEpochDay,
            completedLessons = lessons,
            completedLabs = labs,
            quizScores = quizScoresMap,
            earnedBadges = badges,
            themePreference = theme,
            isFirstLaunch = isFirstLaunch,
            lastOpenedLessonId = lastOpened
        )
    }

    fun calculateLevel(xp: Int): Int {
        return 1 + (xp / 100)
    }

    private fun getCurrentEpochDay(): Long {
        return TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis())
    }

    private fun checkAndUpdateStreak() {
        val today = getCurrentEpochDay()
        val current = _userProgress.value
        val lastDay = current.lastActiveEpochDay

        if (lastDay == 0L) {
            // First time tracking
            prefs.edit()
                .putLong("last_active_epoch_day", today)
                .putInt("streak", 1)
                .apply()
            updateState()
            return
        }

        val diff = today - lastDay
        if (diff == 1L) {
            // Consecutive day
            val newStreak = current.streak + 1
            prefs.edit()
                .putLong("last_active_epoch_day", today)
                .putInt("streak", newStreak)
                .apply()
            updateState()
        } else if (diff > 1L) {
            // Streak broken, reset to 1
            prefs.edit()
                .putLong("last_active_epoch_day", today)
                .putInt("streak", 1)
                .apply()
            updateState()
        } else if (diff == 0L) {
            // Same day activity, keep current streak
        }
    }

    private fun updateState() {
        _userProgress.value = loadProgress()
    }

    fun updateDisplayName(newName: String) {
        val cleanName = newName.trim().ifEmpty { "Learner" }
        prefs.edit().putString("display_name", cleanName).apply()
        updateState()
    }

    fun setFirstLaunchCompleted() {
        prefs.edit().putBoolean("is_first_launch", false).apply()
        updateState()
    }

    fun updateThemePreference(theme: String) {
        prefs.edit().putString("theme_pref", theme).apply()
        updateState()
    }

    fun setLastOpenedLesson(lessonId: String) {
        prefs.edit().putString("last_opened_lesson", lessonId).apply()
        updateState()
    }

    fun completeLesson(lessonId: String, xpReward: Int = 30): Boolean {
        val current = _userProgress.value
        if (current.completedLessons.contains(lessonId)) {
            // Already completed, don't duplicate XP
            setLastOpenedLesson(lessonId)
            return false
        }

        val newLessons = current.completedLessons + lessonId
        val newXp = current.xp + xpReward

        prefs.edit()
            .putStringSet("completed_lessons", newLessons)
            .putInt("xp", newXp)
            .putString("last_opened_lesson", lessonId)
            .apply()

        updateState()
        checkAndUnlockBadges()
        return true
    }

    fun recordQuizResult(quizId: String, scorePercentage: Int, xpReward: Int = 50): Boolean {
        val current = _userProgress.value
        val previousBest = current.quizScores[quizId] ?: -1

        var isNewXpAwarded = false
        val editor = prefs.edit()

        if (scorePercentage > previousBest) {
            editor.putInt("quiz_score_$quizId", scorePercentage)
        }

        // Award XP only on the first pass (>= 70%)
        if (scorePercentage >= 70 && previousBest < 70) {
            val newXp = current.xp + xpReward
            editor.putInt("xp", newXp)
            isNewXpAwarded = true
        }

        editor.apply()
        updateState()
        checkAndUnlockBadges()
        return isNewXpAwarded
    }

    fun completeLab(labId: String, xpReward: Int = 75): Boolean {
        val current = _userProgress.value
        if (current.completedLabs.contains(labId)) {
            return false
        }

        val newLabs = current.completedLabs + labId
        val newXp = current.xp + xpReward

        prefs.edit()
            .putStringSet("completed_labs", newLabs)
            .putInt("xp", newXp)
            .apply()

        updateState()
        checkAndUnlockBadges()
        return true
    }

    private fun checkAndUnlockBadges() {
        val current = _userProgress.value
        val newBadges = current.earnedBadges.toMutableSet()
        var bonusXp = 0

        // Badge 1: First Steps (1 lesson completed)
        if (current.completedLessons.isNotEmpty() && !newBadges.contains("first_steps")) {
            newBadges.add("first_steps")
            bonusXp += 30
        }

        // Badge 2: Phishing Spotter (phishing lab or quiz completed)
        if ((current.completedLabs.contains("lab_phishing") || (current.quizScores["quiz_phishing_101"] ?: 0) >= 70) && !newBadges.contains("phishing_spotter")) {
            newBadges.add("phishing_spotter")
            bonusXp += 50
        }

        // Badge 3: Password Vault Guardian
        if ((current.completedLabs.contains("lab_password") || (current.quizScores["quiz_passwords_101"] ?: 0) >= 70) && !newBadges.contains("password_guardian")) {
            newBadges.add("password_guardian")
            bonusXp += 50
        }

        // Badge 4: Privacy Sentinel
        if (current.completedLessons.contains("privacy_101") && !newBadges.contains("privacy_sentinel")) {
            newBadges.add("privacy_sentinel")
            bonusXp += 50
        }

        // Badge 5: Lab Master (3+ labs completed)
        if (current.completedLabs.size >= 3 && !newBadges.contains("lab_master")) {
            newBadges.add("lab_master")
            bonusXp += 100
        }

        // Badge 6: CyberSaathi Champion (5+ lessons completed)
        if (current.completedLessons.size >= 5 && !newBadges.contains("cyber_champion")) {
            newBadges.add("cyber_champion")
            bonusXp += 150
        }

        if (newBadges.size > current.earnedBadges.size) {
            val totalXp = current.xp + bonusXp
            prefs.edit()
                .putStringSet("earned_badges", newBadges)
                .putInt("xp", totalXp)
                .apply()
            updateState()
        }
    }

    fun resetProgress() {
        prefs.edit().clear().apply()
        updateState()
    }
}
