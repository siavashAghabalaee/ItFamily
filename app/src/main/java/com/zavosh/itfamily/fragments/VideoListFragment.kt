package com.zavosh.itfamily.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zavosh.itfamily.R
import com.zavosh.itfamily.adapters.VideoListAdapter
import com.zavosh.itfamily.helper.PublicMethods
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import com.zavosh.itfamily.retrofit.mymodels.videolistrequest.VideoListResult
import kotlinx.android.synthetic.main.video_list_fragment.view.*
import java.util.stream.Collectors

class VideoListFragment:Fragment() {

    private lateinit var rootview: View
    private var list = ArrayList<VideoListResult>()
    private var holderList = ArrayList<VideoListResult>()
    private lateinit var adapter: VideoListAdapter

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
        adapter = VideoListAdapter(activity,list)

        rootview.ll_box_v?.setOnClickListener {
            PublicMethods.hideKeyboard(activity)
        }
        rootview.menu.setOnClickListener {
            PublicMethods.hideKeyboard(activity)
            activity?.findViewById<DrawerLayout>(R.id.mDrawerLayout)!!.openDrawer(GravityCompat.END)
        }

        rootview.img_back.setOnClickListener {
            PublicMethods.hideKeyboard(activity)
            activity?.onBackPressed()
        }

        getVideoList()

        rootview?.etv_search?.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                val collect = holderList.stream().filter {
                    it.title.contains(s.toString())
                }.collect(Collectors.toList())

                list.clear()
                list.addAll(collect)
                adapter.notifyDataSetChanged()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    private fun getVideoList() {
        Server.getInstance(activity).getVideoList(rootview.loader_video,
            object : Callback.VideoList {
                override fun callback(result: MutableList<VideoListResult>?) {
                    holderList.clear()
                    for (item in result!!){
                        holderList.add(item)
                    }
                    list.clear()
                    list.addAll(holderList)
                    rootview.recyclerView_video.layoutManager = LinearLayoutManager(context)
                    rootview.recyclerView_video.adapter = adapter


                }
            })
    }

}