package com.zavosh.itfamily.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zavosh.itfamily.R;
import com.zavosh.itfamily.helper.PageManager;
import com.zavosh.itfamily.myviews.MyImageView;
import com.zavosh.itfamily.myviews.MyTextView;
import com.zavosh.itfamily.myviews.MyTextViewBold;
import com.zavosh.itfamily.retrofit.mymodels.bloglistrequest.BlogListResult;

import java.util.List;

public class BlogListAdapter extends RecyclerView.Adapter<BlogListAdapter.BlogViewHolder> {

    private Activity activity;
    private List<BlogListResult> list;

    public BlogListAdapter(Activity activity, List<BlogListResult> list) {
        this.activity = activity;
        this.list = list;
    }


    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent, false);
        return new BlogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {

        final BlogListResult blogItem = list.get(position);

        holder.imageView.setPicasso(blogItem.getImage(), activity);
        holder.tv_title.setText(blogItem.getTitle());
        holder.tv_summery.setText(blogItem.getSummery());
        holder.tv_like_count.setText(blogItem.getLinkeCount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle=new Bundle();
                bundle.putParcelable("blog_detail",blogItem);
                PageManager.getInstance().goBlogsDetailsFragment(bundle);
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BlogViewHolder extends RecyclerView.ViewHolder {
        MyImageView imageView;
        MyTextViewBold tv_title;
        MyTextView tv_summery;
        MyTextView tv_like_count;
        RelativeLayout ly_item;

        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_image);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_summery = itemView.findViewById(R.id.tv_summery);
            ly_item = itemView.findViewById(R.id.ly_item);
            tv_like_count = itemView.findViewById(R.id.tv_like_count);
        }
    }
}
