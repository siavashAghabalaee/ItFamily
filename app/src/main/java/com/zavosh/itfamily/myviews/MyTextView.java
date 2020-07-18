package com.zavosh.itfamily.myviews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.zavosh.itfamily.helper.MyApp;

public class MyTextView extends AppCompatTextView {
    public MyTextView(Context context) {
        super(context);
        setTypeface(MyApp.appFont);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(MyApp.appFont);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(MyApp.appFont);
    }

    public String text(){
        return getText().toString();
    }
}
