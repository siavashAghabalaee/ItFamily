package com.zavosh.itfamily.helper;

import android.app.Application;
import android.graphics.Typeface;

import com.orhanobut.hawk.Hawk;

public class MyApp extends Application {
    public static Typeface appFont;
    public static Typeface appFontBold;

    @Override
    public void onCreate() {
        super.onCreate();
        appFont = Typeface.createFromAsset(getAssets(), "IRANSansMobile(FaNum)_Light.ttf");
        appFontBold = Typeface.createFromAsset(getAssets(), "IRANSansWeb(FaNum)_Bold.ttf");
        Hawk.init(this).build();
    }
}
