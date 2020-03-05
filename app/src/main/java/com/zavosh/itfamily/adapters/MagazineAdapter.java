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
import com.zavosh.itfamily.retrofit.mymodels.magazinerequest.MagazineResult;

import java.util.List;

public class MagazineAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<MagazineResult> list;

    public MagazineAdapter(Activity activity, List<MagazineResult> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_magazine_list, parent, false);
        return new MagazineViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {

        if (viewHolder instanceof MagazineViewHolder) {
            MagazineResult sliderContent = list.get(position);
            MagazineViewHolder holder = (MagazineViewHolder) viewHolder;
            holder.imageView.setPicasso(sliderContent.getImage(), activity);
            holder.tv_title.setText(sliderContent.getTitle());

            holder.ly_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("getTitle", list.get(position).getTitle());
//                    bundle.putString("getSummery", list.get(position).getSummery().toString());
                    bundle.putString("getLinkeCount", list.get(position).getLinkeCount());
                    bundle.putString("getLinkAddress", list.get(position).getLinkAddress());
                    bundle.putString("getCommentCount", list.get(position).getCommentCount());
                    bundle.putString("getContentSource", list.get(position).getContentSource());
                    bundle.putString("getId", list.get(position).getId());
                    bundle.putString("getImage", list.get(position).getImage());
                    bundle.putString("getPublishDate", list.get(position).getPublishDate());
                    PageManager.getInstance().goMagazineDetailFragment(bundle);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MagazineViewHolder extends RecyclerView.ViewHolder {
        MyImageView imageView;
        MyTextView tv_title;
        RelativeLayout ly_item;

        public MagazineViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_image);
            tv_title = itemView.findViewById(R.id.tv_title);
            ly_item = itemView.findViewById(R.id.ly_item);
        }
    }
}
