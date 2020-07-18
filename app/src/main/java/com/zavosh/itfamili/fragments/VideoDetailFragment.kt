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
import com.zavosh.itfamili.retrofit.mymodels.grouprequest.GroupDetails
import com.zavosh.itfamili.retrofit.mymodels.homeRequest.Video
import com.zavosh.itfamili.retrofit.mymodels.videolistrequest.VideoListResult
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
            if (video_detail!=null)
        bindViews(video_detail)
        }catch (e:Exception){
            Log.i("sefssfsef","0 "+e.message)
        }

        try {

            val video_detail = bundle.getParcelable<Video>("video_home")
            if (video_detail!=null)
            bindViewsFromHome(video_detail)
        }catch (e:Exception){

        }

        try {
            val video_detail = bundle.getParcelable<GroupDetails>("video_group")
            if (video_detail!=null)
            bindViewsFromGroup(video_detail)
        }catch (e:Exception){
            Log.i("sefssfsef","0 "+e.message)
        }



    }

    private fun bindViews(video_detail: VideoListResult) {
        rootView.video_detail_title.text = video_detail.title ?: ""
        rootView.video_detail_summery.text = video_detail.body ?: ""
        rootView.video_detail_comments.text = (video_detail.commentCount ?: "") + " نفر نظر داده اند "
        rootView.tv_likes_count.text = (video_detail.commentCount ?: "") + " نفر پسندیده اند "
        rootView.img_video_detail.setPicasso(video_detail.image ?: "", activity)

        if (video_detail.isLike.toBoolean()){
            rootView.iv_like?.setImageResource(R.mipmap.red_like)
        }else{
            rootView.iv_like?.setImageResource(R.drawable.like)
        }
        rootView.ly_play_video.setOnClickListener {
            PageManager.getInstance().goVideoPlayerActivity(activity,video_detail.linkAddress)
        }
        rootView.iv_comment.setOnClickListener {
            var bundle = Bundle()
                bundle.putString("id",video_detail.id)
            PageManager.getInstance().goCommentFragment(bundle)
        }
        rootView.iv_like?.setOnClickListener {
            context?.let {
                Server.getInstance(it).like(video_detail.id , !video_detail.isLike.toBoolean())
                if (!video_detail.isLike.toBoolean()){
                    rootView.iv_like?.setImageResource(R.mipmap.red_like)
                    video_detail.isLike = "true"
                }else{
                    rootView.iv_like?.setImageResource(R.drawable.like)
                    video_detail.isLike = "false"
                }
            }

        }
        rootView.iv_share.setOnClickListener {
            PublicMethods.share(video_detail.title ?: "",video_detail.linkAddress ?: "",context)
        }

    }

    private fun bindViewsFromGroup(video_detail: GroupDetails) {
        rootView.video_detail_title.text = video_detail.title ?: ""
        rootView.video_detail_summery.text = video_detail.body ?: ""
        rootView.video_detail_comments.text = (video_detail.commentCount ?: "") + " نفر نظر داده اند "
        rootView.tv_likes_count.text = (video_detail.commentCount ?: "") + " نفر پسندیده اند "
        rootView.img_video_detail.setPicasso(video_detail.image ?: "", activity)

        if (video_detail.isLike){
            rootView.iv_like?.setImageResource(R.mipmap.red_like)
        }else{
            rootView.iv_like?.setImageResource(R.drawable.like)
        }
        rootView.ly_play_video.setOnClickListener {
            PageManager.getInstance().goVideoPlayerActivity(activity,video_detail.linkAddress)
        }
        rootView.iv_comment.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("id",video_detail.id)
            PageManager.getInstance().goCommentFragment(bundle)
        }
        rootView.iv_like?.setOnClickListener {
            context?.let {
                Server.getInstance(it).like(video_detail.id , !video_detail.isLike)
                if (!video_detail.isLike){
                    rootView.iv_like?.setImageResource(R.mipmap.red_like)
                    video_detail.isLike = true
                }else{
                    rootView.iv_like?.setImageResource(R.drawable.like)
                    video_detail.isLike = false
                }
            }

        }

        rootView.iv_share.setOnClickListener {
            PublicMethods.share(video_detail.title ?: "",video_detail.linkAddress ?: "",context)
        }

    }

    private fun bindViewsFromHome(video_detail: Video) {

        rootView.video_detail_title.text = video_detail.title ?: ""
        rootView.video_detail_summery.text = video_detail.summery ?: ""
        rootView.video_detail_comments.text = (video_detail.commentCount ?: "") + " نفر نظر داده اند "
        rootView.tv_likes_count.text = (video_detail.commentCount ?: "") + " نفر پسندیده اند "
        rootView.img_video_detail.setPicasso(video_detail.image ?: "", activity)

        if (video_detail.isLike.toBoolean()){
            rootView.iv_like?.setImageResource(R.mipmap.red_like)
        }else{
            rootView.iv_like?.setImageResource(R.drawable.like)
        }

        rootView.ly_play_video.setOnClickListener {
            PageManager.getInstance().goVideoPlayerActivity(activity,video_detail.linkAddress)
        }

        rootView.iv_like?.setOnClickListener {
            context?.let {
                Server.getInstance(it).like(video_detail.id , !video_detail.isLike.toBoolean())
                if (!video_detail.isLike.toBoolean()){
                    rootView.iv_like?.setImageResource(R.mipmap.red_like)
                    video_detail.isLike = "true"
                }else{
                    rootView.iv_like?.setImageResource(R.drawable.like)
                    video_detail.isLike = "false"
                }
            }

        }

        rootView.iv_comment.setOnClickListener {
            var bundle = Bundle()
                bundle.putString("id",video_detail.id)
            PageManager.getInstance().goCommentFragment(bundle)
        }

        rootView.iv_share.setOnClickListener {
            PublicMethods.share(video_detail.title ?: "",video_detail.linkAddress ?: "",context)
        }

    }


}