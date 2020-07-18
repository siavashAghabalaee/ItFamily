package com.zavosh.itfamily.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zavosh.itfamily.R;
import com.zavosh.itfamily.helper.PageManager;
import com.zavosh.itfamily.myviews.MyImageView;
import com.zavosh.itfamily.myviews.MyTextView;
import com.zavosh.itfamily.myviews.MyTextViewBold;
import com.zavosh.itfamily.retrofit.mymodels.videolistrequest.VideoListResult;

import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoViewHolder> {

    private Activity activity;
    private List<VideoListResult> list;

    public VideoListAdapter(Activity activity, List<VideoListResult> list) {
        this.activity = activity;
        this.list = list;
    }


    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_video_list, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {

        final VideoListResult videoItem = list.get(position);

        holder.iv_image.setPicasso(videoItem.getImage(),activity);
        holder.tv_title.setText(videoItem.getTitle());
        holder.tv_summery.setText(videoItem.getSummery());
        holder.tv_like_count.setText(videoItem.getLinkeCount());

        if (Boolean.parseBoolean(videoItem.getIsLike())){
            holder.iv_like.setImageResource(R.mipmap.red_like);
        }else {
            holder.iv_like.setImageResource(R.drawable.like);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putParcelable("video_detail",videoItem);
                PageManager.getInstance().goVideoDetailFragment(bundle);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        MyImageView iv_image;
        MyTextViewBold tv_title;
        MyTextView tv_summery;
        MyTextView tv_like_count;
        ImageView iv_like;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_image=itemView.findViewById(R.id.iv_image);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_summery=itemView.findViewById(R.id.tv_summery);
            tv_like_count=itemView.findViewById(R.id.tv_like_count);
            iv_like=itemView.findViewById(R.id.iv_like);


        }
    }
}
