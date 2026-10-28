package com.dcdevelopers.cybersaathi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dcdevelopers.cybersaathi.ui.components.TopBar
import com.dcdevelopers.cybersaathi.ui.theme.*

@Composable
fun PrivacyPolicyScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(title = "PRIVACY POLICY", subtitle = "Data Handling & Offline Security", onBack = onBack)

        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth().weight(1f).padding(vertical = 10.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                PolicySection("1. Local-First & Zero Tracking", "CyberSaathi operates 100% offline. We do NOT collect, transmit, sell, or analyze any of your personal data, IP addresses, or learning statistics.")
                PolicySection("2. Stored Local Data", "The app saves your chosen display name, completed lessons, quiz scores, XP rewards, and theme preferences locally on your device using Android SharedPreferences.")
                PolicySection("3. No Credentials or Sensitive Data", "CyberSaathi NEVER asks for, stores, or processes real passwords, OTPs, credit card numbers, or UPI PINs. All interactive labs are offline simulations.")
                PolicySection("4. Zero Invasive Permissions", "CyberSaathi requires ZERO invasive permissions. It does not access your Camera, Contacts, SMS, Location, or Storage.")
                PolicySection("5. Reset & Data Erasure", "You can wipe all stored local data at any time from Settings > Reset All Progress.")
            }
        }
    }
}

@Composable
private fun PolicySection(title: String, body: String) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(title, color = CyanCyber, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        Text(body, color = MaterialTheme.colorScheme.onBackground, fontSize = 11.sp, lineHeight = 15.sp)
    }
}
