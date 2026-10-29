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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dcdevelopers.cybersaathi.data.models.CourseCertificate
import com.dcdevelopers.cybersaathi.data.models.UserProgress
import com.dcdevelopers.cybersaathi.ui.components.TopBar
import com.dcdevelopers.cybersaathi.ui.theme.*

@Composable
fun CertificateHubScreen(
    certificates: List<CourseCertificate>,
    progress: UserProgress,
    onSelectCertificate: (CourseCertificate) -> Unit,
    onBack: () -> Unit
) {
    var currentPage by remember { mutableStateOf(0) }
    val pageSize = 3
    val totalPages = ((certificates.size - 1) / pageSize) + 1
    val safePage = currentPage.coerceIn(0, (totalPages - 1).coerceAtLeast(0))
    val pagedCerts = certificates.drop(safePage * pageSize).take(pageSize)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(title = "CERTIFICATES", subtitle = "Earned & Available Credentials", onBack = onBack)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "CREDENTIALS (${certificates.size})",
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

        // Measured Compact Cards List
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f, fill = false)
        ) {
            pagedCerts.forEach { cert ->
                val quizScore = progress.quizScores[cert.requiredQuizId] ?: 0
                val isLessonCompleted = progress.completedLessons.contains(cert.courseId)
                val isLabCompleted = cert.requiredLabId == null || progress.completedLabs.contains(cert.requiredLabId)
                val isEligible = isLessonCompleted && isLabCompleted && (quizScore >= cert.passingScore)
                val isAvailableToAttempt = isLessonCompleted && !isEligible

                val statusText = when {
                    isEligible -> "EARNED"
                    isAvailableToAttempt -> "AVAILABLE"
                    else -> "LOCKED"
                }

                val statusColor = when {
                    isEligible -> GreenSuccess
                    isAvailableToAttempt -> CyanCyber
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = isEligible) { onSelectCertificate(cert) },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isEligible) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(if (isEligible) CyanCyber.copy(alpha = 0.2f) else MaterialTheme.colorScheme.background),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                if (isEligible) Icons.Filled.WorkspacePremium else Icons.Filled.Lock,
                                contentDescription = null,
                                tint = if (isEligible) CyanCyber else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(Modifier.width(10.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    cert.courseTitle,
                                    color = if (isEligible) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    maxLines = 1,
                                    modifier = Modifier.weight(1f)
                                )

                                Spacer(Modifier.width(6.dp))

                                // Compact Horizontal Status Chip
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(statusColor.copy(alpha = 0.15f))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = statusText,
                                        color = statusColor,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(Modifier.height(2.dp))

                            Text(
                                text = if (isEligible) "Tap to view and export certificate" else "Req: Lesson + Lab + Quiz >= 70% (Score: $quizScore%)",
                                color = if (isEligible) GreenSuccess else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 10.sp,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }

        // Pagination Controls Always Visible Above Navigation Bars
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = { if (safePage > 0) currentPage-- },
                enabled = (safePage > 0),
                modifier = Modifier.weight(1f).height(42.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text("Previous", fontSize = 12.sp)
            }

            Button(
                onClick = { if (safePage < totalPages - 1) currentPage++ },
                enabled = (safePage < totalPages - 1),
                modifier = Modifier.weight(1f).height(42.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
            ) {
                Text("Next", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(6.dp))
                Icon(Icons.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
            }
        }
    }
}
