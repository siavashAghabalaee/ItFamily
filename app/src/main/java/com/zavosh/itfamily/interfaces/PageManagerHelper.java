package com.zavosh.itfamily.interfaces;

import android.content.Context;
import android.os.Bundle;

public interface PageManagerHelper {
    void goLoginActivity(Context context);
    void goRegisterActivity(Context context);
    void goVerifyActivity(Context context);
    void goHomeActivity(Context context);

    //---fragments
    void goHomeFragment();
    void goProfileFragment();
    void goMagazinesFragment();
    void goBlogsFragment();
    void goBlogsDetailsFragment(Bundle bundle);
    void goMagazineDetailFragment(Bundle bundle);
    void goVideoListFragment();
    void goVideoDetailFragment(Bundle bundle);
    void goPodcastFragment();
    void goPodcastDetailFragment(Bundle bundle);
    void goQuestionFragment();
    void goSupportFragment();
}
