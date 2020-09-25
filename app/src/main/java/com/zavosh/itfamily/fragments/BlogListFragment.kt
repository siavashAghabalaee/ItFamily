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
import com.zavosh.itfamily.adapters.BlogListAdapter
import com.zavosh.itfamily.helper.PublicMethods
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import com.zavosh.itfamily.retrofit.mymodels.bloglistrequest.BlogListResult
import kotlinx.android.synthetic.main.fragment_blog_list.view.*
import java.util.stream.Collectors

class BlogListFragment:Fragment() {

    private lateinit var rootView: View
    private var list = ArrayList<BlogListResult>()
    private var holderList = ArrayList<BlogListResult>()
    private lateinit var adapter: BlogListAdapter

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
        adapter = BlogListAdapter(activity,list)
        rootView.img_menu.setOnClickListener {
            activity?.findViewById<DrawerLayout>(R.id.mDrawerLayout)!!.openDrawer(GravityCompat.END)
        }
        rootView.rl_box_b?.setOnClickListener {
            PublicMethods.hideKeyboard(activity)
        }
        rootView?.etv_search_blog?.addTextChangedListener(object : TextWatcher {
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

       Server.getInstance(activity).getBlogList(rootView.loader_blog_list, object : Callback.BlogList {
           override fun callback(result: MutableList<BlogListResult>?) {
               holderList.clear()
               for (item in result!!){
                   holderList.add(item)
               }
               list.clear()
               list.addAll(holderList)
               rootView.recyclerView_blog_list.layoutManager = LinearLayoutManager(context)
               rootView.recyclerView_blog_list.adapter = adapter

           }
       })

    }
}