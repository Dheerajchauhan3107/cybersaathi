package com.dcdevelopers.cybersaathi.utils

import android.content.Context
import android.content.Intent
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

object CertificateExporter {

    fun generateCertificatePdf(
        context: Context,
        displayName: String,
        courseTitle: String,
        certId: String
    ): File {
        val pdfDocument = PdfDocument()
        val width = 1200
        val height = 800
        val pageInfo = PdfDocument.PageInfo.Builder(width, height, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        // Background
        val bgPaint = Paint().apply { color = Color.parseColor("#070B14") }
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), bgPaint)

        // Outer Cyan Border
        val borderPaint = Paint().apply {
            color = Color.parseColor("#54F1E8")
            style = Paint.Style.STROKE
            strokeWidth = 12f
        }
        canvas.drawRect(30f, 30f, (width - 30).toFloat(), (height - 30).toFloat(), borderPaint)

        // Inner Blue Border
        val innerBorderPaint = Paint().apply {
            color = Color.parseColor("#629BFF")
            style = Paint.Style.STROKE
            strokeWidth = 4f
        }
        canvas.drawRect(45f, 45f, (width - 45).toFloat(), (height - 45).toFloat(), innerBorderPaint)

        // Brand
        val brandPaint = Paint().apply {
            color = Color.parseColor("#54F1E8")
            textSize = 32f
            isAntiAlias = true
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("CYBERSAATHI BY DC DEVELOPERS", width / 2f, 120f, brandPaint)

        // Certificate Title
        val titlePaint = Paint().apply {
            color = Color.parseColor("#F3F7FF")
            textSize = 48f
            isAntiAlias = true
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("CERTIFICATE OF COMPLETION", width / 2f, 210f, titlePaint)

        val subPaint = Paint().apply {
            color = Color.parseColor("#B7C5D9")
            textSize = 24f
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("THIS IS PROUDLY PRESENTED TO", width / 2f, 290f, subPaint)

        // Learner Name
        val namePaint = Paint().apply {
            color = Color.parseColor("#54F1E8")
            textSize = 64f
            isAntiAlias = true
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText(displayName.uppercase(), width / 2f, 390f, namePaint)

        canvas.drawText("FOR SUCCESSFULLY PASSING THE EDUCATIONAL DEFENSIVE MODULE", width / 2f, 470f, subPaint)

        // Course Title
        val coursePaint = Paint().apply {
            color = Color.parseColor("#629BFF")
            textSize = 40f
            isAntiAlias = true
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("\"$courseTitle\"", width / 2f, 540f, coursePaint)

        // Footer details
        val footerPaint = Paint().apply {
            color = Color.parseColor("#B7C5D9")
            textSize = 20f
            isAntiAlias = true
        }
        val dateStr = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date())
        canvas.drawText("DATE ISSUED: $dateStr", 80f, 700f, footerPaint)
        canvas.drawText("VERIFICATION ID: $certId", width - 420f, 700f, footerPaint)

        pdfDocument.finishPage(page)

        // Save PDF
        val certsDir = File(context.cacheDir, "certificates")
        certsDir.mkdirs()
        val pdfFile = File(certsDir, "CyberSaathi_Cert_${certId}.pdf")

        FileOutputStream(pdfFile).use { out ->
            pdfDocument.writeTo(out)
        }
        pdfDocument.close()

        return pdfFile
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
