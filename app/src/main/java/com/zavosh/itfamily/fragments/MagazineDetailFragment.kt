package com.zavosh.itfamily.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.zavosh.itfamily.R
import com.zavosh.itfamily.helper.PageManager
import com.zavosh.itfamily.helper.PublicMethods
import com.zavosh.itfamily.retrofit.mymodels.magazinerequest.MagazineResult
import kotlinx.android.synthetic.main.fragment_blog_detail.view.*


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
        rootView = inflater.inflate(R.layout.fragment_blog_detail, container, false)
        setup()
        return rootView
    }

    private fun setup() {

        rootView.img_back.setOnClickListener { activity?.onBackPressed() }
        rootView.menu.setOnClickListener {
            activity?.findViewById<DrawerLayout>(R.id.mDrawerLayout)!!.openDrawer(GravityCompat.END)
        }
        try {
            val magazine_detail = bundle.getParcelable<MagazineResult>("magazine_detail")
            bindViews(magazine_detail)
        } catch (e: Exception) {

        }



    }

    private fun bindViews(magazineDetail: MagazineResult) {

        rootView.iv_like.visibility = View.GONE
        rootView.iv_comment.visibility = View.GONE
        rootView.tv_magazine_title.text = magazineDetail.title ?: ""
        rootView.tv_magazine_summery.text = magazineDetail.summery ?: ""
//        rootView.tv_comments_count.text = (magazineDetail.commentCount ?: "") + " نفر نظر داده اند"
//        rootView.tv_likes_count.text = (magazineDetail.linkeCount ?: "") + " نفر پسندیده اند "
        rootView.publish_date_txt.text = PublicMethods.getDate(magazineDetail.publishDate)
        rootView.img_detail.setPicasso(magazineDetail.image, activity)


        rootView.pdf_icon.setOnClickListener {

            PageManager.getInstance().goPdfViewerActivity(activity,magazineDetail.linkAddress?:"")

        }
        rootView.iv_share.setOnClickListener {
            PublicMethods.share(magazineDetail.title,magazineDetail.linkAddress,context)
        }



        rootView.im_dl.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(magazineDetail.linkAddress)
            activity?.startActivity(i)
        }

    }

}