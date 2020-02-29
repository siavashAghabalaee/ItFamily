package com.zavosh.itfamily.interfaces;

import android.content.Context;

public interface PageManagerHelper {
    void goLoginActivity(Context context);
    void goRegisterActivity(Context context);
    void goVerifyActivity(Context context);
    void goHomeActivity(Context context);

    //---fragments
    void goHomeFragment();
    void goProfileFragment();
    void goMagazinesFragment();
    void goArticlesFragment();
}
