package com.dcdevelopers.cybersaathi.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.dcdevelopers.cybersaathi.data.models.Quiz
import com.dcdevelopers.cybersaathi.ui.components.TopBar
import com.dcdevelopers.cybersaathi.ui.theme.*

@Composable
fun QuizScreen(
    quiz: Quiz,
    onQuizCompleted: (scorePercentage: Int, correctCount: Int, totalQuestions: Int) -> Unit,
    onBack: () -> Unit
) {
    var questionIndex by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var isSubmitted by remember { mutableStateOf(false) }
    var correctAnswersCount by remember { mutableStateOf(0) }

    val totalQuestions = quiz.questions.size
    val currentQuestion = quiz.questions[questionIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(title = quiz.title, subtitle = "Knowledge Check", onBack = onBack)

        // Progress Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("QUESTION ${questionIndex + 1} OF $totalQuestions", color = CyanCyber, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.2.sp)
            Spacer(Modifier.weight(1f))
            Text("Score: $correctAnswersCount", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }

        LinearProgressIndicator(
            progress = { (questionIndex + 1).toFloat() / totalQuestions },
            modifier = Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)),
            color = CyanCyber,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )

        // Question Card
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.padding(14.dp)) {
                Text(
                    currentQuestion.questionText,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 20.sp
                )
            }
        }

        // 4 Compact Options
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            currentQuestion.options.forEachIndexed { optIdx, optionText ->
                val isSelected = (selectedOption == optIdx)
                val isCorrect = (optIdx == currentQuestion.correctIndex)

                val backgroundColor = when {
                    isSubmitted && isCorrect -> GreenSuccess.copy(alpha = 0.15f)
                    isSubmitted && isSelected -> RedError.copy(alpha = 0.15f)
                    isSelected -> CyanCyber.copy(alpha = 0.12f)
                    else -> MaterialTheme.colorScheme.surfaceVariant
                }

                val borderColor = when {
                    isSubmitted && isCorrect -> GreenSuccess
                    isSubmitted && isSelected -> RedError
                    isSelected -> CyanCyber
                    else -> Color.Transparent
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = !isSubmitted) { selectedOption = optIdx }
                        .border(1.5.dp, borderColor, RoundedCornerShape(14.dp)),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = backgroundColor)
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(
                                    when {
                                        isSubmitted && isCorrect -> GreenSuccess
                                        isSubmitted && isSelected -> RedError
                                        isSelected -> CyanCyber
                                        else -> MaterialTheme.colorScheme.background
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = ('A' + optIdx).toString(),
                                color = if (isSelected || (isSubmitted && isCorrect)) BackgroundCyber else MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                        }

                        Spacer(Modifier.width(10.dp))

                        Text(
                            optionText,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // Explanation Box
        if (isSubmitted) {
            val wasCorrect = (selectedOption == currentQuestion.correctIndex)
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (wasCorrect) GreenSuccess.copy(alpha = 0.1f) else RedError.copy(alpha = 0.1f)
                ),
                modifier = Modifier.fillMaxWidth().border(1.dp, if (wasCorrect) GreenSuccess else RedError, RoundedCornerShape(12.dp))
            ) {
                Box(modifier = Modifier.padding(10.dp)) {
                    Text(
                        currentQuestion.explanation,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 11.sp,
                        lineHeight = 15.sp
                    )
                }
            }
        }

        // Action Button
        if (!isSubmitted) {
            Button(
                onClick = {
                    isSubmitted = true
                    if (selectedOption == currentQuestion.correctIndex) {
                        correctAnswersCount++
                    }
                },
                enabled = (selectedOption != null),
                modifier = Modifier.fillMaxWidth().height(46.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
            ) {
                Text("SUBMIT ANSWER", fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
            }
        } else {
            Button(
                onClick = {
                    if (questionIndex < totalQuestions - 1) {
                        questionIndex++
                        selectedOption = null
                        isSubmitted = false
                    } else {
                        val scorePct = ((correctAnswersCount.toFloat() / totalQuestions) * 100).toInt()
                        onQuizCompleted(scorePct, correctAnswersCount, totalQuestions)
                    }
                },
                modifier = Modifier.fillMaxWidth().height(46.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
            ) {
                Text(
                    if (questionIndex < totalQuestions - 1) "NEXT QUESTION" else "SEE RESULT",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 12.sp
                )
            }
        }
    }
}
