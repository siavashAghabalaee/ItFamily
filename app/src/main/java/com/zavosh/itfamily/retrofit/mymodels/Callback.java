package com.zavosh.itfamily.retrofit.mymodels;

import com.zavosh.itfamily.retrofit.mymodels.homeRequest.HomeResult;
import com.zavosh.itfamily.retrofit.mymodels.registerphone.RegisterResult;
import com.zavosh.itfamily.retrofit.mymodels.verifycode.VerifyCodeResult;

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
}
