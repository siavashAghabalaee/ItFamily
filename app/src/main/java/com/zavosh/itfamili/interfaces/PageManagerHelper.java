package com.zavosh.itfamili.interfaces;

import android.content.Context;
import android.os.Bundle;

public interface PageManagerHelper {
    void goLoginActivity(Context context);
    void goRegisterActivity(Context context);
    void goVerifyActivity(Context context);
    void goHomeActivity(Context context);
    void goVideoPlayerActivity(Context context,String videoLink);
    void goPdfViewerActivity(Context context, String pdfLink);

    //---fragments
    void goHomeFragment();
    void goProfileFragment();
    void goMagazinesFragment();
    void goBlogsFragment();
    void goBlogsDetailsFragment(Bundle bundle);
    void goMagazineDetailFragment(Bundle bundle);
    void goMagazineFragmentWithBackStack();
    void goVideoListFragment();
    void goVideoDetailFragment(Bundle bundle);
    void goPodcastFragment();
    void goPodcastDetailFragment(Bundle bundle);
    void goQuestionFragment();
    void goSupportFragment();
    void goCommentFragment(Bundle bundle);
    void goGroupListFragment();
    void goGroupDetailsFragment(Bundle bundle);
}
