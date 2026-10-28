package com.dcdevelopers.cybersaathi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dcdevelopers.cybersaathi.data.models.Lesson
import com.dcdevelopers.cybersaathi.data.models.UserProgress
import com.dcdevelopers.cybersaathi.ui.components.TopBar
import com.dcdevelopers.cybersaathi.ui.theme.*

@Composable
fun LearningHubScreen(
    lessons: List<Lesson>,
    progress: UserProgress,
    onSelectLesson: (Lesson) -> Unit,
    onBack: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var currentPage by remember { mutableStateOf(0) }

    val filteredLessons = lessons.filter { lesson ->
        lesson.title.contains(searchQuery, ignoreCase = true) ||
                lesson.category.contains(searchQuery, ignoreCase = true)
    }

    val pageSize = 3
    val totalPages = ((filteredLessons.size - 1) / pageSize) + 1
    val safePage = currentPage.coerceIn(0, (totalPages - 1).coerceAtLeast(0))
    val pagedLessons = filteredLessons.drop(safePage * pageSize).take(pageSize)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(title = "LEARNING HUB", subtitle = "Choose your cybersecurity module", onBack = onBack)

        // Search Field
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                currentPage = 0
            },
            placeholder = { Text("Search 8+ cybersecurity modules...", fontSize = 12.sp) },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null, tint = CyanCyber, modifier = Modifier.size(18.dp)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().height(48.dp),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = CyanCyber,
                unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "MODULES (${filteredLessons.size})",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp
            )

            Text(
                "Page ${safePage + 1} of $totalPages",
                color = CyanCyber,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // 3 Cards Column (Fits inside single screen)
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            pagedLessons.forEachIndexed { index, lesson ->
                val overallIdx = safePage * pageSize + index + 1
                val isCompleted = progress.completedLessons.contains(lesson.id)
                val isLastOpened = (progress.lastOpenedLessonId == lesson.id)

                LessonCard(
                    index = overallIdx,
                    lesson = lesson,
                    isCompleted = isCompleted,
                    isLastOpened = isLastOpened,
                    onClick = { onSelectLesson(lesson) }
                )
            }
        }

        // Pagination Navigation Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = { if (safePage > 0) currentPage-- },
                enabled = (safePage > 0),
                modifier = Modifier.weight(1f).height(44.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text("Previous Page", fontSize = 12.sp)
            }

            Button(
                onClick = { if (safePage < totalPages - 1) currentPage++ },
                enabled = (safePage < totalPages - 1),
                modifier = Modifier.weight(1f).height(44.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
            ) {
                Text("Next Page", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(6.dp))
                Icon(Icons.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
private fun LessonCard(
    index: Int,
    lesson: Lesson,
    isCompleted: Boolean,
    isLastOpened: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isCompleted) GreenSuccess.copy(alpha = 0.15f) else Color(0xFF1B3440)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    if (isCompleted) Icons.Filled.CheckCircle else Icons.Filled.MenuBook,
                    contentDescription = null,
                    tint = if (isCompleted) GreenSuccess else CyanCyber,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(Modifier.width(10.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "$index. ${lesson.title}",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )

                    if (isCompleted) {
                        Spacer(Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(GreenSuccess.copy(alpha = 0.2f))
                                .padding(horizontal = 5.dp, vertical = 1.dp)
                        ) {
                            Text("DONE", color = GreenSuccess, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                        }
                    } else if (isLastOpened) {
                        Spacer(Modifier.width(6.dp))
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(CyanCyber.copy(alpha = 0.2f))
                                .padding(horizontal = 5.dp, vertical = 1.dp)
                        ) {
                            Text("RESUME", color = CyanCyber, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Text(
                    lesson.subtitle,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp,
                    maxLines = 1
                )

                Text(
                    "${lesson.category} • ${lesson.durationMinutes} MIN • ${lesson.difficulty}",
                    color = CyanCyber,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Icon(
                Icons.Filled.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
