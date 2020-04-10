package com.zavosh.itfamili.activities

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import com.zavosh.itfamili.R
import com.zavosh.itfamili.helper.Memory
import com.zavosh.itfamili.helper.PageManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
        fake()

    }

    private fun setup() {
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
        //hide statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    private fun fake() {
        var handler = Handler()
        var runnable = Runnable {
            Log.i("soefsfs",Memory.loadToken())
            if (Memory.loadToken().isNullOrEmpty())
                PageManager.getInstance().goLoginActivity(this@MainActivity)
            else
                PageManager.getInstance().goHomeActivity(this@MainActivity)
            finish()
        }
        handler.postDelayed(runnable,3000)
    }


}
