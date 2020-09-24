package com.zavosh.itfamily.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.zavosh.itfamily.R
import com.zavosh.itfamily.helper.DialogSelector
import com.zavosh.itfamily.helper.Memory
import com.zavosh.itfamily.myviews.MyToast
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import com.zavosh.itfamily.retrofit.mymodels.basicdata.BasicDataResult
import com.zavosh.itfamily.retrofit.mymodels.getprofileresult.GetProfileResult
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment(),DialogSelector.SelectorResponse  {

    private lateinit var fragmentView: View
    private lateinit var data : GetProfileResult
    private lateinit var baseData : BasicDataResult
    private var sex = ""
    private var isEditable = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_profile, container, false)
        setup()
        return fragmentView
    }

    private fun setup() {
        fragmentView.img_menu.setOnClickListener {
            activity?.findViewById<DrawerLayout>(R.id.mDrawerLayout)!!.openDrawer(GravityCompat.END)
        }

        fragmentView.iv_edit?.setOnClickListener {
            getBaseData()
        }

        fragmentView.chk_male.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked){
                sex=true.toString()
                if (chk_female.isChecked) {
                    chk_female.isChecked = false
                }
            }


        }

        fragmentView.chk_female.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked){
                sex=false.toString()
                if (chk_male.isChecked) {
                    chk_male.isChecked = false
                }
            }


        }

        fragmentView.btn_submit.setOnClickListener {

            if (fragmentView.name_register.text!!.isEmpty() || fragmentView.email_register.text!!.isEmpty()) {
                MyToast.showToast(activity, "لطفا نام و ایمیل خود را وارد کنید")
            }
           else if (!chk_male.isChecked && !chk_female.isChecked) {
                MyToast.showToast(context, "لطفا جنسیت را مشخص کنید")
            }
            else {
                sendProfileData()
            }
        }

        fragmentView.etv_job.setOnClickListener {
            baseData?.let {
                DialogSelector.showDialog(activity!!,it.jobTypeList,"job",this)
            }
        }
        fragmentView.etv_age.setOnClickListener {
            baseData?.let {
                DialogSelector.showDialog(activity!!,it.ageRangeList,"age",this)
            }
        }
        fragmentView.etv_education.setOnClickListener {
            baseData?.let {
                DialogSelector.showDialog(activity!!,it.educationRateList,"education",this)
            }
        }

        setEditable(isEditable)
        getProfile()
    }

    private fun getBaseData(){
        Server.getInstance(context).getProfileBasicData(fragmentView.profile_loader, Callback.BasicData {
            baseData = it
            isEditable = true
            setEditable(isEditable)
        })
    }

    private fun getProfile() {
        Server.getInstance(context).getProfile(fragmentView.profile_loader, Callback.GetProfile {
            fragmentView.rl_data_box?.visibility = View.VISIBLE
            data = it


            fragmentView.phone_register.setText(it.cellNumber)

            fragmentView.email_register.setText(it.email)

            fragmentView.name_register.setText(it.fullName)

            if (!it.isMale.isNullOrEmpty()) {
                sex = it.isMale
                if (it.isMale.toBoolean()) {
                    fragmentView.chk_female.isChecked = false
                    fragmentView.chk_male.isChecked = true
                } else {
                    fragmentView.chk_female.isChecked = true
                    fragmentView.chk_male.isChecked = false
                }
            }

            fragmentView.etv_age?.setText(it.ageRange)
            fragmentView.etv_job?.setText(it.job)
            fragmentView.etv_education?.setText(it.educationRate)
        })
    }

    private fun sendProfileData() {
        var name = fragmentView.name_register.text.toString().trim()
        var email = fragmentView.email_register.text.toString().trim()
        var age = fragmentView.etv_age.text.toString().trim()
        var job = fragmentView.etv_job.text.toString().trim()
        var education = fragmentView.etv_education.text.toString().trim()
        if (!name.isEmpty() && !age.isEmpty()) {
            Server.getInstance(context).sendProfile(name,
                email,
                sex, age, job, education, profile_loader,
                object : Callback.PostProfile {
                    override fun callback(result: String?) {
                        isEditable = false
                        setEditable(isEditable)
                        MyToast.showToast(activity, result)
                        Memory.saveName(name)
                        Memory.saveEmail(email)
                        Memory.saveGender(sex.toBoolean())
                    }

                })
        }else
            MyToast.showToast(context, "لطفا تمام فیلدها را وارد کنید")
    }

    private fun setEditable(isEditable :Boolean){
        //fragmentView.phone_register.isEnabled = isEditable
        fragmentView.email_register.isEnabled = isEditable
        fragmentView.name_register.isEnabled = isEditable
        fragmentView.etv_age.isEnabled = isEditable
        fragmentView.etv_job.isEnabled = isEditable
        fragmentView.etv_education.isEnabled = isEditable
        fragmentView.chk_female.isEnabled = isEditable
        fragmentView.chk_male.isEnabled = isEditable

        if (isEditable){
            fragmentView.btn_submit?.visibility = View.VISIBLE
            fragmentView.iv_edit?.visibility = View.GONE
        }else{
            fragmentView.btn_submit?.visibility = View.GONE
            fragmentView.iv_edit?.visibility = View.VISIBLE
        }
    }

    override fun callback(result: Int, key: String?) {
        when {
            key.equals("age")->{
                fragmentView?.etv_age?.setText(baseData.ageRangeList[result])
            }
            key.equals("job")->{
                fragmentView?.etv_job?.setText(baseData.jobTypeList[result])
            }
            key.equals("education")->{
                fragmentView?.etv_education?.setText(baseData.educationRateList[result])
            }
        }
    }

}
