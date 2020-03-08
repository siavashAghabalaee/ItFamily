package com.zavosh.itfamily.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zavosh.itfamily.R
import com.zavosh.itfamily.activities.HomeActivity
import com.zavosh.itfamily.adapters.VideoListAdapter
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import com.zavosh.itfamily.retrofit.mymodels.videolistrequest.VideoListResult
import kotlinx.android.synthetic.main.video_list_fragment.view.*

class VideoListFragment:Fragment() {

    private lateinit var rootview: View


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootview = inflater.inflate(R.layout.video_list_fragment, container, false)
        setup()
        return rootview
    }

    private fun setup() {

        rootview.menu.setOnClickListener {
            HomeActivity.drawer.openDrawer()
        }

        rootview.img_back.setOnClickListener {

            activity?.onBackPressed()
        }

        getVideoList()
    }

    private fun getVideoList() {
        Server.getInstance(activity).getVideoList(rootview.loader_video,
            object : Callback.VideoList {
                override fun callback(result: MutableList<VideoListResult>?) {


                    var adapter = VideoListAdapter(activity,result)
                    rootview.recyclerView_video.layoutManager = LinearLayoutManager(context)
                    rootview.recyclerView_video.adapter = adapter


                }
            })
    }

}