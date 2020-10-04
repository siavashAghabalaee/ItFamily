package com.zavosh.itfamily.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

import com.zavosh.itfamily.R
import kotlinx.android.synthetic.main.fragment_about_us.view.*

/**
 * A simple [Fragment] subclass.
 */
class AboutUsFragment : Fragment() {
    private lateinit var fragmentView : View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_about_us, container, false)
        setUp()
        return fragmentView
    }

    private fun setUp() {
        fragmentView.tv_about_us.setText(HomeFragment.aboutText)

        fragmentView.menu.setOnClickListener {
            activity?.findViewById<DrawerLayout>(R.id.mDrawerLayout)!!.openDrawer(GravityCompat.END)
        }

        fragmentView.img_back.setOnClickListener {

            activity?.onBackPressed()
        }
    }

}
