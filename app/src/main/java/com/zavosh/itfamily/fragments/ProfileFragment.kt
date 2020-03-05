package com.zavosh.itfamily.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zavosh.itfamily.R
import com.zavosh.itfamily.helper.Memory
import com.zavosh.itfamily.myviews.MyToast
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    private lateinit var fragmentView: View

    private var sex: Boolean? = null


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

        phone_register.setText(Memory.loadPhone() ?: "")

        if (name_register.text.isEmpty() || email_register.text.isEmpty()) {
            MyToast.showToast(activity, "لطفا نام و ایمیل خود را وارد کنید")
        } else {
            if (chk_female.isChecked) {
                sex = false
            } else if (chk_male.isChecked) {
                sex = true
            }
            getProfileData()
        }
    }

    private fun getProfileData() {


        Server.getInstance(context).sendProfile(name_register.text.toString().trim(),
            email_register.text.toString().trim(),
            sex.toString(), loader,
            object : Callback.PostProfile {
                override fun callback(result: String?) {


                }

            })
    }


}
