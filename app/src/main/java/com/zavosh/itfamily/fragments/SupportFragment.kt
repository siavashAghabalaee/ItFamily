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
import com.zavosh.itfamily.myviews.MyToast
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import kotlinx.android.synthetic.main.fragment_support.view.*

class SupportFragment : Fragment() {

    private lateinit var rootView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_support, container, false)
        setup()
        return rootView
    }

    private fun setup() {
        rootView.menu.setOnClickListener {
            activity?.findViewById<DrawerLayout>(R.id.mDrawerLayout)!!.openDrawer(GravityCompat.END)
        }

        rootView.img_back.setOnClickListener {

            activity?.onBackPressed()
        }


        rootView.submit_button.setOnClickListener {

            if (rootView.etv_message.text.toString().isEmpty() || rootView.etv_subject.text.toString().isEmpty()) {
                MyToast.showToast(context,"لطفا عنوان و توضیحات را وارد کنید")
            } else {
                sendSupport()
            }

        }

    }

    private fun sendSupport() {

        Server.getInstance(activity).postQuestion(
            rootView.etv_subject.text.toString(),
            rootView.etv_message.text.toString(),
            rootView.loader_support,
            object : Callback.PostQuestion {
                override fun callback(result: String?) {

                    MyToast.showToast(context,result)

                }
            })

    }


}