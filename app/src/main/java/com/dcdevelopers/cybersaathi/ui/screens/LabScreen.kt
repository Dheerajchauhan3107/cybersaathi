package com.dcdevelopers.cybersaathi.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dcdevelopers.cybersaathi.data.models.Lab
import com.dcdevelopers.cybersaathi.data.models.LabType
import com.dcdevelopers.cybersaathi.ui.components.TopBar
import com.dcdevelopers.cybersaathi.ui.theme.*

@Composable
fun LabScreen(
    lab: Lab,
    onLabCompleted: (xpEarned: Int) -> Unit,
    onBack: () -> Unit
) {
    var isCompleted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopBar(title = lab.title, subtitle = "Practical Defensive Lab", onBack = onBack)

        // Lab Description Box
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Science, contentDescription = null, tint = CyanCyber)
                    Spacer(Modifier.width(8.dp))
                    Text(lab.category.uppercase(), color = CyanCyber, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.2.sp)
                    Spacer(Modifier.weight(1f))
                    Text("+${lab.xpReward} XP", color = GreenSuccess, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
                Text(lab.description, color = MaterialTheme.colorScheme.onBackground, fontSize = 14.sp, lineHeight = 20.sp)
            }
        }

        // Lab Interactive Scenario Content
        when (lab.type) {
            LabType.PHISHING_INSPECTOR -> PhishingInspectorLab { isCompleted = true }
            LabType.URL_ANALYZER -> UrlAnalyzerLab { isCompleted = true }
            LabType.PASSWORD_STRENGTH -> PasswordStrengthLab { isCompleted = true }
            LabType.PERMISSION_AUDITOR -> PermissionAuditorLab { isCompleted = true }
            LabType.UPI_SCAM_DEFENDER -> UpiScamLab { isCompleted = true }
            else -> PhishingInspectorLab { isCompleted = true }
        }

        if (isCompleted) {
            Button(
                onClick = { onLabCompleted(lab.xpReward) },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GreenSuccess, contentColor = BackgroundCyber)
            ) {
                Icon(Icons.Filled.CheckCircle, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("CLAIM +${lab.xpReward} XP & FINISH LAB", fontWeight = FontWeight.ExtraBold)
            }
        }
    }
}

