package com.dcdevelopers.cybersaathi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(title = lab.title, subtitle = "Practical Defensive Lab", onBack = onBack)

        // Lab Description Box
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Science, contentDescription = null, tint = CyanCyber, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(lab.category.uppercase(), color = CyanCyber, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.2.sp)
                    Spacer(Modifier.weight(1f))
                    Text("+${lab.xpReward} XP", color = GreenSuccess, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
                Text(lab.description, color = MaterialTheme.colorScheme.onBackground, fontSize = 12.sp, lineHeight = 16.sp)
            }
        }

        // Lab Interactive Scenario Content
        Box(modifier = Modifier.weight(1f, fill = false).padding(vertical = 8.dp)) {
            when (lab.type) {
                LabType.PHISHING_INSPECTOR -> PhishingInspectorLab { isCompleted = true }
                LabType.URL_ANALYZER -> UrlAnalyzerLab { isCompleted = true }
                LabType.PASSWORD_STRENGTH -> PasswordStrengthLab { isCompleted = true }
                LabType.PERMISSION_AUDITOR -> PermissionAuditorLab { isCompleted = true }
                LabType.UPI_SCAM_DEFENDER -> UpiScamLab { isCompleted = true }
                LabType.LOG_INVESTIGATOR -> LogInvestigatorLab { isCompleted = true }
                LabType.SQL_INJECTION_DEFENDER -> SqlInjectionLab { isCompleted = true }
                LabType.XSS_DEFENDER -> XssDefenderLab { isCompleted = true }
            }
        }

        if (isCompleted) {
            Button(
                onClick = { onLabCompleted(lab.xpReward) },
                modifier = Modifier.fillMaxWidth().height(46.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GreenSuccess, contentColor = BackgroundCyber)
            ) {
                Icon(Icons.Filled.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(6.dp))
                Text("CLAIM +${lab.xpReward} XP & FINISH LAB", fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun LogInvestigatorLab(onSuccess: () -> Unit) {
    var selectedLogIndex by remember { mutableStateOf<Int?>(null) }
    var submitted by remember { mutableStateOf(false) }

    val logs = listOf(
        "192.168.1.10 - - [28/Oct/2026:10:00:01] \"GET /index.html HTTP/1.1\" 200",
        "10.0.0.45 - - [28/Oct/2026:10:00:05] \"GET /login?user=admin' OR '1'='1 HTTP/1.1\" 200",
        "192.168.1.12 - - [28/Oct/2026:10:00:10] \"GET /about HTTP/1.1\" 200"
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("NETWORK LOG INVESTIGATION", color = CyanCyber, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Text("Select the log line indicating a SQL Injection attack attempt:", fontSize = 12.sp)

            logs.forEachIndexed { idx, log ->
                val isSelected = (selectedLogIndex == idx)
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { selectedLogIndex = idx },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = if (isSelected) CyanCyber.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = isSelected, onClick = { selectedLogIndex = idx })
                        Spacer(Modifier.width(6.dp))
                        Text(log, fontSize = 10.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
                    }
                }
            }

            if (selectedLogIndex != null) {
                Button(
                    onClick = {
                        if (selectedLogIndex == 1) {
                            submitted = true
                            onSuccess()
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(42.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
                ) {
                    Text("VERIFY LOG ANALYSIS", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }

            if (submitted) {
                Text("✅ Correct! 'user=admin' OR '1'='1' attempts to bypass authentication using SQL Injection logic.", color = GreenSuccess, fontSize = 11.sp)
            }
        }
    }
}

@Composable
private fun SqlInjectionLab(onSuccess: () -> Unit) {
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var submitted by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("SQL INJECTION DEFENSE", color = CyanCyber, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Text("Select the most secure backend implementation against SQL Injection:", fontSize = 12.sp)

            val options = listOf(
                "Dynamic SQL string concatenation: 'SELECT * FROM users WHERE name = ' + input",
                "Parameterized Query / Prepared Statement: 'SELECT * FROM users WHERE name = ?'",
                "Clearing spaces in user input string"
            )

            options.forEachIndexed { idx, opt ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { selectedOption = idx },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = if (selectedOption == idx) CyanCyber.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = (selectedOption == idx), onClick = { selectedOption = idx })
                        Spacer(Modifier.width(6.dp))
                        Text(opt, fontSize = 11.sp, modifier = Modifier.weight(1f))
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
                    modifier = Modifier.fillMaxWidth().height(42.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
                ) {
                    Text("VERIFY DEFENSIVE CODE", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }

            if (submitted) {
                Text("✅ Correct! Prepared statements isolate parameter data from executable SQL commands.", color = GreenSuccess, fontSize = 11.sp)
            }
        }
    }
}

@Composable
private fun XssDefenderLab(onSuccess: () -> Unit) {
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var submitted by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("XSS DEFENSE CHALLENGE", color = CyanCyber, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Text("Which practice neutralizes malicious script payloads rendered in user comments?", fontSize = 12.sp)

            val options = listOf(
                "Directly inserting innerHTML without escaping",
                "Context-aware HTML Entity Output Encoding (e.g. converting '<' to '&lt;')",
                "Increasing font size"
            )

            options.forEachIndexed { idx, opt ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { selectedOption = idx },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = if (selectedOption == idx) CyanCyber.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = (selectedOption == idx), onClick = { selectedOption = idx })
                        Spacer(Modifier.width(6.dp))
                        Text(opt, fontSize = 11.sp, modifier = Modifier.weight(1f))
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
                    modifier = Modifier.fillMaxWidth().height(42.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
                ) {
                    Text("VERIFY XSS DEFENSE", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }

            if (submitted) {
                Text("✅ Correct! HTML Entity Encoding prevents browsers from interpreting input as executable script tags.", color = GreenSuccess, fontSize = 11.sp)
            }
        }
    }
}

@Composable
private fun PhishingInspectorLab(onSuccess: () -> Unit) {
    var spottedSender by remember { mutableStateOf(false) }
    var spottedUrgency by remember { mutableStateOf(false) }
    var spottedDomain by remember { mutableStateOf(false) }

    val allSpotted = spottedSender && spottedUrgency && spottedDomain

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("SIMULATED INCOMING EMAIL", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp, fontWeight = FontWeight.Bold)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (spottedSender) RedError.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant)
                    .clickable { spottedSender = !spottedSender }
                    .padding(10.dp)
            ) {
                Column {
                    Text("From: State Bank Security <no-reply@sb1-kyc-verify.net>", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = if (spottedSender) RedError else MaterialTheme.colorScheme.onBackground)
                    if (spottedSender) Text("🚩 Red Flag: Fake domain (@sb1-kyc-verify.net is NOT sbi.co.in)", color = RedError, fontSize = 10.sp)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (spottedUrgency) RedError.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant)
                    .clickable { spottedUrgency = !spottedUrgency }
                    .padding(10.dp)
            ) {
                Column {
                    Text("Subject: URGENT! Account Suspension Notice in 2 Hours", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = if (spottedUrgency) RedError else MaterialTheme.colorScheme.onBackground)
                    if (spottedUrgency) Text("🚩 Red Flag: Artificial urgency trigger designed to create panic", color = RedError, fontSize = 10.sp)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (spottedDomain) RedError.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant)
                    .clickable { spottedDomain = !spottedDomain }
                    .padding(10.dp)
            ) {
                Column {
                    Text("Body: Click here to update Pan & KYC details immediately: http://update-kyc-now.info/login", fontSize = 12.sp, color = if (spottedDomain) RedError else MaterialTheme.colorScheme.onBackground)
                    if (spottedDomain) Text("🚩 Red Flag: Unencrypted HTTP link pointing to suspicious .info domain", color = RedError, fontSize = 10.sp)
                }
            }

            Button(
                onClick = onSuccess,
                enabled = allSpotted,
                modifier = Modifier.fillMaxWidth().height(42.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
            ) {
                Text("FLAG AS PHISHING ATTACK", fontWeight = FontWeight.Bold, fontSize = 12.sp)
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
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("IDENTIFY MALICIOUS URL", color = CyanCyber, fontSize = 11.sp, fontWeight = FontWeight.Bold)

            urls.forEachIndexed { idx, (url, _) ->
                val isSelected = (targetChoice == idx)
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { targetChoice = idx },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = if (isSelected) CyanCyber.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = isSelected, onClick = { targetChoice = idx })
                        Spacer(Modifier.width(6.dp))
                        Text(url, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
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
                    modifier = Modifier.fillMaxWidth().height(42.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
                ) {
                    Text("VERIFY URL CHOICE", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }

            if (submitted) {
                Text("✅ Correct! 'paypaI-login.com' used typosquatting and the IP address URL lacked SSL.", color = GreenSuccess, fontSize = 11.sp)
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
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("INTERACTIVE PASSWORD EVALUATOR", color = CyanCyber, fontSize = 11.sp, fontWeight = FontWeight.Bold)

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Type a test passphrase...") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().height(52.dp)
            )

            LinearProgressIndicator(
                progress = { score.toFloat() / 5f },
                color = if (score >= 4) GreenSuccess else if (score >= 2) YellowWarning else RedError,
                modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp))
            )

            Text("Strength: $score / 5 • Crack estimate: ${if (score == 5) "50,000+ Years" else "Instant/Hours"}", fontWeight = FontWeight.Bold, fontSize = 11.sp)

            if (score >= 4) {
                Button(
                    onClick = onSuccess,
                    modifier = Modifier.fillMaxWidth().height(42.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenSuccess, contentColor = BackgroundCyber)
                ) {
                    Text("PASS LAB WITH STRONG PASSPHRASE", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
private fun PermissionAuditorLab(onSuccess: () -> Unit) {
    var cameraRevoked by remember { mutableStateOf(false) }
    var contactsRevoked by remember { mutableStateOf(false) }
    var smsRevoked by remember { mutableStateOf(false) }

    val allRevoked = cameraRevoked && contactsRevoked && smsRevoked

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("APP PERMISSION SECURITY AUDITOR", color = CyanCyber, fontSize = 11.sp, fontWeight = FontWeight.Bold)

            PermRow("Camera Access", "Revoke access", cameraRevoked) { cameraRevoked = !cameraRevoked }
            PermRow("Contacts & Call Logs", "Revoke access", contactsRevoked) { contactsRevoked = !contactsRevoked }
            PermRow("SMS & OTP Reader", "Revoke access", smsRevoked) { smsRevoked = !smsRevoked }

            if (allRevoked) {
                Button(
                    onClick = onSuccess,
                    modifier = Modifier.fillMaxWidth().height(42.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenSuccess, contentColor = BackgroundCyber)
                ) {
                    Text("SAVE AUDITED PRIVACY SETTINGS", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
private fun PermRow(name: String, action: String, isRevoked: Boolean, onToggle: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(MaterialTheme.colorScheme.surfaceVariant).padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(name, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        TextButton(onClick = onToggle) {
            Text(if (isRevoked) "REVOKED ✅" else action, color = if (isRevoked) GreenSuccess else RedError, fontWeight = FontWeight.Bold, fontSize = 11.sp)
        }
    }
}

@Composable
private fun UpiScamLab(onSuccess: () -> Unit) {
    var selectedOption by remember { mutableStateOf<Int?>(null) }
    var submitted by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.fillMaxWidth().border(1.dp, MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(16.dp))
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("UPI FRAUD SCENARIO", color = CyanCyber, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Text("A buyer on OLX sends a QR code claiming 'Scan QR & enter PIN to receive ₹5,000 payment'. What should you do?", fontSize = 12.sp, fontWeight = FontWeight.Bold)

            val opts = listOf(
                "Scan the QR code and enter UPI PIN immediately",
                "Refuse! Explain that UPI PIN is NEVER required to receive money",
                "Ask for their password first"
            )

            opts.forEachIndexed { idx, txt ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { selectedOption = idx },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = if (selectedOption == idx) CyanCyber.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = (selectedOption == idx), onClick = { selectedOption = idx })
                        Spacer(Modifier.width(6.dp))
                        Text(txt, fontSize = 11.sp)
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
                    modifier = Modifier.fillMaxWidth().height(42.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
                ) {
                    Text("SUBMIT DEFENSIVE RESPONSE", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }

            if (submitted) {
                Text("✅ Excellent defense! Entering UPI PIN on QR codes ALWAYS deducts money from your account.", color = GreenSuccess, fontSize = 11.sp)
            }
        }
    }
}
