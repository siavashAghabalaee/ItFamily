package com.zavosh.itfamili.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zavosh.itfamili.R;
import com.zavosh.itfamili.helper.PageManager;
import com.zavosh.itfamili.helper.PublicMethods;
import com.zavosh.itfamili.myviews.MyImageView;
import com.zavosh.itfamili.myviews.MyTextView;
import com.zavosh.itfamili.myviews.MyToast;
import com.zavosh.itfamili.retrofit.mymodels.bloglistrequest.BlogListResult;
import com.zavosh.itfamili.retrofit.mymodels.homeRequest.BlogContent;
import com.zavosh.itfamili.retrofit.mymodels.homeRequest.Magzine;
import com.zavosh.itfamili.retrofit.mymodels.homeRequest.Podcast;
import com.zavosh.itfamili.retrofit.mymodels.homeRequest.SliderContent;
import com.zavosh.itfamili.retrofit.mymodels.homeRequest.Video;

import java.util.List;

public class HomeAdapters extends RecyclerView.Adapter {
    private Activity activity;
    private List<SliderContent> list;
    private List<Podcast> list_pod;
    private List<Magzine> list_mag;
    private Video video;
    private BlogContent blogContent;

    private final int TYPE_NEWS = 0;
    private final int TYPE_VIDEO = 1;
    private final int TYPE_POD = 2;
    private final int TYPE_BLOG = 3;
    private final int TYPE_TITLE = 4;
    private final int TYPE_MAGAZINES = 5;
    private final int TYPE_SPACe = 6;

    public HomeAdapters(Activity activity, List<SliderContent> list, Video video, List<Podcast> list_pod, BlogContent blogContent, List<Magzine> list_mag) {
        this.activity = activity;
        this.list = list;
        this.video = video;
        this.list_pod = list_pod;
        this.blogContent = blogContent;
        this.list_mag = list_mag;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_NEWS) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_news, viewGroup, false);
            return new NewsViewHolder(itemView);
        } else if (viewType == TYPE_VIDEO) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video, viewGroup, false);
            return new VideoViewHolder(itemView);
        } else if (viewType == TYPE_POD) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_pod, viewGroup, false);
            return new PodcastViewHolder(itemView);
        } else if (viewType == TYPE_BLOG) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_blog, viewGroup, false);
            return new BlogViewHolder(itemView);
        } else if (viewType == TYPE_TITLE) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_title, viewGroup, false);
            return new TitleViewHolder(itemView);
        } else if (viewType == TYPE_MAGAZINES) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_magazine, viewGroup, false);
            return new MagViewHolder(itemView);
        } else if (viewType == TYPE_SPACe) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_space, viewGroup, false);
            return new TitleViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof NewsViewHolder) {
            NewsViewHolder holder = (NewsViewHolder) viewHolder;
            LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true);
            AdapterHomeNews adapterHomeNews = new AdapterHomeNews(activity, list);
            holder.recyclerView.setLayoutManager(layoutManager);
            holder.recyclerView.setAdapter(adapterHomeNews);
        } else if (viewHolder instanceof VideoViewHolder) {
            VideoViewHolder holder = (VideoViewHolder) viewHolder;
            holder.iv_image.setPicasso(video.getImage(), activity);
            holder.tv_title.setText(video.getTitle());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("video_home", video);
                    PageManager.getInstance().goVideoDetailFragment(bundle);
                }
            });

        } else if (viewHolder instanceof PodcastViewHolder) {
            PodcastViewHolder holder = (PodcastViewHolder) viewHolder;
            LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, true);
            AdapterHomePodcast adapterHomeNews = new AdapterHomePodcast(activity, list_pod);
            holder.recyclerView.setLayoutManager(layoutManager);
            holder.recyclerView.setAdapter(adapterHomeNews);
        } else if (viewHolder instanceof BlogViewHolder) {
            try {
                BlogViewHolder holder = (BlogViewHolder) viewHolder;
                holder.iv_image.setPicasso(blogContent.getImage(), activity);
                holder.tv_title.setText(blogContent.getTitle());

            } catch (Exception e) {
            }

        } else if (viewHolder instanceof MagViewHolder) {
            try {
                int pos = position - 5;
                final Magzine magzine = list_mag.get(pos);
                MagViewHolder holder = (MagViewHolder) viewHolder;
                holder.iv_image.setPicasso(magzine.getImage(), activity);

                holder.tv_title.setText(magzine.getTitle());
                holder.tv_date.setText(magzine.getPublishDate());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PageManager.getInstance().goMagazineFragmentWithBackStack();
                    }
                });


                holder.ll_download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PublicMethods.openLink(magzine.getLinkAddress(),activity);
                    }
                });

                holder.ll_download1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PublicMethods.openLink(magzine.getLinkAddress(),activity);
                    }
                });

            } catch (Exception e) {
            }

        }


    }


    @Override
    public int getItemCount() {
        return 6 + list_mag.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_NEWS;
            case 1:
                return TYPE_VIDEO;
            case 2:
                return TYPE_POD;
            case 3:
                return TYPE_BLOG;
            case 4:
                return TYPE_TITLE;
            default:
                if (position == 5 + list_mag.size())
                    return TYPE_SPACe;
                else
                    return TYPE_MAGAZINES;
        }

    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerView_news);
        }
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        public MyImageView iv_image;
        public MyTextView tv_title;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }

    public class PodcastViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;

        public PodcastViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerView_news);
        }
    }

    public class BlogViewHolder extends RecyclerView.ViewHolder {
        public MyImageView iv_image;
        public MyTextView tv_title;

        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image_blog);
            tv_title = itemView.findViewById(R.id.tv_titleBlog);

            iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BlogListResult blog = new BlogListResult(blogContent.getId(),
                            blogContent.getTitle(), blogContent.getSummery(), blogContent.getImage(),
                            blogContent.getLinkeCount(),blogContent.getBody(),blogContent.getLinkAddress(),blogContent.getPublishDate(),blogContent.getContentSource(),
                            blogContent.getCommentCount(),blogContent.getIslike());
                    Bundle bundle=new Bundle();
                    bundle.putParcelable("blog_detail",blog);
                    PageManager.getInstance().goBlogsDetailsFragment(bundle);
                }
            });
        }
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder {
        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class MagViewHolder extends RecyclerView.ViewHolder {
        public MyImageView iv_image;
        MyTextView tv_title, tv_date;
        LinearLayout ll_download,ll_download1;

        public MagViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_date = itemView.findViewById(R.id.tv_date);
            ll_download = itemView.findViewById(R.id.ll_download);
            ll_download1 = itemView.findViewById(R.id.ll_download1);

        }
    }
}
