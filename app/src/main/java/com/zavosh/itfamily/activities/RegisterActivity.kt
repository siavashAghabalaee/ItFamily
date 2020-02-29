package com.zavosh.itfamily.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.zavosh.itfamily.R
import com.zavosh.itfamily.helper.PageManager
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    companion object{
        fun getInstance(context: Context) : Intent {
            return Intent(context,RegisterActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setup()
        listeners()
    }

    private fun listeners() {
        iv_register.setOnClickListener {
            Toast.makeText(this@RegisterActivity,"به زودی", Toast.LENGTH_SHORT).show()
        }

        tv_skip.setOnClickListener {
            PageManager.getInstance().goHomeActivity(this@RegisterActivity)
        }
    }

    private fun setup() {
        //hide statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}
