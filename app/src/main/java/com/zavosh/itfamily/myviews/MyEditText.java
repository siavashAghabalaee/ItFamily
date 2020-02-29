package com.zavosh.itfamily.myviews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import com.zavosh.itfamily.helper.MyApp;

public class MyEditText extends AppCompatEditText {
    public MyEditText(Context context) {
        super(context);
        setTypeface(MyApp.appFont);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(MyApp.appFont);
    }

    public String text(){
        return filtered();
    }

    public String filtered(){
        if (getText() != null) {
            return getText().toString()
                    .replace("۰","0")
                    .replace("۱","1")
                    .replace("۲","2")
                    .replace("۳","3")
                    .replace("۴","4")
                    .replace("۵","5")
                    .replace("۶","6")
                    .replace("۷","7")
                    .replace("۸","8")
                    .replace("۹","9")
                    .trim();
        }else{
            return "";
        }
    }

}
