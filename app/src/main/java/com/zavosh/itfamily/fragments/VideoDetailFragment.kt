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
import com.zavosh.itfamily.helper.PageManager
import com.zavosh.itfamily.retrofit.mymodels.homeRequest.Video
import com.zavosh.itfamily.retrofit.mymodels.videolistrequest.VideoListResult
import kotlinx.android.synthetic.main.fragment_video_detail.view.*

class VideoDetailFragment : Fragment() {


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
        rootView = inflater.inflate(R.layout.fragment_video_detail, container, false)
        setup()
        return rootView
    }

    private fun setup() {
        rootView.menu.setOnClickListener {
            activity?.findViewById<DrawerLayout>(R.id.mDrawerLayout)!!.openDrawer(GravityCompat.END)
        }

        rootView.img_back.setOnClickListener { activity?.onBackPressed() }

        try {

        val video_detail = bundle.getParcelable<VideoListResult>("video_detail")
        bindViews(video_detail)
        }catch (e:Exception){

        }

        try {

            val video_detail = bundle.getParcelable<Video>("video_home")
            bindViewsFromHome(video_detail)
        }catch (e:Exception){

        }



    }

    private fun bindViews(video_detail: VideoListResult) {

        rootView.video_detail_title.text = video_detail.title ?: ""
        rootView.video_detail_summery.text = video_detail.body ?: ""
        rootView.video_detail_comments.text = (video_detail.commentCount ?: "") + " نفر نظر داده اند "
        //rootView.tv_like.text = (video_detail.linkeCount ?: "") + " نفر پسندیده اند "
        //rootView.video_detail_address_link.text = video_detail.linkAddress ?: ""
        //rootView.video_detail_address_link2.text = video_detail.linkAddress ?: ""
        //rootView.video_detail_address_link3.text = video_detail.linkAddress ?: ""
        rootView.img_video_detail.setPicasso(video_detail.image ?: "", activity)



        rootView.ly_play_video.setOnClickListener {
            PageManager.getInstance().goVideoPlayerActivity(activity,video_detail.linkAddress)
        }


    }

    private fun bindViewsFromHome(video_detail: Video) {

        rootView.video_detail_title.text = video_detail.title ?: ""
        rootView.video_detail_summery.text = video_detail.summery ?: ""
        rootView.video_detail_comments.text = (video_detail.commentCount ?: "") + " نفر نظر داده اند "
        //rootView.tv_like.text = (video_detail.linkeCount ?: "") + " نفر پسندیده اند "
        //rootView.video_detail_address_link.text = video_detail.linkAddress ?: ""
        //rootView.video_detail_address_link2.text = video_detail.linkAddress ?: ""
        //rootView.video_detail_address_link3.text = video_detail.linkAddress ?: ""
        rootView.img_video_detail.setPicasso(video_detail.image ?: "", activity)



        rootView.ly_play_video.setOnClickListener {
            PageManager.getInstance().goVideoPlayerActivity(activity,video_detail.linkAddress)
        }


    }


}