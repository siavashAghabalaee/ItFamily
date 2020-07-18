package com.zavosh.itfamily.myviews;

import android.content.Context;
import android.util.AttributeSet;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class MyImageView extends RoundedImageView {
    public String imageUrl = "null";

    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setPicasso (String url, Context context){
        imageUrl = url;
        Picasso.with(context).load(url).into(this);
    }
    public void setPicasso (String url , int placeholder , int error ,Context context){
        imageUrl = url;
        Picasso.with(context).load(url).placeholder(placeholder).error(error).into(this);
    }
    public void setPicasso (String url , int placeholder , int error ,Context context,int x , int y){
        imageUrl = url;
        Picasso.with(context).load(url).resize(x,y).onlyScaleDown().placeholder(placeholder).error(error).into(this);
    }
}
