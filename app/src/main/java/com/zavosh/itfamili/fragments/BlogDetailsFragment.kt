package com.zavosh.itfamili.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.zavosh.itfamili.R
import com.zavosh.itfamili.helper.PageManager
import com.zavosh.itfamili.helper.PublicMethods
import com.zavosh.itfamili.retrofit.Server
import com.zavosh.itfamili.retrofit.mymodels.bloglistrequest.BlogListResult
import com.zavosh.itfamili.retrofit.mymodels.grouprequest.GroupDetails
import com.zavosh.itfamili.retrofit.mymodels.homeRequest.SliderContent
import kotlinx.android.synthetic.main.fragment_blog_detail.view.*

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
        rootView = inflater.inflate(R.layout.fragment_blog_detail, container, false)
        setup()
        return rootView
    }

    private fun setup() {
        rootView.menu.setOnClickListener {
            activity?.findViewById<DrawerLayout>(R.id.mDrawerLayout)!!.openDrawer(GravityCompat.END)
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

        try {
            val blog_detail = bundle.getParcelable<GroupDetails>("blog_group")
            bindViewsFromGroup(blog_detail)
        } catch (e: Exception) {

        }


    }

    private fun bindViews(blog_detail: BlogListResult) {
        rootView.tv_magazine_title.text = blog_detail.title ?: ""
        rootView.tv_magazine_summery.text = blog_detail.body ?: ""
        rootView.tv_comments_count.text = (blog_detail.commentCount ?: "") + " نفر نظر داده اند"
        rootView.tv_likes_count.text = (blog_detail.linkeCount ?: "") + " نفر پسندیده اند "
        rootView.publish_date_txt.text = PublicMethods.getDate(blog_detail.publishDate)
        rootView.img_detail.setPicasso(blog_detail.image, activity)


        if (blog_detail.isLike.toBoolean()){
            rootView.iv_like?.setImageResource(R.mipmap.red_like)
        }else{
            rootView.iv_like?.setImageResource(R.drawable.like)
        }

        rootView.pdf_icon.setOnClickListener {

            PageManager.getInstance().goPdfViewerActivity(activity, blog_detail.linkAddress?:"")
        }

        rootView.iv_like?.setOnClickListener {
            context?.let {
                Server.getInstance(it).like(blog_detail.id , !blog_detail.isLike.toBoolean())
                if (!blog_detail.isLike.toBoolean()){
                    rootView.iv_like?.setImageResource(R.mipmap.red_like)
                    blog_detail.isLike = "true"
                }else{
                    rootView.iv_like?.setImageResource(R.drawable.like)
                    blog_detail.isLike = "false"
                }
            }

        }

        rootView.iv_comment.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("id",blog_detail.id)
            PageManager.getInstance().goCommentFragment(bundle)
        }

        rootView.iv_share.setOnClickListener {
            PublicMethods.share(blog_detail.title ?: "",blog_detail.linkAddress ?: "",context)
        }
    }

    private fun bindViewsFromGroup(blog_detail: GroupDetails) {
        rootView.tv_magazine_title.text = blog_detail.title ?: ""
        rootView.tv_magazine_summery.text = blog_detail.body ?: ""
        rootView.tv_comments_count.text = (blog_detail.commentCount ?: "") + " نفر نظر داده اند"
        rootView.tv_likes_count.text = (blog_detail.linkeCount ?: "") + " نفر پسندیده اند "
        rootView.publish_date_txt.text = PublicMethods.getDate(blog_detail.publishDate)
        rootView.img_detail.setPicasso(blog_detail.image, activity)


        if (blog_detail.isLike){
            rootView.iv_like?.setImageResource(R.mipmap.red_like)
        }else{
            rootView.iv_like?.setImageResource(R.drawable.like)
        }

        rootView.pdf_icon.setOnClickListener {

            PageManager.getInstance().goPdfViewerActivity(activity, blog_detail.linkAddress?:"")
        }

        rootView.iv_like?.setOnClickListener {
            context?.let {
                Server.getInstance(it).like(blog_detail.id , !blog_detail.isLike)
                if (!blog_detail.isLike){
                    rootView.iv_like?.setImageResource(R.mipmap.red_like)
                    blog_detail.isLike = true
                }else{
                    rootView.iv_like?.setImageResource(R.drawable.like)
                    blog_detail.isLike = false
                }
            }

        }

        rootView.iv_comment.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("id",blog_detail.id)
            PageManager.getInstance().goCommentFragment(bundle)
        }

        rootView.iv_share.setOnClickListener {
            PublicMethods.share(blog_detail.title ?: "",blog_detail.linkAddress ?: "",context)
        }
    }

    private fun bindViewsFromHome(blog_detail: SliderContent) {
        rootView.pdf_icon.setOnClickListener {

            PageManager.getInstance().goPdfViewerActivity(activity, blog_detail.linkAddress?:"")

        }

        rootView.tv_magazine_title.text = blog_detail.title ?: ""
        rootView.tv_magazine_summery.text = blog_detail.body ?: ""
        rootView.tv_comments_count.text = (blog_detail.commentCount ?: "") + " نفر نظر داده اند"
        rootView.tv_likes_count.text = (blog_detail.linkeCount ?: "") + " نفر پسندیده اند "
        rootView.publish_date_txt.text = PublicMethods.getDate(blog_detail.publishDate)
        rootView.img_detail.setPicasso(blog_detail.image, activity)

        if (blog_detail.isLike.toBoolean()){
            rootView.iv_like?.setImageResource(R.mipmap.red_like)
        }else{
            rootView.iv_like?.setImageResource(R.drawable.like)
        }

        rootView.iv_like?.setOnClickListener {
            context?.let {
                Server.getInstance(it).like(blog_detail.id , !blog_detail.isLike.toBoolean())
                if (!blog_detail.isLike.toBoolean()){
                    rootView.iv_like?.setImageResource(R.mipmap.red_like)
                    blog_detail.isLike = "true"
                }else{
                    rootView.iv_like?.setImageResource(R.drawable.like)
                    blog_detail.isLike = "false"
                }
            }
        }

        rootView.iv_comment.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("id",blog_detail.id)
            PageManager.getInstance().goCommentFragment(bundle)
        }

        rootView.iv_share.setOnClickListener {
            PublicMethods.share(blog_detail.title ?: "",blog_detail.linkAddress ?: "",context)
        }
    }

}