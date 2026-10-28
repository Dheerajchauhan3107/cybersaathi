package com.dcdevelopers.cybersaathi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dcdevelopers.cybersaathi.ui.components.TopBar
import com.dcdevelopers.cybersaathi.ui.theme.*

@Composable
fun ResultScreen(
    title: String,
    scorePercentage: Int,
    correctCount: Int,
    totalQuestions: Int,
    xpEarned: Int,
    onRetry: () -> Unit,
    onViewCertificate: (() -> Unit)?,
    onBackToHub: () -> Unit
) {
    val isPassed = scorePercentage >= 70

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        TopBar(title = "Assessment Result", subtitle = title, onBack = onBackToHub)

        // Result Banner Card
        Card(
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)
                        .background(if (isPassed) GreenSuccess.copy(alpha = 0.15f) else YellowWarning.copy(alpha = 0.15f))
                        .border(2.dp, if (isPassed) GreenSuccess else YellowWarning, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "$scorePercentage%",
                            color = if (isPassed) GreenSuccess else YellowWarning,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            "SCORE",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Text(
                    if (isPassed) "CONGRATULATIONS! YOU PASSED!" else "KEEP PRACTICING!",
                    color = if (isPassed) GreenSuccess else YellowWarning,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.2.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    "You correctly answered $correctCount out of $totalQuestions questions.",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )

                if (xpEarned > 0) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(14.dp))
                            .background(Brush.horizontalGradient(listOf(CyanCyber, BlueCyber)))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            "🎉 +$xpEarned XP REWARD EARNED!",
                            color = BackgroundCyber,
                            fontWeight = FontWeight.Black,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }

        // Action Buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (isPassed && onViewCertificate != null) {
                Button(
                    onClick = onViewCertificate,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
                ) {
                    Icon(Icons.Filled.WorkspacePremium, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("VIEW COMPLETION CERTIFICATE", fontWeight = FontWeight.ExtraBold)
                }
            }

            OutlinedButton(
                onClick = onRetry,
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Filled.Refresh, contentDescription = null, tint = CyanCyber)
                Spacer(Modifier.width(8.dp))
                Text("RETRY QUIZ", color = CyanCyber, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = onBackToHub,
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant, contentColor = MaterialTheme.colorScheme.onBackground)
            ) {
                Text("RETURN TO LEARNING HUB", fontWeight = FontWeight.Bold)
            }
        }
    }
}
