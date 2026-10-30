package com.dcdevelopers.cybersaathi.utils

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

object CertificateExporter {

    fun generateCertificatePdf(
        context: Context,
        displayName: String,
        courseTitle: String,
        certId: String,
        isSample: Boolean = false,
        skillsLearned: String = "Threat Analysis • Risk Mitigation • Defensive Security"
    ): File {
        val pdfDocument = PdfDocument()
        val width = 1200
        val height = 850
        val pageInfo = PdfDocument.PageInfo.Builder(width, height, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        drawCertificateOnCanvas(canvas, width, height, displayName, courseTitle, certId, isSample, skillsLearned)

        pdfDocument.finishPage(page)

        // Save PDF to cache
        val certsDir = File(context.cacheDir, "certificates")
        certsDir.mkdirs()
        val fileName = if (isSample) "CyberSaathi_Sample_Certificate.pdf" else "CyberSaathi_Cert_${certId}.pdf"
        val pdfFile = File(certsDir, fileName)

        FileOutputStream(pdfFile).use { out ->
            pdfDocument.writeTo(out)
        }
        pdfDocument.close()

        return pdfFile
    }

    private fun drawCertificateOnCanvas(
        canvas: Canvas,
        width: Int,
        height: Int,
        displayName: String,
        courseTitle: String,
        certId: String,
        isSample: Boolean,
        skillsLearned: String
    ) {
        // Dark Navy Background
        val bgPaint = Paint().apply { color = Color.parseColor("#070B14") }
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), bgPaint)

        // Outer Gold Border
        val goldBorderPaint = Paint().apply {
            color = Color.parseColor("#FFD166")
            style = Paint.Style.STROKE
            strokeWidth = 10f
            isAntiAlias = true
        }
        canvas.drawRect(25f, 25f, (width - 25).toFloat(), (height - 25).toFloat(), goldBorderPaint)

        // Inner Cyan Border
        val cyanBorderPaint = Paint().apply {
            color = Color.parseColor("#54F1E8")
            style = Paint.Style.STROKE
            strokeWidth = 4f
            isAntiAlias = true
        }
        canvas.drawRect(40f, 40f, (width - 40).toFloat(), (height - 40).toFloat(), cyanBorderPaint)

        // Inner Blue Accent Border
        val blueBorderPaint = Paint().apply {
            color = Color.parseColor("#629BFF")
            style = Paint.Style.STROKE
            strokeWidth = 2f
            isAntiAlias = true
        }
        canvas.drawRect(50f, 50f, (width - 50).toFloat(), (height - 50).toFloat(), blueBorderPaint)

