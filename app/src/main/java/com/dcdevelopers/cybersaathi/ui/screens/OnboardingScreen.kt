package com.dcdevelopers.cybersaathi.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dcdevelopers.cybersaathi.ui.theme.*

@Composable
fun OnboardingScreen(
    onComplete: (displayName: String) -> Unit
) {
    var nameInput by remember { mutableStateOf("") }
    var pledgeAccepted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF102033),
                        BackgroundCyber,
                        Color(0xFF090E19)
                    )
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .size(70.dp)
                .background(Brush.linearGradient(listOf(CyanCyber, BlueCyber)), RoundedCornerShape(22.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Filled.Shield,
                contentDescription = null,
                tint = BackgroundCyber,
                modifier = Modifier.size(42.dp)
            )
        }

        Text(
            "WELCOME TO CYBERSAATHI",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 22.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.8.sp,
            textAlign = TextAlign.Center
        )

        Text(
            "BY DC DEVELOPERS",
            color = CyanCyber,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.6.sp
        )

        Text(
            "Learn Smart. Stay Cyber Safe.",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp
        )

        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                FeatureRow(Icons.Filled.MenuBook, "Interactive Cyber Lessons", "Learn phishing, passwords, 2FA, UPI security & privacy.")
                FeatureRow(Icons.Filled.Science, "Safe Simulated Labs", "Practice identifying scam emails, bad URLs & weak passwords offline.")
                FeatureRow(Icons.Filled.EmojiEvents, "Certificates & Rewards", "Earn XP, unlock badges, and generate completion certificates.")
            }
        }

        OutlinedTextField(
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Enter Your Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = CyanCyber,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = pledgeAccepted,
                onCheckedChange = { pledgeAccepted = it },
                colors = CheckboxDefaults.colors(checkedColor = CyanCyber)
            )

            Spacer(Modifier.width(8.dp))

            Text(
                "I agree to use CyberSaathi strictly for legal, educational, defensive learning.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
                lineHeight = 16.sp
            )
        }

        Button(
            onClick = {
                val finalName = nameInput.trim().ifEmpty { "Learner" }
                onComplete(finalName)
            },
            enabled = pledgeAccepted,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = CyanCyber,
                contentColor = BackgroundCyber
            )
        ) {
            Text(
                "START LEARNING NOW",
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.sp
            )

            Spacer(Modifier.width(8.dp))

            Icon(Icons.Filled.ArrowForward, contentDescription = null)
        }
    }
}

@Composable
private fun FeatureRow(icon: ImageVector, title: String, desc: String) {
    Row(verticalAlignment = Alignment.Top) {
        Icon(icon, contentDescription = null, tint = CyanCyber, modifier = Modifier.size(24.dp))
        Spacer(Modifier.width(12.dp))
        Column {
            Text(title, color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(desc, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp, lineHeight = 17.sp)
        }
    }
}
