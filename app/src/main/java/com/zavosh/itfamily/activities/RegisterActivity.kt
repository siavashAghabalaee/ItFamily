package com.zavosh.itfamily.activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.zavosh.itfamily.R
import com.zavosh.itfamily.helper.DialogSelector
import com.zavosh.itfamily.helper.Memory
import com.zavosh.itfamily.helper.PageManager
import com.zavosh.itfamily.helper.PublicMethods
import com.zavosh.itfamily.myviews.MyToast
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import com.zavosh.itfamily.retrofit.mymodels.basicdata.BasicDataResult
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(),DialogSelector.SelectorResponse {

    private var sex = ""
    private var isFirstBox = true
    private lateinit var data : BasicDataResult

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

        fullScreen?.setOnClickListener {
            PublicMethods.hideKeyboard(this@RegisterActivity)
        }
        iv_register.setOnClickListener {
            if (isFirstBox) {
                if (etv_name.text.toString().isEmpty() || etv_age.text.toString().isEmpty()) {
                    MyToast.showToast(this@RegisterActivity, "لطفا تمام فیلدها را وارد کنید")
                } else if (!chk_male.isChecked && !chk_female.isChecked) {
                    MyToast.showToast(this@RegisterActivity, "لطفا جنسیت را مشخص کنید")
                } else {
                    firstBox?.visibility = View.GONE
                    secondBox?.visibility = View.VISIBLE
                    isFirstBox = false
                }
            }else{
                sendRegisterRequest()
            }




        }
        tv_skip.setOnClickListener {
            PageManager.getInstance().goHomeActivity(this@RegisterActivity)
        }

        etv_age?.setOnClickListener{
            data?.let {
                DialogSelector.showDialog(this@RegisterActivity,it.ageRangeList,"age",this)
            }
        }
        etv_job?.setOnClickListener{
            data?.let {
                DialogSelector.showDialog(this@RegisterActivity,it.jobTypeList,"job",this)
            }
        }
        etv_education?.setOnClickListener{
            data?.let {
                DialogSelector.showDialog(this@RegisterActivity,it.educationRateList,"education",this)
            }
        }
    }

    private fun sendRegisterRequest() {
        var name = etv_name.text.toString().trim()
        var age = etv_age.text.toString().trim()
        var email =  etv_email.text.toString().trim()
        var education = etv_education.text()
        var job = etv_job.text()
        Server.getInstance(this@RegisterActivity)
            .sendProfile(name,email,
                sex,age,job,education, register_loader,
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
        Server.getInstance(this@RegisterActivity).getProfileBasicData(register_loader, Callback.BasicData {
            data = it
        })
    }

    override fun callback(result: Int, key: String?) {
        when {
            key.equals("age")->{
                etv_age?.setText(data.ageRangeList[result])
            }
            key.equals("job")->{
                etv_job?.setText(data.jobTypeList[result])
            }
            key.equals("education")->{
                etv_education?.setText(data.educationRateList[result])
            }
        }
    }
}
