package com.zavosh.itfamili.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.zavosh.itfamili.R;
import com.zavosh.itfamili.helper.PageManager;
import com.zavosh.itfamili.myviews.MyImageView;
import com.zavosh.itfamili.myviews.MyTextView;
import com.zavosh.itfamili.myviews.MyTextViewBold;
import com.zavosh.itfamili.retrofit.mymodels.grouprequest.GroupDetails;

import java.util.ArrayList;

public class GroupDetailsAdapter extends RecyclerView.Adapter{

    private Activity activity;
    private ArrayList<GroupDetails> list;

    public GroupDetailsAdapter(Activity activity, ArrayList<GroupDetails> list) {
        this.activity = activity;
        this.list = list;
    }

    private final int type_video = 0;
    private final int type_podcast = 1;
    private final int type_blog = 2;
    private  int type_magazine = 3;
    private final int SPACE_TYPE = 4;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case type_video:
                return new VideoViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_group_video,parent,false)) ;
            case type_blog:
                return new BlogViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_group_blog,parent,false)) ;
            case type_podcast:
                return new PodcastViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_podcast_list,parent,false)) ;
            case SPACE_TYPE:
                return new SpaceViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_space, parent, false));

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        try {
            GroupDetails groupDetails = list.get(position);
            if (holder instanceof VideoViewHolder){
                VideoViewHolder videoViewHolder = (VideoViewHolder)holder;
                videoViewHolder.iv_image.setPicasso(groupDetails.getImage(),activity);
                videoViewHolder.tv_title.setText(groupDetails.getTitle());
                videoViewHolder.tv_summery.setText(groupDetails.getSummery());
            }

            if (holder instanceof BlogViewHolder){
                BlogViewHolder blogViewHolder = (BlogViewHolder)holder;
                blogViewHolder.iv_image.setPicasso(groupDetails.getImage(),activity);
                blogViewHolder.tv_title.setText(groupDetails.getTitle());
                blogViewHolder.tv_summery.setText(groupDetails.getSummery());
            }

            if (holder instanceof PodcastViewHolder){
                PodcastViewHolder podcastViewHolder = (PodcastViewHolder)holder;
                podcastViewHolder.iv_image.setPicasso(groupDetails.getImage(),activity);
                podcastViewHolder.tv_title.setText(groupDetails.getTitle());
            }
        }catch (Exception e){}


    }

    @Override
    public int getItemCount() {
        return list.size()+1 ;
    }

    @Override
    public int getItemViewType(int position) {
        if (position<list.size()) {
            GroupDetails item = list.get(position);
            switch (item.getType()) {
                case "podcast":
                    return type_podcast;
                case "video":
                    return type_video;
                case "blog":
                    return type_blog;
                default:
                    return 4;
            }
        }else
            return SPACE_TYPE;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        MyTextViewBold tv_title;
        MyTextView tv_summery;
        MyImageView iv_image;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title=itemView.findViewById(R.id.tv_title);
            tv_summery=itemView.findViewById(R.id.tv_summery);
            iv_image=itemView.findViewById(R.id.iv_image);
//
            iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GroupDetails groupDetails = list.get(getAdapterPosition());
                    Bundle bundle=new Bundle();
                    bundle.putParcelable("video_group",groupDetails);
                    PageManager.getInstance().goVideoDetailFragment(bundle);
                }
            });
        }
    }

    public class PodcastViewHolder extends RecyclerView.ViewHolder {

        MyTextView tv_title;
        MyImageView iv_image;
        ConstraintLayout cl_box;
        public PodcastViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title=itemView.findViewById(R.id.tv_podcast_title);
            iv_image=itemView.findViewById(R.id.iv_image);
            cl_box=itemView.findViewById(R.id.cl_box);
//
            cl_box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GroupDetails groupDetails = list.get(getAdapterPosition());
                    Bundle bundle=new Bundle();
                    bundle.putParcelable("podcast_group",groupDetails);
                    PageManager.getInstance().goPodcastDetailFragment(bundle);
                }
            });
        }
    }

    public class BlogViewHolder extends RecyclerView.ViewHolder {

        MyTextViewBold tv_title;
        MyImageView iv_image;
        MyTextView tv_summery;


        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title=itemView.findViewById(R.id.tv_title);
            tv_summery=itemView.findViewById(R.id.tv_summery);
            iv_image=itemView.findViewById(R.id.iv_image);

            iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GroupDetails groupDetails = list.get(getAdapterPosition());
                    Bundle bundle=new Bundle();
                    bundle.putParcelable("blog_group",groupDetails);
                    PageManager.getInstance().goBlogsDetailsFragment(bundle);
                }
            });

        }
    }

    public class SpaceViewHolder extends RecyclerView.ViewHolder {
        public SpaceViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
