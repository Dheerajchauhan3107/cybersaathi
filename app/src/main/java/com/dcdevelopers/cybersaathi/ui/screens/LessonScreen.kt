package com.dcdevelopers.cybersaathi.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dcdevelopers.cybersaathi.data.models.Lesson
import com.dcdevelopers.cybersaathi.ui.components.TopBar
import com.dcdevelopers.cybersaathi.ui.theme.*

@Composable
fun LessonScreen(
    lesson: Lesson,
    isAlreadyCompleted: Boolean,
    onCompleteAndStartQuiz: () -> Unit,
    onStartLab: ((String) -> Unit)?,
    onBack: () -> Unit
) {
    var currentSectionIndex by remember { mutableStateOf(0) }
    val sectionCount = lesson.sections.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(title = lesson.title, subtitle = lesson.category, onBack = onBack)

        // Progress Header Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(Brush.linearGradient(listOf(Color(0xFF153744), Color(0xFF17223B))))
                .padding(14.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "${lesson.durationMinutes} MIN READ • ${lesson.difficulty.uppercase()}",
                        color = CyanCyber,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )

                    Spacer(Modifier.weight(1f))

                    Text(
                        "PAGE ${currentSectionIndex + 1} OF $sectionCount",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                LinearProgressIndicator(
                    progress = { (currentSectionIndex + 1).toFloat() / sectionCount },
                    modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                    color = CyanCyber,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }

        // Single Viewport Animated Section Body Card
        AnimatedContent(
            targetState = currentSectionIndex,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "section_transition",
            modifier = Modifier.weight(1f).padding(vertical = 8.dp)
        ) { page ->
            val section = lesson.sections[page]

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    section.title,
                    color = CyanCyber,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier.fillMaxWidth().weight(1f).padding(vertical = 6.dp)
                ) {
                    Box(modifier = Modifier.padding(14.dp)) {
                        Text(
                            section.bodyText,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 13.sp,
                            lineHeight = 19.sp
                        )
                    }
                }

                // Callout Box
                if (section.warningTip != null) {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = YellowWarning.copy(alpha = 0.1f)),
                        modifier = Modifier.fillMaxWidth().border(1.dp, YellowWarning.copy(alpha = 0.5f), RoundedCornerShape(14.dp))
                    ) {
                        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.Warning, contentDescription = null, tint = YellowWarning, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text(section.warningTip, color = MaterialTheme.colorScheme.onBackground, fontSize = 11.sp, lineHeight = 15.sp)
                        }
                    }
                } else if (section.practicalExample != null) {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = CyanCyber.copy(alpha = 0.08f)),
                        modifier = Modifier.fillMaxWidth().border(1.dp, CyanCyber.copy(alpha = 0.4f), RoundedCornerShape(14.dp))
                    ) {
                        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.Lightbulb, contentDescription = null, tint = CyanCyber, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(8.dp))
                            Text(section.practicalExample, color = MaterialTheme.colorScheme.onBackground, fontSize = 11.sp, lineHeight = 15.sp)
                        }
                    }
                }
            }
        }

        // Bottom Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (currentSectionIndex > 0) {
                OutlinedButton(
                    onClick = { currentSectionIndex-- },
                    modifier = Modifier.weight(1f).height(46.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Previous", fontSize = 12.sp)
                }
            }

            if (currentSectionIndex < sectionCount - 1) {
                Button(
                    onClick = { currentSectionIndex++ },
                    modifier = Modifier.weight(1f).height(46.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
                ) {
                    Text("Next Page", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Spacer(Modifier.width(4.dp))
                    Icon(Icons.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                }
            } else {
                Button(
                    onClick = onCompleteAndStartQuiz,
                    modifier = Modifier.weight(1f).height(46.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
                ) {
                    Icon(Icons.Filled.Quiz, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(if (isAlreadyCompleted) "RETACK QUIZ" else "START QUIZ", fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
                }
            }
        }
    }
}
