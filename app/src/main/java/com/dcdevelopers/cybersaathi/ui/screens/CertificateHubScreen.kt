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
    onOpenSamplePreview: () -> Unit,
    onBack: () -> Unit
) {
    var currentPage by remember { mutableStateOf(0) }
    val pageSize = 2
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

        // Sample Certificate Banner
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.5.dp, YellowWarning.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
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
                        .background(YellowWarning.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Visibility, contentDescription = null, tint = YellowWarning, modifier = Modifier.size(20.dp))
                }

                Spacer(Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text("SAMPLE CERTIFICATE PREVIEW", color = YellowWarning, fontWeight = FontWeight.Bold, fontSize = 11.sp, letterSpacing = 0.8.sp)
                    Text("Preview the certificate design with placeholder 'Your Name'.", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
                }

                Spacer(Modifier.width(6.dp))

                Button(
                    onClick = onOpenSamplePreview,
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = YellowWarning, contentColor = BackgroundCyber)
                ) {
                    Text("PREVIEW", fontSize = 10.sp, fontWeight = FontWeight.ExtraBold)
                }
            }
        }

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
                "Page ${safePage + 1} of $totalPages",
                color = CyanCyber,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Paged Certificate Cards with Eligibility Checklist
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f, fill = false)
        ) {
            pagedCerts.forEach { cert ->
                val quizScore = progress.quizScores[cert.requiredQuizId] ?: 0
                val isLessonCompleted = progress.completedLessons.contains(cert.courseId)
                val isLabCompleted = cert.requiredLabId == null || progress.completedLabs.contains(cert.requiredLabId)
                val isEligible = isLessonCompleted && isLabCompleted && (quizScore >= cert.passingScore)

                val statusText = if (isEligible) "EARNED" else "LOCKED"
                val statusColor = if (isEligible) GreenSuccess else MaterialTheme.colorScheme.onSurfaceVariant

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = isEligible) { onSelectCertificate(cert) },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isEligible) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                if (isEligible) Icons.Filled.WorkspacePremium else Icons.Filled.Lock,
                                contentDescription = null,
                                tint = if (isEligible) CyanCyber else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(18.dp)
                            )

                            Spacer(Modifier.width(8.dp))

                            Text(
                                cert.courseTitle,
                                color = if (isEligible) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                maxLines = 1,
                                modifier = Modifier.weight(1f)
                            )

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

                        // Requirements Checklist Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            RequirementCheckItem("Theory", isLessonCompleted)
                            RequirementCheckItem("Quiz ($quizScore%/70%)", quizScore >= cert.passingScore)
                            RequirementCheckItem("Lab", isLabCompleted)
                        }
                    }
                }
            }
        }

        // Pagination Controls
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

@Composable
private fun RequirementCheckItem(label: String, isMet: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            if (isMet) Icons.Filled.CheckCircle else Icons.Filled.Cancel,
            contentDescription = null,
            tint = if (isMet) GreenSuccess else RedError,
            modifier = Modifier.size(12.dp)
        )
        Spacer(Modifier.width(4.dp))
        Text(
            label,
            color = if (isMet) GreenSuccess else MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 9.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
