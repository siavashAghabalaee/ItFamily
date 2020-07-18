package com.zavosh.itfamili.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zavosh.itfamili.R;
import com.zavosh.itfamili.helper.PageManager;
import com.zavosh.itfamili.myviews.MyTextView;
import com.zavosh.itfamili.retrofit.mymodels.contentlist.GroupItem;
import com.zavosh.itfamili.retrofit.mymodels.questionListlistrequest.QuestionListResult;

import java.util.List;

public class GroupListAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<GroupItem> list;

    private int ITEM_TYPE = 1;
    private int SPACE_TYPE = 2;

    public GroupListAdapter(Activity activity, List<GroupItem> list) {
        this.activity = activity;
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SPACE_TYPE) {
            return new SpaceViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_space, parent, false));
        } else {
            return new ItemViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_group, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            GroupItem item = list.get(position);
            itemViewHolder.tv_title.setText(item.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < list.size())
            return ITEM_TYPE;
        else
            return SPACE_TYPE;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        MyTextView tv_title;
        LinearLayout ll_item;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            ll_item = itemView.findViewById(R.id.ll_item);

            ll_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GroupItem groupItem = list.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putString("id", groupItem.getId());
                    PageManager.getInstance().goGroupDetailsFragment(bundle);
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
