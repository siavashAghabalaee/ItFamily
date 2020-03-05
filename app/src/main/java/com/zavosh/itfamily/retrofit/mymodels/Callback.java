package com.zavosh.itfamily.retrofit.mymodels;

import com.zavosh.itfamily.retrofit.mymodels.bloglistrequest.BlogListResult;
import com.zavosh.itfamily.retrofit.mymodels.homeRequest.HomeResult;
import com.zavosh.itfamily.retrofit.mymodels.magazinerequest.MagazineResult;
import com.zavosh.itfamily.retrofit.mymodels.registerphone.RegisterResult;
import com.zavosh.itfamily.retrofit.mymodels.verifycode.VerifyCodeResult;

import java.util.List;

public interface Callback {
    interface Register{
        void callback(RegisterResult result);
    }

    interface VerfyCode{
        void callback(VerifyCodeResult result);
    }

    interface Home{
        void callback(HomeResult result);
    }

    interface PostProfile{
        void callback(String result);
    }

    interface MagazineList{
        void callback(List<MagazineResult> result);
    }

    interface BlogList{
        void callback(List<BlogListResult> result);
    }
}
