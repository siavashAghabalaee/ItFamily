package com.zavosh.itfamily.activities

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zavosh.itfamily.R
import kotlinx.android.synthetic.main.activity_pdf_viewer.*

class PdfViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)


        val pdfLink = intent?.extras?.getString("pdf_link")

        Log.i("log",""+pdfLink)

        pdfView.fromUri(Uri.parse(pdfLink))
            .enableSwipe(true) // allows to block changing pages using swipe
            .swipeHorizontal(true)
            .enableDoubletap(true)
            .load()
    }
}
