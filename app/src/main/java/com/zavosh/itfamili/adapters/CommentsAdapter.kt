package com.zavosh.itfamili.adapters

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.zavosh.itfamili.R
import com.zavosh.itfamili.fragments.CommentFragment
import com.zavosh.itfamili.helper.PageManager
import com.zavosh.itfamili.interfaces.OnEndListListener
import com.zavosh.itfamili.myviews.MyImageView
import com.zavosh.itfamili.myviews.MyTextView
import com.zavosh.itfamili.myviews.MyTextViewBold
import com.zavosh.itfamili.retrofit.mymodels.bloglistrequest.BlogListResult
import com.zavosh.itfamili.retrofit.mymodels.commentrequest.CommentResult
import java.lang.Boolean

class CommentsAdapter(var activity : Activity , var list : List<CommentResult>,
                      var endListener : OnEndListListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var ITEM_TYPE = 1
    private var FOOTER_TYPE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == FOOTER_TYPE){
            val itemView: View =
                LayoutInflater.from(parent.context).inflate(R.layout.footer_loader, parent, false)
            return FooterViewHolder(itemView)
        }else {
            val itemView: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
            return CommentViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CommentViewHolder) {
            var holder = holder as CommentViewHolder
            val comment = list!![position]
            holder.tv_from.text = comment.userFullName
            holder.tv_body.text = comment.comment
            holder.tv_time.text = comment.commentDate

            if (position == list.size - 2)
                endListener.endList()
        }else{
            var holder = holder as FooterViewHolder
            if (CommentFragment.isExistNextPage)
                holder.ll_footer.visibility = View.VISIBLE
            else
                holder.ll_footer.visibility = View.GONE
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == list.size)
            return FOOTER_TYPE
        else
            return ITEM_TYPE

    }

    override fun getItemCount(): Int {
        return list!!.size+1
    }

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_from: MyTextViewBold
        var tv_body: MyTextView
        var tv_time: MyTextView

        init {
            tv_body = itemView.findViewById(R.id.tv_body)
            tv_from = itemView.findViewById(R.id.tv_from)
            tv_time = itemView.findViewById(R.id.tv_time)

        }
    }
    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ll_footer : LinearLayout
        init {
            ll_footer = itemView.findViewById(R.id.ll_footer)
        }
    }

}