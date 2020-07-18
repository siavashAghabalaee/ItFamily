package com.zavosh.itfamily.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zavosh.itfamily.R;
import com.zavosh.itfamily.helper.PageManager;
import com.zavosh.itfamily.myviews.MyImageView;
import com.zavosh.itfamily.myviews.MyTextView;
import com.zavosh.itfamily.retrofit.mymodels.homeRequest.Podcast;

import java.util.List;

public class AdapterHomePodcast  extends RecyclerView.Adapter{
    private Activity activity;
    private List<Podcast> list;

    private final int TYPE_NEWS = 0;

    public AdapterHomePodcast(Activity activity, List<Podcast> list) {
        this.activity = activity;
        this.list = list;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_NEWS){
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_podcast, viewGroup, false);
            return new PodcastViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof PodcastViewHolder){
            final Podcast podcast = list.get(position);
            PodcastViewHolder holder = (PodcastViewHolder) viewHolder;
            holder.imageView.setPicasso(podcast.getImage(),activity);
            holder.tv_title.setText(podcast.getTitle());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("podcast_home", podcast);
                    PageManager.getInstance().goPodcastDetailFragment(bundle);
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

    public class PodcastViewHolder extends RecyclerView.ViewHolder{
        MyImageView imageView;
        MyTextView tv_title;
        public PodcastViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_image);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }
}
