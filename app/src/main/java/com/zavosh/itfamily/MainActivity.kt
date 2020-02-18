package com.zavosh.itfamily

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.zavosh.itfamily.helper.PageManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
        fake()

    }

    private fun setup() {
        //hide statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    private fun fake() {
        var handler = Handler()
        var runnable = Runnable {
            PageManager.getInstance().helper.goLoginActivity(this@MainActivity)
            finish()
        }
        handler.postDelayed(runnable,3000)
    }


}
