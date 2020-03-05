package com.zavosh.itfamily.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zavosh.itfamily.R;
import com.zavosh.itfamily.myviews.MyImageView;
import com.zavosh.itfamily.myviews.MyTextView;
import com.zavosh.itfamily.retrofit.mymodels.bloglistrequest.BlogListResult;

import java.util.List;

public class BlogListAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<BlogListResult> list;

    public BlogListAdapter(Activity activity, List<BlogListResult> list) {
        this.activity = activity;
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog_list, parent, false);
        return new BlogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        BlogListResult blogItem = list.get(position);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BlogViewHolder extends RecyclerView.ViewHolder {
        MyImageView imageView;
        MyTextView tv_title;
        RelativeLayout ly_item;

        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_image);
            tv_title = itemView.findViewById(R.id.tv_title);
            ly_item = itemView.findViewById(R.id.ly_item);
        }
    }
}
