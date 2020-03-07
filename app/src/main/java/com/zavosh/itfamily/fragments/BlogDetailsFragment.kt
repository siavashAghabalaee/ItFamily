package com.zavosh.itfamily.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zavosh.itfamily.R
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

        try {
            val blog_detail = bundle.getParcelable<BlogListResult>("blog_detail")
            bindViews(blog_detail)
        } catch (e: Exception) {

        }

        try {
            val blog_detail = bundle.getParcelable<SliderContent>("blog_home")
            bindViewsFromHome(blog_detail)
        }catch (e:Exception){

        }


    }

    private fun bindViews(blog_detail: BlogListResult) {
     /*   rootView.img_blog_detail.setPicasso(blog_detail.image ?: "", activity)
        rootView.blog_detail_title.text = blog_detail.title ?: ""
        rootView.blog_detail_summery.text = blog_detail.summery ?: ""
        rootView.blog_detail_comments.text = blog_detail.commentCount ?: ""
        rootView.link_address_blog1.text = blog_detail.linkAddress ?: ""
        rootView.link_address_blog2.text = blog_detail.linkAddress ?: ""
        rootView.link_address_blog3.text = blog_detail.linkAddress ?: ""*/


        rootView.tv_magazine_title.text = blog_detail.title ?: ""
        rootView.tv_magazine_summery.text = blog_detail.summery ?: ""
        rootView.tv_comments_count.text = (blog_detail.commentCount ?: "") + " نفر نظر داده اند"
        rootView.tv_likes_count.text = (blog_detail.linkeCount ?: "") + " نفر پسندیده اند "
        rootView.publish_date_txt.text = PublicMethods.getDate(blog_detail.publishDate)
        rootView.img_detail.setPicasso(blog_detail.image, activity)


        rootView.pdf_icon.setOnClickListener {  }

    }


    private fun bindViewsFromHome(blog_detail: SliderContent) {
       /* rootView.img_blog_detail.setPicasso(blog_detail.image ?: "", activity)
        rootView.blog_detail_title.text = blog_detail.title ?: ""
        rootView.blog_detail_summery.text = blog_detail.summery ?: ""
        rootView.blog_detail_comments.text = blog_detail.commentCount ?: ""
        rootView.link_address_blog1.text = blog_detail.linkAddress ?: ""
        rootView.link_address_blog2.text = blog_detail.linkAddress ?: ""
        rootView.link_address_blog3.text = blog_detail.linkAddress ?: ""*/

        rootView.pdf_icon.setOnClickListener {  }


        rootView.tv_magazine_title.text = blog_detail.title ?: ""
        rootView.tv_magazine_summery.text = blog_detail.summery ?: ""
        rootView.tv_comments_count.text = (blog_detail.commentCount ?: "") + " نفر نظر داده اند"
        rootView.tv_likes_count.text = (blog_detail.linkeCount ?: "") + " نفر پسندیده اند "
        rootView.publish_date_txt.text = PublicMethods.getDate(blog_detail.publishDate)
        rootView.img_detail.setPicasso(blog_detail.image, activity)

    }


}