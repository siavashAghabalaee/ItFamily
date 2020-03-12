package com.zavosh.itfamily.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zavosh.itfamily.R
import com.zavosh.itfamily.activities.HomeActivity
import com.zavosh.itfamily.adapters.PodcastAdapter
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import com.zavosh.itfamily.retrofit.mymodels.podcastlistrequest.PodcastListResult
import kotlinx.android.synthetic.main.fragment_podcasts.view.*

class PodCastFragment : Fragment() {

    private lateinit var rootView : View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView =inflater.inflate(R.layout.fragment_podcasts, container, false)
        setup()
        return rootView
    }

    private fun setup() {
        rootView.menu.setOnClickListener {
            activity?.findViewById<DrawerLayout>(R.id.mDrawerLayout)!!.openDrawer(GravityCompat.END)
        }

        rootView.img_back.setOnClickListener {

            activity?.onBackPressed()
        }

        getPodcasts()
    }

    private fun getPodcasts() {

        Server.getInstance(activity).getPodcastList(rootView.loader_podcast,
            object : Callback.PodcastList {
                override fun callback(result: MutableList<PodcastListResult>?) {

                    var adapter = PodcastAdapter(activity,result)
                    rootView.recyclerView_podcast.layoutManager = LinearLayoutManager(context)
                    rootView.recyclerView_podcast.adapter = adapter

                }
            })

    }


}