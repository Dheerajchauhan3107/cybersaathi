package com.dcdevelopers.cybersaathi.ui.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dcdevelopers.cybersaathi.ui.components.TopBar
import com.dcdevelopers.cybersaathi.ui.theme.*
import com.dcdevelopers.cybersaathi.utils.CertificateExporter
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CertificateScreen(
    displayName: String,
    courseTitle: String,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val currentDate = remember { SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date()) }
    val certId = remember(displayName, courseTitle) { "CS-2026-" + (displayName + courseTitle).hashCode().toString().takeLast(6).uppercase() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(title = "OFFICIAL CERTIFICATE", subtitle = "Branded Credential", onBack = onBack)

        // Certificate Card Container
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, CyanCyber.copy(alpha = 0.6f), RoundedCornerShape(22.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFF132338), Color(0xFF0F1829), Color(0xFF15263A))
                        )
                    )
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape)
                        .background(Brush.linearGradient(listOf(CyanCyber, BlueCyber))),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.WorkspacePremium, contentDescription = null, tint = BackgroundCyber, modifier = Modifier.size(32.dp))
                }

                Text("CYBERSAATHI BY DC DEVELOPERS", color = MaterialTheme.colorScheme.onBackground, fontSize = 16.sp, fontWeight = FontWeight.Black, letterSpacing = 1.6.sp)
                HorizontalDivider(color = CyanCyber.copy(alpha = 0.3f), thickness = 1.dp)

                Text("CERTIFICATE OF COMPLETION", color = CyanCyber, fontSize = 12.sp, fontWeight = FontWeight.Black, letterSpacing = 1.2.sp)
                Text("This is proudly presented to", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp)
                Text(displayName.uppercase(), color = MaterialTheme.colorScheme.onBackground, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)

                Text("for passing the educational defensive module", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp, textAlign = TextAlign.Center)
                Text("\"$courseTitle\"", color = CyanCyber, fontSize = 15.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

                HorizontalDivider(color = CyanCyber.copy(alpha = 0.3f), thickness = 1.dp)

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text("DATE ISSUED", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                        Text(currentDate, color = MaterialTheme.colorScheme.onBackground, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("VERIFICATION ID", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                        Text(certId, color = CyanCyber, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Export / Share Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    val certFile = CertificateExporter.generateAndShareCertificate(context, displayName, courseTitle, certId)
                    if (certFile != null) {
                        CertificateExporter.shareCertificateFile(context, certFile)
                        Toast.makeText(context, "Certificate exported to cache!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.weight(1f).height(46.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
            ) {
                Icon(Icons.Filled.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(6.dp))
                Text("EXPORT & SHARE", fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
            }
        }
    }
}
