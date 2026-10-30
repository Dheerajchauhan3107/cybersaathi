package com.dcdevelopers.cybersaathi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dcdevelopers.cybersaathi.data.models.Course
import com.dcdevelopers.cybersaathi.data.models.Lesson
import com.dcdevelopers.cybersaathi.data.models.UserProgress
import com.dcdevelopers.cybersaathi.ui.components.TopBar
import com.dcdevelopers.cybersaathi.ui.theme.*

@Composable
fun CourseSyllabusScreen(
    course: Course,
    allLessons: List<Lesson>,
    progress: UserProgress,
    onSelectLesson: (Lesson) -> Unit,
    onStartQuiz: (String) -> Unit,
    onStartLab: ((String) -> Unit)?,
    onViewCertificate: () -> Unit,
    onBack: () -> Unit
) {
    val courseLessons = allLessons.filter { lesson ->
        course.modules.any { module -> module.lessonIds.contains(lesson.id) }
    }

    val completedCount = courseLessons.count { progress.completedLessons.contains(it.id) }
    val totalCount = courseLessons.size.coerceAtLeast(1)
    val progressPct = (completedCount.toFloat() / totalCount)

    val quizScore = progress.quizScores[course.quizId] ?: 0
    val isQuizPassed = quizScore >= 70
    val isLabCompleted = course.labId == null || progress.completedLabs.contains(course.labId)
    val isEligibleForCert = (completedCount == courseLessons.size) && isQuizPassed && isLabCompleted

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .padding(bottom = 60.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopBar(title = course.title, subtitle = "Course Syllabus & Roadmap", onBack = onBack)

        // Course Progress Banner
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.size(42.dp).clip(RoundedCornerShape(12.dp)).background(CyanCyber.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Filled.School, contentDescription = null, tint = CyanCyber, modifier = Modifier.size(24.dp))
                    }

                    Spacer(Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(course.category.uppercase(), color = CyanCyber, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.2.sp)
                        Text(course.title, color = MaterialTheme.colorScheme.onBackground, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Text(course.description, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp, lineHeight = 17.sp)

                LinearProgressIndicator(
                    progress = { progressPct },
                    modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                    color = CyanCyber,
                    trackColor = MaterialTheme.colorScheme.background
                )

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("$completedCount of ${courseLessons.size} Lessons Done", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Quiz: ${if (isQuizPassed) "$quizScore% ✓" else "$quizScore%"}", fontSize = 10.sp, color = if (isQuizPassed) GreenSuccess else YellowWarning, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Action Shortcuts (Quiz, Lab, Cert)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(
                onClick = { onStartQuiz(course.quizId) },
                modifier = Modifier.weight(1f).height(42.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Filled.Quiz, contentDescription = null, tint = CyanCyber, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Text("Quiz", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }

            if (course.labId != null && onStartLab != null) {
                OutlinedButton(
                    onClick = { onStartLab(course.labId) },
                    modifier = Modifier.weight(1f).height(42.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Filled.Science, contentDescription = null, tint = CyanCyber, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Lab", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }

            Button(
                onClick = onViewCertificate,
                enabled = isEligibleForCert,
                modifier = Modifier.weight(1f).height(42.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = if (isEligibleForCert) GreenSuccess else MaterialTheme.colorScheme.surfaceVariant, contentColor = BackgroundCyber)
            ) {
                Icon(Icons.Filled.WorkspacePremium, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Text(if (isEligibleForCert) "Cert ✓" else "Locked", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        Text("MODULES & LESSONS SYLLABUS", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.2.sp)

        // Modules List
        course.modules.forEach { module ->
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(module.title, color = CyanCyber, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text(module.description, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp)

                    HorizontalDivider(color = MaterialTheme.colorScheme.background, thickness = 1.dp)

                    module.lessonIds.forEach { lessonId ->
                        val lesson = allLessons.firstOrNull { it.id == lessonId }
                        if (lesson != null) {
                            val isCompleted = progress.completedLessons.contains(lesson.id)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable { onSelectLesson(lesson) }
                                    .background(MaterialTheme.colorScheme.background)
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    if (isCompleted) Icons.Filled.CheckCircle else Icons.Filled.PlayCircleOutline,
                                    contentDescription = null,
                                    tint = if (isCompleted) GreenSuccess else CyanCyber,
                                    modifier = Modifier.size(18.dp)
                                )

                                Spacer(Modifier.width(10.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(lesson.title, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = MaterialTheme.colorScheme.onBackground)
                                    Text("${lesson.durationMinutes} MIN • ${lesson.difficulty}", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(if (isCompleted) GreenSuccess.copy(alpha = 0.2f) else CyanCyber.copy(alpha = 0.15f))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        if (isCompleted) "DONE" else "START",
                                        color = if (isCompleted) GreenSuccess else CyanCyber,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
