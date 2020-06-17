package com.zavosh.itfamili.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.zavosh.itfamili.R
import com.zavosh.itfamili.adapters.CommentsAdapter
import com.zavosh.itfamili.helper.PublicMethods
import com.zavosh.itfamili.interfaces.OnEndListListener
import com.zavosh.itfamili.retrofit.Server
import com.zavosh.itfamili.retrofit.mymodels.commentrequest.CommentResult
import kotlinx.android.synthetic.main.fragment_comment.view.*


class CommentFragment : Fragment() ,OnEndListListener{
    private var page = 1
    var isRequestEnd = false

    private lateinit var rootView: View
    private lateinit var adapter : CommentsAdapter
    private var list = ArrayList<CommentResult>()

    companion object{
        var isExistNextPage = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment'
        rootView = inflater.inflate(R.layout.fragment_comment, container, false)
        setup()
        listeners()
        return rootView
    }

    private fun listeners() {
        rootView.tv_send.setOnClickListener {
            PublicMethods.hideKeyboard(activity)
            if (rootView.etv_comment.text().trim().length!=0){
                val id = arguments?.getString("id")
                var comment = rootView.etv_comment.text().trim()
                rootView.etv_comment.setText("")
                Server.getInstance(context).sendComment(id,comment,{
                    list.clear()
                    page = 1
                    getComments(page)
                })
            }
        }
    }

    private fun setup() {
        rootView.img_menu.setOnClickListener {
            activity?.findViewById<DrawerLayout>(R.id.mDrawerLayout)!!.openDrawer(GravityCompat.END)}
        rootView.img_back.setOnClickListener {
            activity?.onBackPressed()
        }


        adapter = CommentsAdapter(activity!!,list,this)
        rootView.recyclerView_comment.layoutManager = LinearLayoutManager(context)
        rootView.recyclerView_comment.adapter = adapter

        getComments(page)
    }

    private fun getComments(page : Int) {
        isRequestEnd = false
        val id = arguments?.getString("id")
        Server.getInstance(context).getComments(id,page,{
            list.addAll(it)
            isRequestEnd = true

            if (it.size == 10){
                Log.i("aohdwuh","true")
                isExistNextPage = true
            }else{
                Log.i("aohdwuh","false")
                isExistNextPage = false
            }

            adapter.notifyDataSetChanged()
        })
    }

    override fun endList() {
        if (isRequestEnd && isExistNextPage){
            page++
            getComments(page)
        }
    }
}
