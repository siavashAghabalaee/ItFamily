package com.zavosh.itfamily.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zavosh.itfamily.R
import com.zavosh.itfamily.retrofit.mymodels.magazinerequest.MagazineResult
import kotlinx.android.synthetic.main.fragment_magazine_detail.view.*

class MagazineDetailFragment : Fragment() {

    private lateinit var rootView: View
    lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bundle = arguments!!

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

        try{
        val magazine_detail = bundle.getParcelable<MagazineResult>("magazine_detail")
        bindViews(magazine_detail)
        }catch (e:Exception){

        }

    }

    private fun bindViews(magazineDetail: MagazineResult) {

        rootView.tv_magazine_title.text = magazineDetail.title ?: ""
        rootView.tv_magazine_summery.text = magazineDetail.summery ?: ""
        rootView.tv_link_address.text = magazineDetail.linkAddress?: ""
        rootView.tv_link_address2.text = magazineDetail.linkAddress ?: ""
        rootView.tv_link_address3.text = magazineDetail.linkAddress?: ""
        rootView.tv_comments_count.text = magazineDetail.commentCount ?: ""

    }

}