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
    isSample: Boolean = false,
    onUpdateDisplayName: ((String) -> Unit)? = null,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var activeName by remember { mutableStateOf(if (isSample) "Your Name" else displayName.ifEmpty { "Learner" }) }
    var isEditingName by remember { mutableStateOf(false) }

    val currentDate = remember { SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date()) }
    val certId = remember(activeName, courseTitle) {
        if (isSample) "CS-2026-SAMPLE" else "CS-2026-" + (activeName + courseTitle).hashCode().toString().takeLast(6).uppercase()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(
            title = if (isSample) "SAMPLE CERTIFICATE" else "OFFICIAL CREDENTIAL",
            subtitle = if (isSample) "Sample Preview Mode" else "Earned Achievement",
            onBack = onBack
        )

        if (isSample) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(YellowWarning.copy(alpha = 0.15f))
                    .border(1.dp, YellowWarning, RoundedCornerShape(12.dp))
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "★ SAMPLE PREVIEW - NOT A GENUINE CREDENTIAL ★",
                    color = YellowWarning,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                )
            }
        }

        // Certificate Card Container with Gold Accents
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier
                .fillMaxWidth()
                .border(2.5.dp, if (isSample) YellowWarning else YellowWarning, RoundedCornerShape(22.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFF132338), Color(0xFF0F1829), Color(0xFF15263A))
                        )
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Brush.linearGradient(listOf(CyanCyber, BlueCyber))),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.WorkspacePremium, contentDescription = null, tint = BackgroundCyber, modifier = Modifier.size(30.dp))
                }

                Text("CYBERSAATHI", color = CyanCyber, fontSize = 16.sp, fontWeight = FontWeight.Black, letterSpacing = 1.6.sp)
                Text("BY DC DEVELOPERS  •  LEARN SMART. STAY CYBER SAFE.", color = YellowWarning, fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)

                HorizontalDivider(color = CyanCyber.copy(alpha = 0.3f), thickness = 1.dp)

                Text("CERTIFICATE OF COMPLETION", color = MaterialTheme.colorScheme.onBackground, fontSize = 13.sp, fontWeight = FontWeight.Black, letterSpacing = 1.2.sp)
                Text("THIS IS PROUDLY PRESENTED TO", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)

                if (!isEditingName) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            activeName.uppercase(),
                            color = CyanCyber,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center
                        )
                        if (!isSample) {
                            IconButton(onClick = { isEditingName = true }, modifier = Modifier.size(24.dp)) {
                                Icon(Icons.Filled.Edit, contentDescription = "Edit Name", tint = CyanCyber, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        OutlinedTextField(
                            value = activeName,
                            onValueChange = { activeName = it },
                            singleLine = true,
                            modifier = Modifier.weight(1f).height(46.dp),
                            shape = RoundedCornerShape(10.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                        Button(
                            onClick = {
                                isEditingName = false
                                onUpdateDisplayName?.invoke(activeName)
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
                        ) {
                            Text("Save", fontSize = 11.sp)
                        }
                    }
                }

                Text("FOR SUCCESSFULLY MASTERING THE DEFENSIVE MODULE", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp, textAlign = TextAlign.Center)
                Text("\"$courseTitle\"", color = BlueCyber, fontSize = 14.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

                Text("Earned Skills: Threat Analysis • Risk Mitigation • Defensive Security", color = CyanCyber, fontSize = 9.sp, fontWeight = FontWeight.Bold)

                HorizontalDivider(color = CyanCyber.copy(alpha = 0.3f), thickness = 1.dp)

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text("ISSUE DATE", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                        Text(if (isSample) "SAMPLE DATE" else currentDate, color = YellowWarning, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("VERIFICATION ID", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                        Text(certId, color = YellowWarning, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Export & Save Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = {
                    val pdfFile = CertificateExporter.generateCertificatePdf(context, activeName, courseTitle, certId, isSample)
                    val savedUri = CertificateExporter.savePdfToPublicDownloads(context, pdfFile, certId)
                    if (savedUri != null) {
                        Toast.makeText(context, "Saved PDF to Downloads folder!", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Saved PDF in cache!", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.weight(1f).height(44.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Filled.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Text("SAVE PDF", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = {
                    val pdfFile = CertificateExporter.generateCertificatePdf(context, activeName, courseTitle, certId, isSample)
                    CertificateExporter.sharePdfCertificate(context, pdfFile)
                },
                modifier = Modifier.weight(1f).height(44.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = CyanCyber, contentColor = BackgroundCyber)
            ) {
                Icon(Icons.Filled.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Text("SHARE PDF", fontWeight = FontWeight.ExtraBold, fontSize = 11.sp)
            }
        }
    }
}