        // Corner Gold Ornaments
        val cornerPaint = Paint().apply {
            color = Color.parseColor("#FFD166")
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        canvas.drawCircle(50f, 50f, 8f, cornerPaint)
        canvas.drawCircle((width - 50).toFloat(), 50f, 8f, cornerPaint)
        canvas.drawCircle(50f, (height - 50).toFloat(), 8f, cornerPaint)
        canvas.drawCircle((width - 50).toFloat(), (height - 50).toFloat(), 8f, cornerPaint)

        // Shield Logo Artwork
        val shieldPath = Path().apply {
            moveTo(width / 2f, 80f)
            lineTo((width / 2f) + 30f, 95f)
            lineTo((width / 2f) + 30f, 125f)
            cubicTo((width / 2f) + 30f, 140f, (width / 2f) + 15f, 155f, width / 2f, 160f)
            cubicTo((width / 2f) - 15f, 155f, (width / 2f) - 30f, 140f, (width / 2f) - 30f, 125f)
            lineTo((width / 2f) - 30f, 95f)
            close()
        }
        val shieldPaint = Paint().apply {
            color = Color.parseColor("#54F1E8")
            style = Paint.Style.STROKE
            strokeWidth = 3f
            isAntiAlias = true
        }
        canvas.drawPath(shieldPath, shieldPaint)

        // App & Brand Title
        val brandPaint = Paint().apply {
            color = Color.parseColor("#54F1E8")
            textSize = 30f
            isAntiAlias = true
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("CYBERSAATHI", width / 2f, 195f, brandPaint)

        val taglinePaint = Paint().apply {
            color = Color.parseColor("#FFD166")
            textSize = 16f
            isAntiAlias = true
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("BY DC DEVELOPERS  •  LEARN SMART. STAY CYBER SAFE.", width / 2f, 225f, taglinePaint)

        // Certificate Title
        val titlePaint = Paint().apply {
            color = Color.parseColor("#F3F7FF")
            textSize = 44f
            isAntiAlias = true
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("CERTIFICATE OF COMPLETION", width / 2f, 290f, titlePaint)

        if (isSample) {
            val sampleBadgePaint = Paint().apply {
                color = Color.parseColor("#FFD166")
                textSize = 18f
                isAntiAlias = true
                typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
                textAlign = Paint.Align.CENTER
            }
            canvas.drawText("★ SAMPLE PREVIEW - NOT A GENUINE CREDENTIAL ★", width / 2f, 325f, sampleBadgePaint)
        }

        val subPaint = Paint().apply {
            color = Color.parseColor("#B7C5D9")
            textSize = 22f
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("THIS IS PROUDLY PRESENTED TO", width / 2f, 375f, subPaint)

        // Learner Name
        val namePaint = Paint().apply {
            color = Color.parseColor("#54F1E8")
            textSize = 52f
            isAntiAlias = true
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText(displayName.uppercase(), width / 2f, 450f, namePaint)

        canvas.drawText("FOR SUCCESSFULLY MASTERING THE DEFENSIVE CURRICULUM OF", width / 2f, 510f, subPaint)

        // Course Title
        val coursePaint = Paint().apply {
            color = Color.parseColor("#629BFF")
            textSize = 36f
            isAntiAlias = true
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("\"$courseTitle\"", width / 2f, 570f, coursePaint)

        // Skills Learned Line
        val skillsPaint = Paint().apply {
            color = Color.parseColor("#54F1E8")
            textSize = 17f
            isAntiAlias = true
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("Earned Skills: $skillsLearned", width / 2f, 620f, skillsPaint)

        // Divider
        val divPaint = Paint().apply {
            color = Color.parseColor("#1F324D")
            strokeWidth = 2f
        }
        canvas.drawLine(100f, 660f, (width - 100).toFloat(), 660f, divPaint)

        // Footer Metadata
        val footerLabelPaint = Paint().apply {
            color = Color.parseColor("#B7C5D9")
            textSize = 16f
            isAntiAlias = true
        }
        val footerValPaint = Paint().apply {
            color = Color.parseColor("#FFD166")
            textSize = 18f
            isAntiAlias = true
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }

        val dateStr = if (isSample) "SAMPLE ISSUE DATE" else SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date())
        canvas.drawText("ISSUE DATE:", 100f, 715f, footerLabelPaint)
        canvas.drawText(dateStr, 100f, 745f, footerValPaint)

        val idStr = if (isSample) "CS-2026-SAMPLE" else certId
        footerLabelPaint.textAlign = Paint.Align.RIGHT
        footerValPaint.textAlign = Paint.Align.RIGHT
        canvas.drawText("VERIFICATION ID:", (width - 100).toFloat(), 715f, footerLabelPaint)
        canvas.drawText(idStr, (width - 100).toFloat(), 745f, footerValPaint)

        // Bottom Disclaimer
        val discPaint = Paint().apply {
            color = Color.parseColor("#B7C5D9")
            textSize = 13f
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("Issued by CyberSaathi App • DC DEVELOPERS • Educational Cybersecurity Credential", width / 2f, 800f, discPaint)
    }

    fun savePdfToPublicDownloads(context: Context, pdfFile: File, certId: String): Uri? {
        val fileName = "CyberSaathi_Certificate_${certId}.pdf"
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
            }
            val resolver = context.contentResolver
            val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
            if (uri != null) {
                resolver.openOutputStream(uri)?.use { out ->
                    pdfFile.inputStream().copyTo(out)
                }
            }
            uri
        } else {
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val outFile = File(downloadsDir, fileName)
            pdfFile.copyTo(outFile, overwrite = true)
            Uri.fromFile(outFile)
        }
    }

    fun sharePdfCertificate(context: Context, pdfFile: File) {
        val uri: Uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            pdfFile
        )
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "application/pdf"
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share CyberSaathi PDF Certificate"))
    }
}
