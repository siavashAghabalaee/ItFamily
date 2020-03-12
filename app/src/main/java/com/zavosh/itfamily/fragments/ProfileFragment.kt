package com.zavosh.itfamily.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.zavosh.itfamily.R
import com.zavosh.itfamily.activities.HomeActivity
import com.zavosh.itfamily.helper.Memory
import com.zavosh.itfamily.myviews.MyToast
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    private lateinit var fragmentView: View

    private var sex = ""


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

        fragmentView.phone_register.setText(Memory.loadPhone() ?: "")


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

            if (fragmentView.name_register.text.isEmpty() || fragmentView.email_register.text.isEmpty()) {
                MyToast.showToast(activity, "لطفا نام و ایمیل خود را وارد کنید")
            }
           else if (!chk_male.isChecked && !chk_female.isChecked) {

                MyToast.showToast(context, "لطفا جنسیت را مشخص کنید")
            }
            else {
                getProfileData()
            }

        }


    }

    private fun getProfileData() {

        Server.getInstance(context).sendProfile(fragmentView.name_register.text.toString().trim(),
            fragmentView.email_register.text.toString().trim(),
            sex, loader,
            object : Callback.PostProfile {
                override fun callback(result: String?) {
                    MyToast.showToast(activity, result)
                }

            })
    }


}
