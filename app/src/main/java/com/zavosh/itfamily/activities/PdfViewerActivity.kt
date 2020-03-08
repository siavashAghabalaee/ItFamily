package com.zavosh.itfamily.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zavosh.itfamily.R
import kotlinx.android.synthetic.main.activity_pdf_viewer.*

class PdfViewerActivity : AppCompatActivity() {


    companion object {

        private const val ARG_INTENT_PDF_LINK = "pdf_link"

        fun getInstance(context: Context, pdfLink: String): Intent {
            val intent = Intent(context, PdfViewerActivity::class.java)
            intent.putExtra(ARG_INTENT_PDF_LINK, pdfLink)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)


        val pdfLink = intent?.extras?.getString(ARG_INTENT_PDF_LINK)

        Log.i("log", "" + pdfLink)

        pdfView.fromUri(Uri.parse(pdfLink))
            .enableSwipe(true) // allows to block changing pages using swipe
            .swipeHorizontal(true)
            .enableDoubletap(true)
            .load()
    }
}
