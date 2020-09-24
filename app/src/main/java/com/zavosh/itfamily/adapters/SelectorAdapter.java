package com.zavosh.itfamily.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zavosh.itfamily.R;
import com.zavosh.itfamily.myviews.MyTextView;

import java.util.ArrayList;

public class SelectorAdapter extends ArrayAdapter {
    private ArrayList<String> list;
    private Context context;
    public SelectorAdapter(@NonNull Context context, @NonNull ArrayList<String> list) {
        super(context, 0, list);
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.item_selector,null);
        String item = list.get(position);
        MyTextView tv_item = rootView.findViewById(R.id.tv_title);
        tv_item.setText(item);
        return super.getView(position, rootView, parent);
    }
}
