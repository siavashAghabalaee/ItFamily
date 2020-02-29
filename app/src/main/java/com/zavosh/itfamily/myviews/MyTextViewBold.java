package com.zavosh.itfamily.myviews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.zavosh.itfamily.helper.MyApp;

public class MyTextViewBold extends AppCompatTextView {
    public MyTextViewBold(Context context) {
        super(context);
        setTypeface(MyApp.appFontBold);
    }

    public MyTextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(MyApp.appFontBold);
    }

    public MyTextViewBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(MyApp.appFontBold);
    }

    public String text(){
        return getText().toString();
    }
}
