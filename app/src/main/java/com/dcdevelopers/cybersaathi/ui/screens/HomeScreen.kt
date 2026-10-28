package com.dcdevelopers.cybersaathi.ui.screens

import androidx.compose.animation.core.*
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dcdevelopers.cybersaathi.data.models.DailyTip
import com.dcdevelopers.cybersaathi.data.models.Lesson
import com.dcdevelopers.cybersaathi.data.models.UserProgress
import com.dcdevelopers.cybersaathi.ui.components.CyberHeader
import com.dcdevelopers.cybersaathi.ui.components.StatCard
import com.dcdevelopers.cybersaathi.ui.theme.*

@Composable
fun HomeScreen(
    progress: UserProgress,
    dailyTip: DailyTip,
    recommendedLesson: Lesson,
    onStartLearning: () -> Unit,
    onOpenLesson: (String) -> Unit,
    onOpenProfile: () -> Unit,
    onOpenSettings: () -> Unit
) {
    val transition = rememberInfiniteTransition(label = "cyber_animation")

    val pulse by transition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    val glow by transition.animateFloat(
        initialValue = 0.35f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top Header
        CyberHeader(onProfileClick = onOpenProfile)

        // Greeting
        Text(
            "Hello, ${progress.displayName} 👋",
            color = CyanCyber,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        // Single Viewport Hero Dashboard Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF153342), Color(0xFF111B30), Color(0xFF17213A))
                        )
                    )
                    .padding(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .scale(pulse)
                        .border(1.5.dp, CyanCyber.copy(alpha = glow), CircleShape)
                        .padding(8.dp)
                        .border(1.dp, BlueCyber.copy(alpha = glow), CircleShape)
                        .padding(8.dp)
                        .border(1.dp, CyanCyber.copy(alpha = glow), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    listOf(CyanCyber.copy(alpha = 0.3f), MaterialTheme.colorScheme.surface)
                                )
                            )
                            .border(1.dp, CyanCyber, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Filled.Security,
                            contentDescription = "Interactive security animation",
                            tint = CyanCyber,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

                Text(
                    "BUILD YOUR CYBER DEFENSES",
                    color = CyanCyber,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.2.sp
                )

                Text(
                    "Master phishing defense, passphrases, 2FA, UPI scam protection & smartphone privacy.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = onStartLearning,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CyanCyber,
                        contentColor = BackgroundCyber
                    )
                ) {
                    Icon(Icons.Filled.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(
                        if (progress.completedLessons.isNotEmpty()) "CONTINUE LEARNING" else "START LEARNING",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                        letterSpacing = 0.8.sp
                    )
                }
            }
        }

        // Stats Summary Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatCard(Modifier.weight(1f), Icons.Filled.Bolt, "${progress.xp} XP", "TOTAL XP")
            StatCard(Modifier.weight(1f), Icons.Filled.LocalFireDepartment, "${progress.streak} DAYS", "STREAK", iconTint = Color(0xFFFF9800))
            StatCard(Modifier.weight(1f), Icons.Filled.CheckCircle, "${progress.completedLessons.size}", "LESSONS")
            StatCard(Modifier.weight(1f), Icons.Filled.Star, "${progress.earnedBadges.size}", "BADGES")
        }

        // Daily Cyber Tip Card
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Lightbulb, contentDescription = null, tint = CyanCyber, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("DAILY TIP: ${dailyTip.title.uppercase()}", color = CyanCyber, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                Text(dailyTip.summary, color = MaterialTheme.colorScheme.onBackground, fontSize = 11.sp, lineHeight = 15.sp, maxLines = 2)
            }
        }

        // Recommended Next Lesson Row
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onOpenLesson(recommendedLesson.id) },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF1B3440)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.MenuBook, contentDescription = null, tint = CyanCyber, modifier = Modifier.size(20.dp))
                }

                Spacer(Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "NEXT: ${recommendedLesson.title}",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                    Text(
                        recommendedLesson.subtitle,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp,
                        maxLines = 1
                    )
                }

                Icon(Icons.Filled.ArrowForward, contentDescription = null, tint = CyanCyber, modifier = Modifier.size(18.dp))
            }
        }
    }
}
