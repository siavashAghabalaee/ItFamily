package com.zavosh.itfamily.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zavosh.itfamily.R
import com.zavosh.itfamily.activities.HomeActivity
import com.zavosh.itfamily.adapters.BlogListAdapter
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import com.zavosh.itfamily.retrofit.mymodels.bloglistrequest.BlogListResult
import kotlinx.android.synthetic.main.fragment_blog_list.view.*

class BlogListFragment:Fragment() {

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_blog_list, container, false)
        setup()
        return rootView
    }

    private fun setup() {
        rootView.img_menu.setOnClickListener {HomeActivity.drawer.openDrawer()}
       Server.getInstance(activity).getBlogList(rootView.loader_blog_list, object : Callback.BlogList {
           override fun callback(result: MutableList<BlogListResult>?) {

               var adapter = BlogListAdapter(activity,result)
               rootView.recyclerView_blog_list.layoutManager = LinearLayoutManager(context)
               rootView.recyclerView_blog_list.adapter = adapter

           }
       })

    }
}