package com.zavosh.itfamily.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jean.jcplayer.model.JcAudio
import com.zavosh.itfamily.R
import com.zavosh.itfamily.retrofit.mymodels.podcastlistrequest.PodcastListResult
import kotlinx.android.synthetic.main.fragment_pod_detail.view.*
import java.lang.Exception


class PodcastDetailFragment: Fragment() {


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
        rootView = inflater.inflate(R.layout.fragment_pod_detail, container, false)
        setup()
        return rootView
    }

    private fun setup() {

        val podcastDetail = bundle?.getParcelable<PodcastListResult>("podcast_detail")

        bindViews(podcastDetail)

    }

    private fun bindViews(podcastDetail: PodcastListResult?) {

        rootView.tv_likes_count.text=(podcastDetail?.linkeCount?:"")+" نفر پسندیده اند "
        rootView.tv_comments.text= (podcastDetail?.commentCount?:"")+" نفر نظر داده اند "
        rootView.tv_title.text=podcastDetail?.title?:""
        rootView.tv_summery.text=podcastDetail?.summery?:""
        rootView.profile_avatar.setPicasso(podcastDetail?.image,activity)
        //rootView.link_address.text=podcastDetail?.linkAddress?:""
        rootView.link_address2.text=podcastDetail?.publishDate?:""
        //rootView.link_address3.text=podcastDetail?.linkAddress?:""


        /*rootView.img_play.setOnClickListener {

            Log.i("log","link : "+podcastDetail?.linkAddress)

        }*/
        val jcAudios: ArrayList<JcAudio> = ArrayList()
        jcAudios.add(JcAudio.createFromURL("",podcastDetail?.linkAddress!!))

        rootView.jcplayer.initPlaylist(jcAudios)

    }

    override fun onPause() {
        super.onPause()
        rootView.jcplayer.kill()

        //rootView.paus
    }
}