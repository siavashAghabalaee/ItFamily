package com.zavosh.itfamili.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zavosh.itfamili.R;
import com.zavosh.itfamili.helper.PageManager;
import com.zavosh.itfamili.myviews.MyImageView;
import com.zavosh.itfamili.myviews.MyTextView;
import com.zavosh.itfamili.retrofit.mymodels.homeRequest.SliderContent;

import java.util.List;

public class AdapterHomeNews extends RecyclerView.Adapter{
    private Activity activity;
    private List<SliderContent> list;

    private final int TYPE_NEWS = 0;

    public AdapterHomeNews(Activity activity, List<SliderContent> list) {
        this.activity = activity;
        this.list = list;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_NEWS){
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news, viewGroup, false);
            return new NewsViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof NewsViewHolder){
            SliderContent sliderContent = list.get(position);
            NewsViewHolder holder = (NewsViewHolder) viewHolder;
            holder.imageView.setPicasso(sliderContent.getImage(),activity);
            holder.tv_title.setText(sliderContent.getTitle());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("blog_home", list.get(position));
                    PageManager.getInstance().goBlogsDetailsFragment(bundle);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_NEWS;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        MyImageView imageView;
        MyTextView tv_title;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_image);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
