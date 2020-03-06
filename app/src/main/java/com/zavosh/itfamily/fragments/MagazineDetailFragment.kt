package com.zavosh.itfamily.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zavosh.itfamily.R
import com.zavosh.itfamily.retrofit.mymodels.magazinerequest.MagazineResult
import kotlinx.android.synthetic.main.activity_parimagazin_details.view.*

class MagazineDetailFragment : Fragment() {

    private lateinit var rootView: View
    var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bundle = arguments

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_parimagazin_details, container, false)
        setup()
        return rootView
    }

    private fun setup() {

        val magazine_detail = bundle?.getParcelable<MagazineResult>("magazine_detail")

        bindViews(magazine_detail)



    }

    private fun bindViews(magazineDetail: MagazineResult?) {

        rootView.tv_magazine_title.text=magazineDetail?.title?:""
        rootView.tv_magazine_summery.text=if(magazineDetail?.summery.toString()==null) "" else magazineDetail?.summery.toString()
        rootView.tv_link_address.text=magazineDetail?.linkAddress.toString()?:""
        rootView.tv_link_address2.text=magazineDetail?.linkAddress.toString()?:""
        rootView.tv_link_address3.text=magazineDetail?.linkAddress.toString()?:""
        rootView.tv_comments_count.text=magazineDetail?.commentCount.toString()?:""


        Log.i("log",""+magazineDetail?.summery.toString())

    }

}