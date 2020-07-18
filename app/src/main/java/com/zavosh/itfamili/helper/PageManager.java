package com.zavosh.itfamili.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.zavosh.itfamili.activities.HomeActivity;
import com.zavosh.itfamili.activities.LoginActivity;
import com.zavosh.itfamili.activities.PdfViewerActivity;
import com.zavosh.itfamili.activities.RegisterActivity;
import com.zavosh.itfamili.activities.VerifyActivity;
import com.zavosh.itfamili.activities.VideoPlayerActivity;
import com.zavosh.itfamili.fragments.BlogDetailsFragment;
import com.zavosh.itfamili.fragments.BlogListFragment;
import com.zavosh.itfamili.fragments.CommentFragment;
import com.zavosh.itfamili.fragments.GroupDetailsFragment;
import com.zavosh.itfamili.fragments.GroupListFragment;
import com.zavosh.itfamili.fragments.HomeFragment;
import com.zavosh.itfamili.fragments.MagazineDetailFragment;
import com.zavosh.itfamili.fragments.MagazinesFragment;
import com.zavosh.itfamili.fragments.PodCastFragment;
import com.zavosh.itfamili.fragments.PodcastDetailFragment;
import com.zavosh.itfamili.fragments.ProfileFragment;
import com.zavosh.itfamili.fragments.QuestionFragment;
import com.zavosh.itfamili.fragments.SupportFragment;
import com.zavosh.itfamili.fragments.VideoDetailFragment;
import com.zavosh.itfamili.fragments.VideoListFragment;
import com.zavosh.itfamili.interfaces.PageManagerHelper;

public class PageManager implements PageManagerHelper {
    public static PageManager pageManager;
    public static FragmentHandler fragmentHandler;

    public PageManagerHelper helper = this;

    private PageManager() {
    }

    public static PageManager getInstance() {

        if (pageManager == null)
            return pageManager = new PageManager();
        else
            return pageManager;


    }

    public static PageManager getInstance(Activity activity, FragmentManager fragmentManager) {
        fragmentHandler = new FragmentHandler(activity, fragmentManager);
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
    public void goVideoPlayerActivity(Context context,String videoLink) {
        Intent intent = VideoPlayerActivity.Companion.getInstance(context,videoLink);
        context.startActivity(intent);
    }

    @Override
    public void goPdfViewerActivity(Context context, String pdfLink) {
        Intent intent = PdfViewerActivity.Companion.getInstance(context,pdfLink);
        context.startActivity(intent);
    }

    @Override
    public void goHomeFragment() {
        fragmentHandler.clearBack();
        HomeFragment fragment = new HomeFragment();
        fragmentHandler.loadFragment(fragment, false);
    }

    @Override
    public void goProfileFragment() {
        fragmentHandler.clearBack();
        ProfileFragment fragment = new ProfileFragment();
        fragmentHandler.loadFragment(fragment, false);
    }

    @Override
    public void goMagazinesFragment() {
        fragmentHandler.clearBack();
        MagazinesFragment fragment = new MagazinesFragment();
        fragmentHandler.loadFragment(fragment, false);
    }

    @Override
    public void goMagazineFragmentWithBackStack() {
        fragmentHandler.clearBack();
        MagazinesFragment fragment = new MagazinesFragment();
        fragmentHandler.loadFragment(fragment, true);
    }

    @Override
    public void goMagazineDetailFragment(Bundle bundle) {
        MagazineDetailFragment fragment = new MagazineDetailFragment();
        fragmentHandler.loadFragment(fragment, true, bundle);
    }

    @Override
    public void goBlogsFragment() {
        fragmentHandler.clearBack();
        BlogListFragment fragment = new BlogListFragment();
        fragmentHandler.loadFragment(fragment, false);
    }

    @Override
    public void goBlogsDetailsFragment(Bundle bundle) {
        BlogDetailsFragment fragment = new BlogDetailsFragment();
        fragmentHandler.loadFragment(fragment, true, bundle);
    }

    @Override
    public void goVideoListFragment() {
        fragmentHandler.clearBack();
        VideoListFragment fragment = new VideoListFragment();
        fragmentHandler.loadFragment(fragment, true);
    }


    public void goVideoDetailFragment(Bundle bundle) {
        VideoDetailFragment fragment = new VideoDetailFragment();
        fragmentHandler.loadFragment(fragment, true, bundle);
    }

    @Override
    public void goPodcastFragment() {
        fragmentHandler.clearBack();
        PodCastFragment fragment = new PodCastFragment();
        fragmentHandler.loadFragment(fragment, true);

    }

    @Override
    public void goPodcastDetailFragment(Bundle bundle) {
        PodcastDetailFragment fragment = new PodcastDetailFragment();
        fragmentHandler.loadFragment(fragment, true, bundle);
    }


    @Override
    public void goQuestionFragment() {
        fragmentHandler.clearBack();
        QuestionFragment fragment = new QuestionFragment();
        fragmentHandler.loadFragment(fragment, true);

    }

    @Override
    public void goSupportFragment() {
        fragmentHandler.clearBack();
        SupportFragment fragment = new SupportFragment();
        fragmentHandler.loadFragment(fragment, true);

    }

    @Override
    public void goCommentFragment(Bundle bundle) {
        CommentFragment fragment = new CommentFragment();
        fragmentHandler.loadFragment(fragment,true,bundle);
    }

    @Override
    public void goGroupListFragment() {
        GroupListFragment fragment = new GroupListFragment();
        fragmentHandler.loadFragment(fragment,true);
    }

    @Override
    public void goGroupDetailsFragment(Bundle bundle) {
        GroupDetailsFragment fragment = new GroupDetailsFragment();
        fragmentHandler.loadFragment(fragment,true,bundle);
    }

}
