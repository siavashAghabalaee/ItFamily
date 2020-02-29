package com.zavosh.itfamily.retrofit;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.zavosh.itfamily.R;
import com.zavosh.itfamily.helper.Memory;
import com.zavosh.itfamily.myviews.MyToast;
import com.zavosh.itfamily.retrofit.mymodels.homeRequest.HomeRequest;
import com.zavosh.itfamily.retrofit.mymodels.homeRequest.HomeSender;
import com.zavosh.itfamily.retrofit.mymodels.registerphone.RegisterResponse;
import com.zavosh.itfamily.retrofit.mymodels.registerphone.RegisterSender;
import com.zavosh.itfamily.retrofit.mymodels.verifycode.VerifyCodeResponse;
import com.zavosh.itfamily.retrofit.mymodels.verifycode.VerifyCodeSender;
import com.zavosh.itfamily.retrofit.mymodels.zavoshchecker.CheckResponse;
import com.zavosh.itfamily.retrofit.mymodels.zavoshchecker.RequestsManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Server implements RequestsManager {
    APIService apiService = ApiUtils.getAPIService();
    public static Server server;
    private Context context;
    private ProgressBar loader;
    private Server(Context context){
        this.context = context;
    }
    public static Server getInstance(Context context){
        if (server == null)
            return server = new Server(context);
        else
            return server;
    }

    //id = 1
    public void sendPhone(String phone, final ProgressBar loader,
                          final com.zavosh.itfamily.retrofit.mymodels.Callback.Register callback){
        this.loader = loader;
        this.loader.setVisibility(View.VISIBLE);
        apiService.registerRequest(new RegisterSender(phone)).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(),context,1,Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())){
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                if (context!=null) {
                    loader.setVisibility(View.GONE);
                    MyToast.showToast(context, context.getString(R.string.error));
                }
            }
        });
    }
    //id = 2
    public void sendVerifyCode(String tokenId,String activationCode ,String deviceId , String deviceModel , String osType , String osVersion , final ProgressBar loader,
                               final com.zavosh.itfamily.retrofit.mymodels.Callback.VerfyCode callback){
        this.loader = loader;
        this.loader.setVisibility(View.VISIBLE);
        apiService.sendVerify(new VerifyCodeSender(tokenId,activationCode,deviceId,deviceModel,osType,osVersion)).enqueue(new Callback<VerifyCodeResponse>() {
            @Override
            public void onResponse(Call<VerifyCodeResponse> call, Response<VerifyCodeResponse> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(),context,2,Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())){
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<VerifyCodeResponse> call, Throwable t) {
                if (context!=null) {
                    loader.setVisibility(View.GONE);
                    MyToast.showToast(context, context.getString(R.string.error));
                }
            }
        });
    }

    //id = 3
    public void getHome(String version,String osType, final ProgressBar loader,
                        final com.zavosh.itfamily.retrofit.mymodels.Callback.Home callback){
        this.loader = loader;   this.version = version;  this.osType = osType;  this.callbackHome = callback;
        this.loader.setVisibility(View.VISIBLE);
        apiService.getHome(Memory.loadToken(),new HomeSender(version,osType)).enqueue(new Callback<HomeRequest>() {
            @Override
            public void onResponse(Call<HomeRequest> call, Response<HomeRequest> response) {
                Log.i("aeaijwdaiojd","status "+response.code());
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(),context,3,Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())){
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<HomeRequest> call, Throwable t) {
                if (context!=null) {
                    loader.setVisibility(View.GONE);
                    MyToast.showToast(context, context.getString(R.string.error));
                }
            }
        });
    }

    @Override
    public void resendRequest(int id) {
        switch (id){
            case 3:
                Log.i("aeaijwdaiojd","resend ");
                getHome(version,osType,loader,callbackHome);
                break;
        }
    }

    @Override
    public void setMessageForProgressBar(String message, int id) {
        if (context!=null) {
            Log.i("ServerLog","7");
            MyToast.showToast(context, message);
            loader.setVisibility(View.GONE);
        }
    }






    private String version;
    private String osType;
    private com.zavosh.itfamily.retrofit.mymodels.Callback.Home callbackHome;
}
