package com.dcdevelopers.cybersaathi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.dcdevelopers.cybersaathi.data.models.Lab
import com.dcdevelopers.cybersaathi.data.models.UserProgress
import com.dcdevelopers.cybersaathi.ui.components.TopBar
import com.dcdevelopers.cybersaathi.ui.theme.*

@Composable
fun LabHubScreen(
    labs: List<Lab>,
    progress: UserProgress,
    onSelectLab: (Lab) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .padding(bottom = 60.dp), // Prevent bottom navigation overlap
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(title = "PRACTICE LABS", subtitle = "Interactive Defensive Simulations", onBack = onBack)

        Text(
            "DEFENSIVE LABS (${labs.size})",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.2.sp
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            labs.forEach { lab ->
                val isCompleted = progress.completedLabs.contains(lab.id)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectLab(lab) },
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
                                .size(38.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isCompleted) GreenSuccess.copy(alpha = 0.15f) else Color(0xFF1B3440)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                if (isCompleted) Icons.Filled.CheckCircle else Icons.Filled.Science,
                                contentDescription = null,
                                tint = if (isCompleted) GreenSuccess else CyanCyber,
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
                                    lab.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    maxLines = 1,
                                    modifier = Modifier.weight(1f)
                                )

                                Spacer(Modifier.width(6.dp))

                                // Compact Horizontal Status Chip
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(if (isCompleted) GreenSuccess.copy(alpha = 0.2f) else CyanCyber.copy(alpha = 0.15f))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = if (isCompleted) "COMPLETED" else "+${lab.xpReward} XP",
                                        color = if (isCompleted) GreenSuccess else CyanCyber,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(Modifier.height(2.dp))

                            Text(
                                lab.description,
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(10.dp)
        ) {
            Text("🛡️ Practice labs are offline defensive simulations for educational safety.", fontSize = 10.sp, color = CyanCyber, lineHeight = 14.sp)
        }
    }
}
