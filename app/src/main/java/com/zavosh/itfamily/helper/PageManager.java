package com.zavosh.itfamily.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.zavosh.itfamily.activities.HomeActivity;
import com.zavosh.itfamily.activities.LoginActivity;
import com.zavosh.itfamily.activities.RegisterActivity;
import com.zavosh.itfamily.activities.VerifyActivity;
import com.zavosh.itfamily.fragments.BlogListFragment;
import com.zavosh.itfamily.fragments.HomeFragment;
import com.zavosh.itfamily.fragments.MagazineDetailFragment;
import com.zavosh.itfamily.fragments.MagazinesFragment;
import com.zavosh.itfamily.fragments.PodCastFragment;
import com.zavosh.itfamily.fragments.ProfileFragment;
import com.zavosh.itfamily.fragments.QuestionFragment;
import com.zavosh.itfamily.fragments.SupportFragment;
import com.zavosh.itfamily.fragments.VideoListFragment;
import com.zavosh.itfamily.interfaces.PageManagerHelper;

public class PageManager implements PageManagerHelper{
    public static PageManager pageManager;
    public static FragmentHandler fragmentHandler;

    public PageManagerHelper helper = this;

    private PageManager() {}

    public static PageManager getInstance(){

        if (pageManager == null)
            return pageManager = new PageManager();
        else
            return pageManager;


    }

    public static PageManager getInstance(Activity activity, FragmentManager fragmentManager){
        fragmentHandler = new FragmentHandler(activity,fragmentManager);
        if (pageManager == null)
            return pageManager = new PageManager();
        else
            return pageManager;


    }


    @Override
    public void goLoginActivity(Context context) {
        context.startActivity(LoginActivity.Companion.getInstanse(context));
    }

    @Override
    public void goRegisterActivity(Context context) {
        context.startActivity(RegisterActivity.Companion.getInstance(context));
    }

    @Override
    public void goVerifyActivity(Context context) {
        context.startActivity(VerifyActivity.Companion.getInstance(context));
    }

    @Override
    public void goHomeActivity(Context context) {
        Intent intent = HomeActivity.Companion.getInstance(context);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    public void goHomeFragment() {
        fragmentHandler.clearBack();
        HomeFragment fragment = new HomeFragment();
        fragmentHandler.loadFragment(fragment,false);
    }

    @Override
    public void goProfileFragment() {
        fragmentHandler.clearBack();
        ProfileFragment fragment = new ProfileFragment();
        fragmentHandler.loadFragment(fragment,false);
    }

    @Override
    public void goMagazinesFragment() {
        fragmentHandler.clearBack();
        MagazinesFragment fragment = new MagazinesFragment();
        fragmentHandler.loadFragment(fragment,false);
    }

    @Override
    public void goMagazineDetailFragment(Bundle bundle) {
        fragmentHandler.clearBack();
        MagazineDetailFragment fragment = new MagazineDetailFragment();
        fragmentHandler.loadFragment(fragment,true,bundle);
    }

    @Override
    public void goBlogsFragment() {
        fragmentHandler.clearBack();
        BlogListFragment fragment = new BlogListFragment();
        fragmentHandler.loadFragment(fragment,false);
    }

    @Override
    public void goVideoListFragment() {
        fragmentHandler.clearBack();
        VideoListFragment fragment = new VideoListFragment();
        fragmentHandler.loadFragment(fragment,true);
    }

    @Override
    public void goPodcastFragment() {
        fragmentHandler.clearBack();
        PodCastFragment fragment = new PodCastFragment();
        fragmentHandler.loadFragment(fragment,true);

    }

    @Override
    public void goQuestionFragment() {
        fragmentHandler.clearBack();
        QuestionFragment fragment = new QuestionFragment();
        fragmentHandler.loadFragment(fragment,true);

    }

    @Override
    public void goSupportFragment() {
        fragmentHandler.clearBack();
        SupportFragment fragment = new SupportFragment();
        fragmentHandler.loadFragment(fragment,true);

    }

}
