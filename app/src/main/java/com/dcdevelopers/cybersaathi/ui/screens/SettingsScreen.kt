package com.dcdevelopers.cybersaathi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dcdevelopers.cybersaathi.data.models.UserProgress
import com.dcdevelopers.cybersaathi.ui.components.TopBar
import com.dcdevelopers.cybersaathi.ui.theme.*

@Composable
fun SettingsScreen(
    progress: UserProgress,
    onSelectTheme: (String) -> Unit,
    onOpenPrivacyPolicy: () -> Unit,
    onOpenAbout: () -> Unit,
    onResetProgress: () -> Unit,
    onBack: () -> Unit
) {
    var showResetDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(title = "SETTINGS", subtitle = "App Preferences", onBack = onBack)

        // Theme Selector Group
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("APPEARANCE THEME", color = CyanCyber, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                ThemeOptionRow("Cyber Dark (Default)", "CYBER_DARK", progress.themePreference) { onSelectTheme("CYBER_DARK") }
                ThemeOptionRow("OLED Pitch Black", "OLED_BLACK", progress.themePreference) { onSelectTheme("OLED_BLACK") }
                ThemeOptionRow("Clean Light Mode", "LIGHT", progress.themePreference) { onSelectTheme("LIGHT") }
            }
        }

        // About & Legal Group
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                SettingsNavRow("About CyberSaathi & DC DEVELOPERS", Icons.Filled.Info, onOpenAbout)
                HorizontalDivider(color = MaterialTheme.colorScheme.background, thickness = 1.dp)
                SettingsNavRow("Privacy Policy & Local Security", Icons.Filled.PrivacyTip, onOpenPrivacyPolicy)
            }
        }

        // Data Management Group
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().clickable { showResetDialog = true }.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Delete, contentDescription = null, tint = RedError, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(10.dp))
                Column {
                    Text("Reset All Progress", color = RedError, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    Text("Clear lessons, quizzes, XP, and certificates.", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("CyberSaathi by DC DEVELOPERS", color = CyanCyber, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Text("Version 1.0.0 • Offline-First", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
        }

        if (showResetDialog) {
            AlertDialog(
                onDismissRequest = { showResetDialog = false },
                title = { Text("Reset Progress?") },
                text = { Text("Clear all learning progress, quizzes, and XP?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showResetDialog = false
                            onResetProgress()
                        }
                    ) {
                        Text("Reset Everything", color = RedError, fontWeight = FontWeight.Bold)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showResetDialog = false }) { Text("Cancel") }
                }
            )
        }
    }
}

@Composable
private fun ThemeOptionRow(name: String, key: String, currentTheme: String, onSelect: () -> Unit) {
    val isSelected = (currentTheme == key)
    Row(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onSelect).padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = isSelected, onClick = onSelect)
        Spacer(Modifier.width(6.dp))
        Text(name, color = MaterialTheme.colorScheme.onBackground, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun SettingsNavRow(title: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick).padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = CyanCyber, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(10.dp))
        Text(title, color = MaterialTheme.colorScheme.onBackground, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
        Icon(Icons.Filled.ArrowForward, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(16.dp))
    }
}
