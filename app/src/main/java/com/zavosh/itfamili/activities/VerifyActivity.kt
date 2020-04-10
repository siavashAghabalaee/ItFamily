package com.zavosh.itfamili.activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.zavosh.itfamili.R
import com.zavosh.itfamili.helper.Memory
import com.zavosh.itfamili.helper.PageManager
import com.zavosh.itfamili.helper.PublicMethods
import com.zavosh.itfamili.myviews.MyToast
import com.zavosh.itfamili.retrofit.Server
import com.zavosh.itfamili.retrofit.mymodels.Callback
import kotlinx.android.synthetic.main.activity_login.iv_bg
import kotlinx.android.synthetic.main.activity_verify.*

class VerifyActivity : AppCompatActivity() {

    companion object{
        fun getInstance(context: Context) : Intent {
            return Intent(context,VerifyActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        setup()

        listeners()
    }

    private fun listeners() {
        iv_bg?.setOnClickListener {
            PublicMethods.hideKeyboard(this@VerifyActivity)
        }

        iv_verify.setOnClickListener {
            var pass = etv_code?.text()
            if (!pass.isNullOrEmpty()){
                Server.getInstance(this@VerifyActivity).sendVerifyCode(Memory.loadTokenCode(),pass
                    ,PublicMethods.getDeviceId(this@VerifyActivity),PublicMethods.getDeviceString(),"Android",PublicMethods.getAndroidApi()
                    ,loader_verify,Callback.VerfyCode {
                        Memory.savePassword(pass)
                        Memory.saveToken(it.tokenId)
                        PageManager.getInstance().goRegisterActivity(this@VerifyActivity)
                    })
            }else{
                MyToast.showToast(this@VerifyActivity,getString(R.string.enter_code))
            }
        }
    }

    private fun setup() {
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
        //hide statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}
