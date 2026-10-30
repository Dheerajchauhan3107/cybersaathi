package com.dcdevelopers.cybersaathi.ui.screens

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dcdevelopers.cybersaathi.data.models.Lesson
import com.dcdevelopers.cybersaathi.ui.components.TopBar
import com.dcdevelopers.cybersaathi.ui.theme.*
import java.util.Locale

@Composable
fun LessonScreen(
    lesson: Lesson,
    isAlreadyCompleted: Boolean,
    onMarkLessonComplete: () -> Unit,
    onStartQuiz: (() -> Unit)?,
    onStartLab: ((String) -> Unit)?,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }
    var isSpeaking by remember { mutableStateOf(false) }

    DisposableEffect(context) {
        val textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.ENGLISH
            }
        }
        tts = textToSpeech
        onDispose {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()) // One continuous vertically scrollable theory screen
            .padding(16.dp)
            .padding(bottom = 70.dp), // Clear bottom navigation insets
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopBar(
            title = lesson.title,
            subtitle = lesson.category,
            onBack = {
                tts?.stop()
                onBack()
            }
        )

        // Lesson Overview Header Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(Brush.linearGradient(listOf(Color(0xFF153744), Color(0xFF17223B))))
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "${lesson.durationMinutes} MIN READ • ${lesson.difficulty.uppercase()}",
                        color = CyanCyber,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )

                    Spacer(Modifier.weight(1f))

                    // Text-To-Speech Read Aloud Control
                    IconButton(
                        onClick = {
                            if (isSpeaking) {
                                tts?.stop()
                                isSpeaking = false
                            } else {
                                val fullText = lesson.sections.joinToString(". ") { "${it.title}. ${it.bodyText}" }
                                tts?.speak(fullText, TextToSpeech.QUEUE_FLUSH, null, null)
                                isSpeaking = true
                            }
                        },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            if (isSpeaking) Icons.Filled.VolumeOff else Icons.Filled.VolumeUp,
                            contentDescription = "Read Aloud",
                            tint = if (isSpeaking) GreenSuccess else CyanCyber,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }

                Text(
                    lesson.subtitle,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // All Lesson Sections Combined Continuously in Order
        lesson.sections.forEachIndexed { index, section ->
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    "${index + 1}. ${section.title}",
                    color = CyanCyber,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier.padding(16.dp)) {
                        Text(
                            section.bodyText,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 14.sp,
                            lineHeight = 22.sp
                        )
                    }
                }

                // Warning Tip Callout Card
                if (section.warningTip != null) {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = YellowWarning.copy(alpha = 0.1f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, YellowWarning.copy(alpha = 0.5f), RoundedCornerShape(14.dp))
                    ) {
                        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.Warning, contentDescription = null, tint = YellowWarning, modifier = Modifier.size(20.dp))
                            Spacer(Modifier.width(10.dp))
                            Text(section.warningTip, color = MaterialTheme.colorScheme.onBackground, fontSize = 12.sp, lineHeight = 17.sp)
                        }
                    }
                }

                // Practical Example Callout Card
                if (section.practicalExample != null) {
                    Card(
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = CyanCyber.copy(alpha = 0.08f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, CyanCyber.copy(alpha = 0.4f), RoundedCornerShape(14.dp))
                    ) {
                        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.Lightbulb, contentDescription = null, tint = CyanCyber, modifier = Modifier.size(20.dp))
                            Spacer(Modifier.width(10.dp))
                            Text(section.practicalExample, color = MaterialTheme.colorScheme.onBackground, fontSize = 12.sp, lineHeight = 17.sp)
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(10.dp))

        // Action Controls (Independent of mandatory quiz navigation)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    tts?.stop()
                    onMarkLessonComplete()
                },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = if (isAlreadyCompleted) GreenSuccess else CyanCyber, contentColor = BackgroundCyber)
            ) {
                Icon(Icons.Filled.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text(if (isAlreadyCompleted) "LESSON COMPLETED ✓" else "MARK LESSON AS COMPLETED", fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
            }

            if (onStartQuiz != null) {
                OutlinedButton(
                    onClick = {
                        tts?.stop()
                        onStartQuiz()
                    },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(Icons.Filled.Quiz, contentDescription = null, tint = CyanCyber, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("TAKE OPTIONAL QUIZ ASSESSMENT", color = CyanCyber, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }

            if (lesson.labId != null && onStartLab != null) {
                OutlinedButton(
                    onClick = {
                        tts?.stop()
                        onStartLab(lesson.labId)
                    },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(Icons.Filled.Science, contentDescription = null, tint = CyanCyber, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("TRY DEFENSIVE PRACTICE LAB", color = CyanCyber, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        }
    }
}
