package com.zavosh.itfamily.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zavosh.itfamily.R
import com.zavosh.itfamily.activities.HomeActivity
import com.zavosh.itfamily.helper.PageManager
import com.zavosh.itfamily.helper.PublicMethods
import com.zavosh.itfamily.retrofit.mymodels.bloglistrequest.BlogListResult
import com.zavosh.itfamily.retrofit.mymodels.homeRequest.SliderContent
import kotlinx.android.synthetic.main.fragment_magazine_detail.view.*

class BlogDetailsFragment : Fragment() {

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
        rootView.menu.setOnClickListener {
            HomeActivity.drawer.openDrawer()
        }

        rootView.img_back.setOnClickListener { activity?.onBackPressed() }

        try {
            val blog_detail = bundle.getParcelable<BlogListResult>("blog_detail")
            bindViews(blog_detail)
        } catch (e: Exception) {

        }

        try {
            val blog_detail = bundle.getParcelable<SliderContent>("blog_home")
            bindViewsFromHome(blog_detail)
        } catch (e: Exception) {

        }


    }

    private fun bindViews(blog_detail: BlogListResult) {

        rootView.tv_magazine_title.text = blog_detail.title ?: ""
        rootView.tv_magazine_summery.text = blog_detail.summery ?: ""
        rootView.tv_comments_count.text = (blog_detail.commentCount ?: "") + " نفر نظر داده اند"
        rootView.tv_likes_count.text = (blog_detail.linkeCount ?: "") + " نفر پسندیده اند "
        rootView.publish_date_txt.text = PublicMethods.getDate(blog_detail.publishDate)
        rootView.img_detail.setPicasso(blog_detail.image, activity)


        rootView.pdf_icon.setOnClickListener {

            PageManager.getInstance().goPdfViewerActivity(activity, blog_detail.linkAddress?:"")
        }

    }


    private fun bindViewsFromHome(blog_detail: SliderContent) {

        rootView.pdf_icon.setOnClickListener {

            PageManager.getInstance().goPdfViewerActivity(activity, blog_detail.linkAddress?:"")

        }



        rootView.tv_magazine_title.text = blog_detail.title ?: ""
        rootView.tv_magazine_summery.text = blog_detail.summery ?: ""
        rootView.tv_comments_count.text = (blog_detail.commentCount ?: "") + " نفر نظر داده اند"
        rootView.tv_likes_count.text = (blog_detail.linkeCount ?: "") + " نفر پسندیده اند "
        rootView.publish_date_txt.text = PublicMethods.getDate(blog_detail.publishDate)
        rootView.img_detail.setPicasso(blog_detail.image, activity)

    }


}