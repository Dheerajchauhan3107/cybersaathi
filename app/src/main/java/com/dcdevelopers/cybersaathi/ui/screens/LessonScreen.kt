package com.dcdevelopers.cybersaathi.ui.screens

import android.speech.tts.TextToSpeech
import androidx.compose.animation.*
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
    onCompleteAndStartQuiz: () -> Unit,
    onStartLab: ((String) -> Unit)?,
    onBack: () -> Unit
) {
    var currentSectionIndex by remember { mutableStateOf(0) }
    val sectionCount = lesson.sections.size
    val currentSection = lesson.sections[currentSectionIndex]

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
            .verticalScroll(rememberScrollState()) // Vertically scrollable theory pages as requested
            .padding(16.dp)
            .padding(bottom = 60.dp), // Prevent bottom nav overlap
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        TopBar(title = lesson.title, subtitle = lesson.category, onBack = {
            tts?.stop()
            onBack()
        })

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

                    // Text-To-Speech Speaker Button
                    IconButton(
                        onClick = {
                            if (isSpeaking) {
                                tts?.stop()
                                isSpeaking = false
                            } else {
                                val speakText = "${currentSection.title}. ${currentSection.bodyText}"
                                tts?.speak(speakText, TextToSpeech.QUEUE_FLUSH, null, null)
                                isSpeaking = true
                            }
                        },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            if (isSpeaking) Icons.Filled.VolumeOff else Icons.Filled.VolumeUp,
                            contentDescription = "Read Aloud",
                            tint = if (isSpeaking) GreenSuccess else CyanCyber,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(Modifier.width(6.dp))

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

        // Section Title & Body
        Text(
            currentSection.title,
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
                    currentSection.bodyText,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
            }
        }

        // Warning Tip Callout
        if (currentSection.warningTip != null) {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = YellowWarning.copy(alpha = 0.1f)),
                modifier = Modifier.fillMaxWidth().border(1.dp, YellowWarning.copy(alpha = 0.5f), RoundedCornerShape(14.dp))
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Warning, contentDescription = null, tint = YellowWarning, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(currentSection.warningTip, color = MaterialTheme.colorScheme.onBackground, fontSize = 11.sp, lineHeight = 15.sp)
                }
            }
        }

        // Practical Example Callout
        if (currentSection.practicalExample != null) {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = CyanCyber.copy(alpha = 0.08f)),
                modifier = Modifier.fillMaxWidth().border(1.dp, CyanCyber.copy(alpha = 0.4f), RoundedCornerShape(14.dp))
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Lightbulb, contentDescription = null, tint = CyanCyber, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(currentSection.practicalExample, color = MaterialTheme.colorScheme.onBackground, fontSize = 11.sp, lineHeight = 15.sp)
                }
            }
        }

        Spacer(Modifier.height(10.dp))

        // Navigation Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (currentSectionIndex > 0) {
                OutlinedButton(
                    onClick = {
                        tts?.stop()
                        isSpeaking = false
                        currentSectionIndex--
                    },
                    modifier = Modifier.weight(1f).height(46.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Previous Page", fontSize = 12.sp)
                }
            }

            if (currentSectionIndex < sectionCount - 1) {
                Button(
                    onClick = {
                        tts?.stop()
                        isSpeaking = false
                        currentSectionIndex++
                    },
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
                    onClick = {
                        tts?.stop()
                        isSpeaking = false
                        onCompleteAndStartQuiz()
                    },
                    modifier = Modifier.weight(1f).height(46.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
                ) {
                    Icon(Icons.Filled.Quiz, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(if (isAlreadyCompleted) "RETRACT & QUIZ" else "START QUIZ", fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
                }
            }
        }
    }
}
