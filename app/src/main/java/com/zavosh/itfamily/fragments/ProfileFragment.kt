package com.zavosh.itfamily.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zavosh.itfamily.R
import com.zavosh.itfamily.retrofit.Server

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    private lateinit var fragmentView: View
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
        getProfileData()
    }

    private fun getProfileData() {
        Server.getInstance(context)
    }


}
