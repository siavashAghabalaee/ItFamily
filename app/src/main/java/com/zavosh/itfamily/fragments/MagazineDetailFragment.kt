package com.zavosh.itfamily.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zavosh.itfamily.R

class MagazineDetailFragment : Fragment() {

    private lateinit var rootView: View
    var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bundle= arguments

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_magazine_detail, container, false)
        setup()
        return rootView
    }

    private fun setup() {

        bundle?.getString("getTitle")
        bundle?.getString("getLinkeCount")
        bundle?.getString("getLinkAddress")
        bundle?.getString("getCommentCount")
        bundle?.getString("getContentSource")
        bundle?.getString("getId")
        bundle?.getString("getImage")
        bundle?.getString("getPublishDate")

    }

}