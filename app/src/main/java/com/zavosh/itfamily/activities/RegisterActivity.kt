package com.zavosh.itfamily.activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.zavosh.itfamily.R
import com.zavosh.itfamily.helper.Memory
import com.zavosh.itfamily.helper.PageManager
import com.zavosh.itfamily.myviews.MyToast
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private var sex = ""


    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setup()
        listeners()
    }

    private fun listeners() {

        chk_male.setOnCheckedChangeListener { buttonView, isChecked ->
            sex = isChecked.toString()
            if (isChecked) {
                if (chk_female.isChecked) {
                    chk_female.isChecked = false
                }
            }
        }

        chk_female.setOnCheckedChangeListener { buttonView, isChecked ->
            sex = isChecked.toString()
            if (isChecked) {
                if (chk_male.isChecked) {
                    chk_male.isChecked = false
                }
            }
        }


        iv_register.setOnClickListener {

            if (etv_name.text.toString().isEmpty() || etv_mail.text.toString().isEmpty()) {
                MyToast.showToast(this@RegisterActivity, "لطفا تمام فیلدها را وارد کنید")
            } else if (!chk_male.isChecked && !chk_female.isChecked) {
                MyToast.showToast(this@RegisterActivity, "لطفا جنسیت را مشخص کنید")
            } else {
                sendRegisterRequest()
            }
        }
        tv_skip.setOnClickListener {
            PageManager.getInstance().goHomeActivity(this@RegisterActivity)
        }
    }

    private fun sendRegisterRequest() {
        var name = etv_name.text.toString().trim()
        var email =  etv_mail.text.toString().trim()
        Server.getInstance(this@RegisterActivity)
            .sendProfile(name,email,
                sex, register_loader,
                object : Callback.PostProfile {
                    override fun callback(result: String?) {
                        MyToast.showToast(this@RegisterActivity, result)
                        Memory.saveName(name)
                        Memory.saveEmail(email)
                        Memory.saveGender(sex.toBoolean())

                        PageManager.getInstance().goHomeActivity(this@RegisterActivity)
                    }
                }
            )
    }

    private fun setup() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
        //hide statusBar
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}
