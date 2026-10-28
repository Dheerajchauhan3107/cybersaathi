package com.dcdevelopers.cybersaathi.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.dcdevelopers.cybersaathi.data.repository.ContentRepository
import com.dcdevelopers.cybersaathi.data.repository.ProgressRepository
import com.dcdevelopers.cybersaathi.ui.components.BottomTab
import com.dcdevelopers.cybersaathi.ui.components.CyberBottomNavigation
import com.dcdevelopers.cybersaathi.ui.screens.*
import com.dcdevelopers.cybersaathi.ui.theme.CyberSaathiTheme

sealed class ScreenState {
    object Onboarding : ScreenState()
    object Home : ScreenState()
    object LearningHub : ScreenState()
    object LabHub : ScreenState()
    object CertificateHub : ScreenState()
    data class LessonView(val lessonId: String) : ScreenState()
    data class QuizView(val quizId: String) : ScreenState()
    data class LabView(val labId: String) : ScreenState()
    data class ResultView(
        val title: String,
        val scorePercentage: Int,
        val correctCount: Int,
        val totalQuestions: Int,
        val xpEarned: Int,
        val quizId: String,
        val lessonTitle: String
    ) : ScreenState()
    data class CertificateView(val displayName: String, val courseTitle: String) : ScreenState()
    object Profile : ScreenState()
    object Settings : ScreenState()
    object PrivacyPolicy : ScreenState()
    object About : ScreenState()
}

