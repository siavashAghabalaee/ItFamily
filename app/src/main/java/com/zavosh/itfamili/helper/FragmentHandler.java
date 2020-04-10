package com.zavosh.itfamili.helper;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zavosh.itfamili.R;

public class FragmentHandler {
    Activity activity;
    FragmentManager fragmentManager;

    public FragmentHandler(Activity activity, FragmentManager fragmentManager) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    public void loadFragment(Fragment fragment, boolean addToBackStack) {
        try {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            String frgTag = fragment.getClass().getSimpleName();
            fragmentTransaction.replace(R.id.frameLayout, fragment, frgTag);
            if (addToBackStack)
                fragmentTransaction.addToBackStack(frgTag);
            fragmentTransaction.commit();
        }catch (Exception e){}


    }

    public void loadFragment(Fragment fragment, boolean addToBackStack, Bundle bundle) {
        try {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            String frgTag = fragment.getClass().getSimpleName();
            if(bundle!=null)
                fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.frameLayout, fragment, frgTag);
            if (addToBackStack)
                fragmentTransaction.addToBackStack(frgTag);

            fragmentTransaction.commit();
        }catch (Exception e){}


    }


    private boolean isExistFragment(String tag) {
        Fragment targetFragment = fragmentManager.findFragmentByTag(tag);
        return targetFragment != null;
    }

    public void clearBack(){
        for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }
    }
    public void clearOneBack(){
        fragmentManager.popBackStack();
    }

}
