package com.zavosh.itfamily.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zavosh.itfamily.R;
import com.zavosh.itfamily.helper.PageManager;
import com.zavosh.itfamily.helper.PublicMethods;
import com.zavosh.itfamily.myviews.MyImageView;
import com.zavosh.itfamily.myviews.MyTextView;
import com.zavosh.itfamily.retrofit.mymodels.podcastlistrequest.PodcastListResult;

import java.util.List;

public class PodcastAdapter extends RecyclerView.Adapter<PodcastAdapter.PodcastViewHolder> {

    private Activity activity;
    private List<PodcastListResult> list;

    public PodcastAdapter(Activity activity, List<PodcastListResult> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public PodcastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PodcastViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_podcast_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PodcastViewHolder holder, int position) {

        final PodcastListResult podcastItem = list.get(position);
        holder.iv_image.setPicasso(podcastItem.getImage(), activity);
        holder.tv_podcast_title.setText(podcastItem.getTitle());
        holder.tv_date.setText(PublicMethods.getDate(podcastItem.getPublishDate()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putParcelable("podcast_detail", podcastItem);
                Log.i("siavashiii","linkeCount "+podcastItem.getLinkeCount());
                PageManager.getInstance().goPodcastDetailFragment(bundle);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PodcastViewHolder extends RecyclerView.ViewHolder {

        MyImageView iv_image;
        MyTextView tv_podcast_title;
        MyTextView tv_date;

        public PodcastViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_image = itemView.findViewById(R.id.iv_image);
            tv_podcast_title = itemView.findViewById(R.id.tv_podcast_title);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}
