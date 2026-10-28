package com.dcdevelopers.cybersaathi.data.models

data class UserProgress(
    val displayName: String = "Learner",
    val xp: Int = 0,
    val level: Int = 1,
    val streak: Int = 1,
    val lastActiveEpochDay: Long = 0L,
    val completedLessons: Set<String> = emptySet(),
    val completedLabs: Set<String> = emptySet(),
    val quizScores: Map<String, Int> = emptyMap(), // quizId -> score percentage
    val earnedBadges: Set<String> = emptySet(),
    val themePreference: String = "CYBER_DARK", // CYBER_DARK, OLED_BLACK, LIGHT
    val isFirstLaunch: Boolean = true,
    val lastOpenedLessonId: String? = null
)

data class LessonSection(
    val title: String,
    val bodyText: String,
    val warningTip: String? = null,
    val practicalExample: String? = null
)

data class Lesson(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: String,
    val durationMinutes: Int,
    val difficulty: String,
    val iconName: String,
    val sections: List<LessonSection>,
    val quizId: String,
    val labId: String? = null
)

data class QuizQuestion(
    val id: String,
    val questionText: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class Quiz(
    val id: String,
    val lessonId: String,
    val title: String,
    val questions: List<QuizQuestion>,
    val passingScorePercentage: Int = 70,
    val xpReward: Int = 50
)

enum class LabType {
    PHISHING_INSPECTOR,
    URL_ANALYZER,
    PASSWORD_STRENGTH,
    PERMISSION_AUDITOR,
    UPI_SCAM_DEFENDER
}

data class Lab(
    val id: String,
    val lessonId: String,
    val title: String,
    val category: String,
    val description: String,
    val type: LabType,
    val xpReward: Int = 75
)

data class Badge(
    val id: String,
    val title: String,
    val description: String,
    val iconName: String,
    val xpBonus: Int = 50
)

data class DailyTip(
    val id: Int,
    val title: String,
    val summary: String,
    val actionableRecommendation: String,
    val category: String
)

data class CourseCertificate(
    val id: String,
    val courseId: String,
    val courseTitle: String,
    val category: String,
    val requiredQuizId: String,
    val passingScore: Int = 70
)
