package com.dcdevelopers.cybersaathi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
    val pageSize = 4
    val totalPages = ((certificates.size - 1) / pageSize) + 1
    val pagedCerts = certificates.drop(currentPage * pageSize).take(pageSize)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(title = "CERTIFICATE CREDENTIALS", subtitle = "Earned & Available Credentials", onBack = onBack)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "COURSE CERTIFICATES (${certificates.size})",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.2.sp
            )

            Text(
                "Page ${currentPage + 1} of $totalPages",
                color = CyanCyber,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // 4 Compact Cards in Viewport
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            pagedCerts.forEach { cert ->
                val quizScore = progress.quizScores[cert.requiredQuizId] ?: 0
                val isLessonCompleted = progress.completedLessons.contains(cert.courseId)
                val isEligible = isLessonCompleted && (quizScore >= cert.passingScore)

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
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(if (isEligible) CyanCyber.copy(alpha = 0.2f) else MaterialTheme.colorScheme.background),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                if (isEligible) Icons.Filled.WorkspacePremium else Icons.Filled.Lock,
                                contentDescription = null,
                                tint = if (isEligible) CyanCyber else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(Modifier.width(10.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                cert.courseTitle,
                                color = if (isEligible) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )

                            Text(
                                if (isEligible) "Ready to View & Export Certificate" else "Requires: Complete lesson & Quiz score >= 70% (Current: $quizScore%)",
                                color = if (isEligible) GreenSuccess else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 10.sp
                            )
                        }

                        if (isEligible) {
                            Text("VIEW", color = CyanCyber, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Pagination Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = { if (currentPage > 0) currentPage-- },
                enabled = (currentPage > 0),
                modifier = Modifier.weight(1f).height(44.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(6.dp))
                Text("Previous", fontSize = 12.sp)
            }

            Button(
                onClick = { if (currentPage < totalPages - 1) currentPage++ },
                enabled = (currentPage < totalPages - 1),
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
