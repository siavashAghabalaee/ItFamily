package com.zavosh.itfamili.adapters

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.zavosh.itfamili.R
import com.zavosh.itfamili.helper.PageManager
import com.zavosh.itfamili.myviews.MyImageView
import com.zavosh.itfamili.myviews.MyTextView
import com.zavosh.itfamili.myviews.MyTextViewBold
import com.zavosh.itfamili.retrofit.mymodels.bloglistrequest.BlogListResult
import com.zavosh.itfamili.retrofit.mymodels.commentrequest.CommentResult
import java.lang.Boolean

class CommentsAdapter(var activity : Activity , var list : List<CommentResult>) : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = list!![position]
        holder.tv_from.text = comment.userFullName
        holder.tv_body.text = comment.comment
        holder.tv_time.text = comment.commentDate
    }

    override fun getItemCount(): Int {
        return list!!.size
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

}