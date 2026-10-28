package com.dcdevelopers.cybersaathi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dcdevelopers.cybersaathi.data.models.Badge
import com.dcdevelopers.cybersaathi.data.models.UserProgress
import com.dcdevelopers.cybersaathi.ui.components.StatCard
import com.dcdevelopers.cybersaathi.ui.components.TopBar
import com.dcdevelopers.cybersaathi.ui.theme.*

@Composable
fun ProfileScreen(
    progress: UserProgress,
    allBadges: List<Badge>,
    onUpdateName: (String) -> Unit,
    onOpenSettings: () -> Unit,
    onResetProgress: () -> Unit,
    onBack: () -> Unit
) {
    var isEditingName by remember { mutableStateOf(false) }
    var tempName by remember { mutableStateOf(progress.displayName) }

    val currentLevelBaseXp = (progress.level - 1) * 100
    val progressInLevel = (progress.xp - currentLevelBaseXp).coerceAtLeast(0)
    val xpForLevelUp = 100

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(title = "LEARNER PROFILE", subtitle = "Progress & Achievements", onBack = onBack)

        // Profile Avatar & Name Card
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Brush.linearGradient(listOf(CyanCyber, BlueCyber))),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Person, contentDescription = null, tint = BackgroundCyber, modifier = Modifier.size(32.dp))
                }

                if (!isEditingName) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(progress.displayName, color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                        IconButton(onClick = { isEditingName = true }) {
                            Icon(Icons.Filled.Edit, contentDescription = "Edit Name", tint = CyanCyber, modifier = Modifier.size(16.dp))
                        }
                    }
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        OutlinedTextField(
                            value = tempName,
                            onValueChange = { tempName = it },
                            singleLine = true,
                            modifier = Modifier.weight(1f).height(46.dp),
                            shape = RoundedCornerShape(10.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Button(
                            onClick = {
                                onUpdateName(tempName)
                                isEditingName = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
                        ) {
                            Text("Save", fontSize = 11.sp)
                        }
                    }
                }

                Text("LEVEL ${progress.level} CYBER DEFENDER", color = CyanCyber, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.2.sp)

                LinearProgressIndicator(
                    progress = { progressInLevel.toFloat() / xpForLevelUp },
                    modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                    color = CyanCyber,
                    trackColor = MaterialTheme.colorScheme.background
                )
            }
        }

        // Stats Summary Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatCard(Modifier.weight(1f), Icons.Filled.CheckCircle, "${progress.completedLessons.size}", "LESSONS")
            StatCard(Modifier.weight(1f), Icons.Filled.Science, "${progress.completedLabs.size}", "LABS")
            StatCard(Modifier.weight(1f), Icons.Filled.LocalFireDepartment, "${progress.streak}", "STREAK", iconTint = Color(0xFFFF9800))
            StatCard(Modifier.weight(1f), Icons.Filled.Star, "${progress.earnedBadges.size}/${allBadges.size}", "BADGES")
        }

        // Badges Compact Row
        Text("ACHIEVEMENT BADGES (${progress.earnedBadges.size}/${allBadges.size})", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp, fontWeight = FontWeight.Bold)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            allBadges.take(4).forEach { badge ->
                val isUnlocked = progress.earnedBadges.contains(badge.id)
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isUnlocked) CyanCyber.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant)
                        .border(1.dp, if (isUnlocked) CyanCyber else Color.Transparent, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        if (isUnlocked) Icons.Filled.EmojiEvents else Icons.Filled.Lock,
                        contentDescription = badge.title,
                        tint = if (isUnlocked) CyanCyber else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        // Settings Shortcut Button
        Button(
            onClick = onOpenSettings,
            modifier = Modifier.fillMaxWidth().height(44.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant, contentColor = MaterialTheme.colorScheme.onBackground)
        ) {
            Icon(Icons.Filled.Settings, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text("OPEN APP SETTINGS", fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
    }
}
