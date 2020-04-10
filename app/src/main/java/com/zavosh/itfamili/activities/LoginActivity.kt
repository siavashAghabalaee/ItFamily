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
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    companion object{
        fun getInstanse(context: Context) : Intent{
            return Intent(context,LoginActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setup()
        listeners()
    }

    private fun listeners() {
        iv_login.setOnClickListener {

            PublicMethods.hideKeyboard(this@LoginActivity)
            if(!etv_phone.text().isNullOrEmpty()){
                sendNumberToServer(etv_phone.text())
            }else{
                MyToast.showToast(this@LoginActivity,getString(R.string.enter_phone))
            }
        }

        iv_bg.setOnClickListener {
            PublicMethods.hideKeyboard(this@LoginActivity)
        }
    }

    private fun sendNumberToServer(phone : String) {
        Server.getInstance(this).sendPhone(phone,loader,Callback.Register {
            Memory.savePhone(phone)
            Memory.saveTokenCode(it.tokenId)
            Memory.saveUserCode(it.userCode)
            PageManager.getInstance().helper.goVerifyActivity(this@LoginActivity)
        })
    }

    private fun setup() {
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
        //hide statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}
