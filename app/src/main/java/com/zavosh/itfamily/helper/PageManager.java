package com.zavosh.itfamily.helper;

import android.content.Context;
import com.zavosh.itfamily.activities.LoginActivity;
import com.zavosh.itfamily.interfaces.PageManagerHelper;

public class PageManager implements PageManagerHelper{
    public static PageManager pageManager;

    public PageManagerHelper helper = this;

    private PageManager() {}

    public static PageManager getInstance(){

        if (pageManager == null)
            return pageManager = new PageManager();
        else
            return pageManager;


    }


    @Override
    public void goLoginActivity(Context context) {
        context.startActivity(LoginActivity.Companion.getInstanse(context));
    }
}
