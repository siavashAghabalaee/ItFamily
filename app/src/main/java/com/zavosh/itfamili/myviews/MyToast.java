package com.zavosh.itfamili.myviews;

import android.content.Context;
import android.widget.Toast;

public class MyToast {
    public static void showToast(Context context, String message){
        try {
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }

    }
}
