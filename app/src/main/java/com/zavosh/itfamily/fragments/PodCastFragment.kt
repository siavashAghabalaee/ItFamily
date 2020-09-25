package com.zavosh.itfamily.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zavosh.itfamily.R
import com.zavosh.itfamily.adapters.PodcastAdapter
import com.zavosh.itfamily.helper.PublicMethods
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import com.zavosh.itfamily.retrofit.mymodels.podcastlistrequest.PodcastListResult
import kotlinx.android.synthetic.main.fragment_podcasts.view.*
import kotlinx.android.synthetic.main.fragment_podcasts.view.img_back
import kotlinx.android.synthetic.main.fragment_podcasts.view.menu
import kotlinx.android.synthetic.main.video_list_fragment.view.*
import java.util.stream.Collectors

class PodCastFragment : Fragment() {

    private lateinit var rootView : View
    private var list = ArrayList<PodcastListResult>()
    private var holderList = ArrayList<PodcastListResult>()
    private lateinit var adapter: PodcastAdapter
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
        adapter = PodcastAdapter(activity,list)
        rootView.menu.setOnClickListener {
            activity?.findViewById<DrawerLayout>(R.id.mDrawerLayout)!!.openDrawer(GravityCompat.END)
        }

        rootView.img_back.setOnClickListener {

            activity?.onBackPressed()
        }

        rootView.ll_box_p?.setOnClickListener {
            PublicMethods.hideKeyboard(activity)
        }

        rootView?.etv_search_podcast?.addTextChangedListener(object : TextWatcher {
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

        getPodcasts()
    }

    private fun getPodcasts() {

        Server.getInstance(activity).getPodcastList(rootView.loader_podcast,
            object : Callback.PodcastList {
                override fun callback(result: MutableList<PodcastListResult>?) {
                    holderList.clear()
                    for (item in result!!){
                        holderList.add(item)
                    }
                    list.clear()
                    list.addAll(holderList)

                    rootView.recyclerView_podcast.layoutManager = LinearLayoutManager(context)
                    rootView.recyclerView_podcast.adapter = adapter

                }
            })

    }


}