@Composable
private fun PhishingInspectorLab(onSuccess: () -> Unit) {
    var spottedSender by remember { mutableStateOf(false) }
    var spottedUrgency by remember { mutableStateOf(false) }
    var spottedDomain by remember { mutableStateOf(false) }
    var verdictMade by remember { mutableStateOf(false) }

    val allSpotted = spottedSender && spottedUrgency && spottedDomain

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(20.dp))
    ) {
        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text("SIMULATED INCOMING EMAIL", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp, fontWeight = FontWeight.Bold)

            // Sender Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (spottedSender) RedError.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant)
                    .clickable { spottedSender = !spottedSender }
                    .padding(12.dp)
            ) {
                Column {
                    Text("From: State Bank Security <no-reply@sb1-kyc-verify.net>", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = if (spottedSender) RedError else MaterialTheme.colorScheme.onBackground)
                    if (spottedSender) Text("🚩 Red Flag: Fake domain (@sb1-kyc-verify.net is NOT sbi.co.in)", color = RedError, fontSize = 11.sp)
                }
            }

            // Subject / Urgency Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (spottedUrgency) RedError.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant)
                    .clickable { spottedUrgency = !spottedUrgency }
                    .padding(12.dp)
            ) {
                Column {
                    Text("Subject: URGENT! Account Suspension Notice in 2 Hours", fontWeight = FontWeight.Bold, fontSize = 13.sp, color = if (spottedUrgency) RedError else MaterialTheme.colorScheme.onBackground)
                    if (spottedUrgency) Text("🚩 Red Flag: Artificial urgency trigger designed to create panic", color = RedError, fontSize = 11.sp)
                }
            }

            // Link Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (spottedDomain) RedError.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant)
                    .clickable { spottedDomain = !spottedDomain }
                    .padding(12.dp)
            ) {
                Column {
                    Text("Body: Click here to update Pan & KYC details immediately: http://update-kyc-now.info/login", fontSize = 13.sp, color = if (spottedDomain) RedError else MaterialTheme.colorScheme.onBackground)
                    if (spottedDomain) Text("🚩 Red Flag: Unencrypted HTTP link pointing to suspicious .info domain", color = RedError, fontSize = 11.sp)
                }
            }

            Text("Tap on suspicious email areas above to flag red flags (${listOf(spottedSender, spottedUrgency, spottedDomain).count { it }}/3 spotted).", fontSize = 12.sp, color = CyanCyber)

            Button(
                onClick = {
                    verdictMade = true
                    onSuccess()
                },
                enabled = allSpotted,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
            ) {
                Text("FLAG AS PHISHING ATTACK", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun UrlAnalyzerLab(onSuccess: () -> Unit) {
    var targetChoice by remember { mutableStateOf<Int?>(null) }
    var submitted by remember { mutableStateOf(false) }

    val urls = listOf(
        "https://www.google.com/search" to true,
        "http://paypaI-login.com/account" to false,
        "http://192.168.0.50/bank/login" to false
    )

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(20.dp))
    ) {
        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("IDENTIFY MALICIOUS URL", color = CyanCyber, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text("Select the ONLY safe, legitimate web address below:", fontSize = 13.sp)

            urls.forEachIndexed { idx, (url, isSafe) ->
                val isSelected = (targetChoice == idx)
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { targetChoice = idx },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = if (isSelected) CyanCyber.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = isSelected, onClick = { targetChoice = idx })
                        Spacer(Modifier.width(8.dp))
                        Text(url, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            if (targetChoice != null) {
                Button(
                    onClick = {
                        if (targetChoice == 0) {
                            submitted = true
                            onSuccess()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
                ) {
                    Text("VERIFY URL CHOICE", fontWeight = FontWeight.Bold)
                }
            }

            if (submitted) {
                Text("✅ Correct! 'paypaI-login.com' used a capital 'I' instead of 'l' (typosquatting), and the IP address URL lacked SSL encryption.", color = GreenSuccess, fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun PasswordStrengthLab(onSuccess: () -> Unit) {
    var password by remember { mutableStateOf("") }

    val hasMinLength = password.length >= 14
    val hasUpper = password.any { it.isUpperCase() }
    val hasLower = password.any { it.isLowerCase() }
    val hasDigit = password.any { it.isDigit() }
    val hasSymbol = password.any { !it.isLetterOrDigit() }

    val score = listOf(hasMinLength, hasUpper, hasLower, hasDigit, hasSymbol).count { it }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(20.dp))
    ) {
        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("INTERACTIVE PASSWORD EVALUATOR", color = CyanCyber, fontSize = 12.sp, fontWeight = FontWeight.Bold)

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Type a test passphrase...") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            LinearProgressIndicator(
                progress = { score.toFloat() / 5f },
                color = if (score >= 4) GreenSuccess else if (score >= 2) YellowWarning else RedError,
                modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp))
            )

            Text("Strength: $score / 5 • Crack estimate: ${if (score == 5) "50,000+ Years" else if (score >= 3) "3 Months" else "Instant"}", fontWeight = FontWeight.Bold, fontSize = 13.sp)

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                CheckItem("14+ Characters Length", hasMinLength)
                CheckItem("Uppercase Letters (A-Z)", hasUpper)
                CheckItem("Lowercase Letters (a-z)", hasLower)
                CheckItem("Digits (0-9)", hasDigit)
                CheckItem("Special Symbols (!@#$)", hasSymbol)
            }

            if (score >= 4) {
                Button(
                    onClick = onSuccess,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenSuccess, contentColor = BackgroundCyber)
                ) {
                    Text("PASS LAB WITH STRONG PASSPHRASE", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun CheckItem(label: String, isOk: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(if (isOk) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked, contentDescription = null, tint = if (isOk) GreenSuccess else MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(16.dp))
        Spacer(Modifier.width(8.dp))
        Text(label, fontSize = 12.sp, color = if (isOk) GreenSuccess else MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun PermissionAuditorLab(onSuccess: () -> Unit) {
    var cameraRevoked by remember { mutableStateOf(false) }
    var contactsRevoked by remember { mutableStateOf(false) }
    var smsRevoked by remember { mutableStateOf(false) }

    val allRevoked = cameraRevoked && contactsRevoked && smsRevoked

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(20.dp))
    ) {
        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("APP PERMISSION SECURITY AUDITOR", color = CyanCyber, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text("App: 'SuperFlashlight 2026' is requesting risky permissions. Revoke all unnecessary privileges:", fontSize = 13.sp)

            PermRow("Camera Access", "Revoke access", cameraRevoked) { cameraRevoked = !cameraRevoked }
            PermRow("Contacts & Call Logs", "Revoke access", contactsRevoked) { contactsRevoked = !contactsRevoked }
            PermRow("SMS & OTP Reader", "Revoke access", smsRevoked) { smsRevoked = !smsRevoked }

            if (allRevoked) {
                Button(
                    onClick = onSuccess,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenSuccess, contentColor = BackgroundCyber)
                ) {
                    Text("SAVE AUDITED PRIVACY SETTINGS", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun PermRow(name: String, action: String, isRevoked: Boolean, onToggle: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(MaterialTheme.colorScheme.surfaceVariant).padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(name, fontSize = 13.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        TextButton(onClick = onToggle) {
            Text(if (isRevoked) "REVOKED ✅" else action, color = if (isRevoked) GreenSuccess else RedError, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
    }
}

@Composable
private fun UpiScamLab(onSuccess: () -> Unit) {
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var submitted by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(20.dp))
    ) {
        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("UPI FRAUD SCENARIO", color = CyanCyber, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text("A buyer on OLX sends a QR code claiming: 'Scan QR & enter PIN to receive ₹5,000 payment'. What should you do?", fontSize = 14.sp, fontWeight = FontWeight.Bold)

            val opts = listOf(
                "Scan the QR code and enter UPI PIN immediately",
                "Refuse! Explain that UPI PIN is NEVER required to receive money",
                "Ask for their password first"
            )

            opts.forEachIndexed { idx, txt ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { selectedOption = idx },
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = if (selectedOption == idx) CyanCyber.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = (selectedOption == idx), onClick = { selectedOption = idx })
                        Spacer(Modifier.width(8.dp))
                        Text(txt, fontSize = 13.sp)
                    }
                }
            }

            if (selectedOption != null) {
                Button(
                    onClick = {
                        if (selectedOption == 1) {
                            submitted = true
                            onSuccess()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
                ) {
                    Text("SUBMIT DEFENSIVE RESPONSE", fontWeight = FontWeight.Bold)
                }
            }

            if (submitted) {
                Text("✅ Excellent defense! Entering UPI PIN on QR codes ALWAYS deducts money from your account.", color = GreenSuccess, fontSize = 12.sp)
            }
        }
    }
}