@Composable
fun CyberSaathiAppNavigation(
    progressRepository: ProgressRepository
) {
    val progress by progressRepository.userProgress.collectAsState()

    var currentScreen by remember(progress.isFirstLaunch) {
        mutableStateOf<ScreenState>(
            if (progress.isFirstLaunch) ScreenState.Onboarding else ScreenState.Home
        )
    }

    val selectedTab = when (currentScreen) {
        ScreenState.Home -> BottomTab.HOME
        ScreenState.LearningHub, is ScreenState.LessonView, is ScreenState.QuizView -> BottomTab.LEARN
        ScreenState.LabHub, is ScreenState.LabView -> BottomTab.PRACTICE
        ScreenState.CertificateHub, is ScreenState.CertificateView, is ScreenState.ResultView -> BottomTab.PROGRESS
        ScreenState.Profile, ScreenState.Settings, ScreenState.PrivacyPolicy, ScreenState.About -> BottomTab.PROFILE
        else -> BottomTab.HOME
    }

    CyberSaathiTheme(themePreference = progress.themePreference) {
        Scaffold(
            bottomBar = {
                if (currentScreen != ScreenState.Onboarding) {
                    CyberBottomNavigation(
                        selectedTab = selectedTab,
                        onTabSelected = { tab ->
                            currentScreen = when (tab) {
                                BottomTab.HOME -> ScreenState.Home
                                BottomTab.LEARN -> ScreenState.LearningHub
                                BottomTab.PRACTICE -> ScreenState.LabHub
                                BottomTab.PROGRESS -> ScreenState.CertificateHub
                                BottomTab.PROFILE -> ScreenState.Profile
                            }
                        }
                    )
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (val screen = currentScreen) {
                    ScreenState.Onboarding -> {
                        OnboardingScreen(
                            onComplete = { name ->
                                progressRepository.updateDisplayName(name)
                                progressRepository.setFirstLaunchCompleted()
                                currentScreen = ScreenState.Home
                            }
                        )
                    }

                    ScreenState.Home -> {
                        val dayIndex = ((System.currentTimeMillis() / (1000 * 60 * 60 * 24)) % ContentRepository.dailyTips.size).toInt()
                        val todayTip = ContentRepository.dailyTips.getOrElse(dayIndex) { ContentRepository.dailyTips.first() }

                        val recommended = ContentRepository.lessons.firstOrNull { !progress.completedLessons.contains(it.id) }
                            ?: ContentRepository.lessons.first()

                        HomeScreen(
                            progress = progress,
                            dailyTip = todayTip,
                            recommendedLesson = recommended,
                            onStartLearning = {
                                val nextLesson = progress.lastOpenedLessonId ?: recommended.id
                                currentScreen = ScreenState.LessonView(nextLesson)
                            },
                            onOpenLesson = { lessonId ->
                                currentScreen = ScreenState.LessonView(lessonId)
                            },
                            onOpenProfile = { currentScreen = ScreenState.Profile },
                            onOpenSettings = { currentScreen = ScreenState.Settings }
                        )
                    }

                    ScreenState.LearningHub -> {
                        LearningHubScreen(
                            lessons = ContentRepository.lessons,
                            progress = progress,
                            onSelectLesson = { lesson ->
                                currentScreen = ScreenState.LessonView(lesson.id)
                            },
                            onBack = { currentScreen = ScreenState.Home }
                        )
                    }

                    ScreenState.LabHub -> {
                        LabHubScreen(
                            labs = ContentRepository.labs,
                            progress = progress,
                            onSelectLab = { lab ->
                                currentScreen = ScreenState.LabView(lab.id)
                            },
                            onBack = { currentScreen = ScreenState.Home }
                        )
                    }

                    ScreenState.CertificateHub -> {
                        CertificateHubScreen(
                            certificates = ContentRepository.certificates,
                            progress = progress,
                            onSelectCertificate = { cert ->
                                currentScreen = ScreenState.CertificateView(
                                    displayName = progress.displayName,
                                    courseTitle = cert.courseTitle
                                )
                            },
                            onBack = { currentScreen = ScreenState.Home }
                        )
                    }

                    is ScreenState.LessonView -> {
                        val lesson = ContentRepository.lessons.firstOrNull { it.id == screen.lessonId }
                            ?: ContentRepository.lessons.first()

                        val isCompleted = progress.completedLessons.contains(lesson.id)

                        LessonScreen(
                            lesson = lesson,
                            isAlreadyCompleted = isCompleted,
                            onCompleteAndStartQuiz = {
                                progressRepository.completeLesson(lesson.id, xpReward = 30)
                                currentScreen = ScreenState.QuizView(lesson.quizId)
                            },
                            onStartLab = if (lesson.labId != null) {
                                { labId -> currentScreen = ScreenState.LabView(labId) }
                            } else null,
                            onBack = { currentScreen = ScreenState.LearningHub }
                        )
                    }

                    is ScreenState.QuizView -> {
                        val quiz = ContentRepository.quizzes[screen.quizId]
                            ?: ContentRepository.quizzes.values.first()

                        val lesson = ContentRepository.lessons.firstOrNull { it.quizId == quiz.id }

                        QuizScreen(
                            quiz = quiz,
                            onQuizCompleted = { scorePct, correct, total ->
                                val xpEarned = if (progressRepository.recordQuizResult(quiz.id, scorePct, xpReward = 50)) 50 else 0
                                currentScreen = ScreenState.ResultView(
                                    title = quiz.title,
                                    scorePercentage = scorePct,
                                    correctCount = correct,
                                    totalQuestions = total,
                                    xpEarned = xpEarned,
                                    quizId = quiz.id,
                                    lessonTitle = lesson?.title ?: "Cybersecurity"
                                )
                            },
                            onBack = {
                                if (lesson != null) {
                                    currentScreen = ScreenState.LessonView(lesson.id)
                                } else {
                                    currentScreen = ScreenState.LearningHub
                                }
                            }
                        )
                    }

                    is ScreenState.LabView -> {
                        val lab = ContentRepository.labs.firstOrNull { it.id == screen.labId }
                            ?: ContentRepository.labs.first()

                        LabScreen(
                            lab = lab,
                            onLabCompleted = { xpReward ->
                                progressRepository.completeLab(lab.id, xpReward = xpReward)
                                currentScreen = ScreenState.LabHub
                            },
                            onBack = { currentScreen = ScreenState.LabHub }
                        )
                    }

                    is ScreenState.ResultView -> {
                        ResultScreen(
                            title = screen.title,
                            scorePercentage = screen.scorePercentage,
                            correctCount = screen.correctCount,
                            totalQuestions = screen.totalQuestions,
                            xpEarned = screen.xpEarned,
                            onRetry = {
                                currentScreen = ScreenState.QuizView(screen.quizId)
                            },
                            onViewCertificate = if (screen.scorePercentage >= 70) {
                                {
                                    currentScreen = ScreenState.CertificateView(
                                        displayName = progress.displayName,
                                        courseTitle = screen.lessonTitle
                                    )
                                }
                            } else null,
                            onBackToHub = { currentScreen = ScreenState.LearningHub }
                        )
                    }

                    is ScreenState.CertificateView -> {
                        CertificateScreen(
                            displayName = screen.displayName,
                            courseTitle = screen.courseTitle,
                            onBack = { currentScreen = ScreenState.CertificateHub }
                        )
                    }

                    ScreenState.Profile -> {
                        ProfileScreen(
                            progress = progress,
                            allBadges = ContentRepository.badges,
                            onUpdateName = { newName ->
                                progressRepository.updateDisplayName(newName)
                            },
                            onOpenSettings = { currentScreen = ScreenState.Settings },
                            onResetProgress = {
                                progressRepository.resetProgress()
                                currentScreen = ScreenState.Onboarding
                            },
                            onBack = { currentScreen = ScreenState.Home }
                        )
                    }

                    ScreenState.Settings -> {
                        SettingsScreen(
                            progress = progress,
                            onSelectTheme = { theme ->
                                progressRepository.updateThemePreference(theme)
                            },
                            onOpenPrivacyPolicy = { currentScreen = ScreenState.PrivacyPolicy },
                            onOpenAbout = { currentScreen = ScreenState.About },
                            onResetProgress = {
                                progressRepository.resetProgress()
                                currentScreen = ScreenState.Onboarding
                            },
                            onBack = { currentScreen = ScreenState.Home }
                        )
                    }

                    ScreenState.PrivacyPolicy -> {
                        PrivacyPolicyScreen(
                            onBack = { currentScreen = ScreenState.Settings }
                        )
                    }

                    ScreenState.About -> {
                        AboutScreen(
                            onBack = { currentScreen = ScreenState.Settings }
                        )
                    }
                }
            }
        }
    }
}
