package com.zavosh.itfamily.retrofit.mymodels.zavoshchecker;

import android.content.Context;
import android.util.Log;

import com.zavosh.itfamily.R;
import com.zavosh.itfamily.helper.Memory;
import com.zavosh.itfamily.myviews.MyToast;
import com.zavosh.itfamily.retrofit.APIService;
import com.zavosh.itfamily.retrofit.ApiUtils;
import com.zavosh.itfamily.retrofit.mymodels.Status;
import com.zavosh.itfamily.retrofit.mymodels.loginrequest.LoginRequest;
import com.zavosh.itfamily.retrofit.mymodels.loginrequest.LoginSender;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckResponse {
    public RequestsManager requestsManager;
    private int responseCode;
    private Status status;
    private Context context;
    private int id;

    public CheckResponse() {
    }

    public CheckResponse(int responseCode, Context context, int id,RequestsManager requestsManager) {
        this.responseCode = responseCode;
        this.context = context;
        this.id = id;
        this.requestsManager = requestsManager;
    }

    public boolean checkStatus(int responseCode , Status status, Context context, int id){
        Log.i("kk",status.getStatusCode() + " " + status.getIsSuccess());
        if (responseCode == 401){
            getToken(context, id);
            return false;
        }else if (responseCode == 200){
            if (status.getStatusCode() == 16){
                Log.i("kk","1");
                getToken(context,id);
                return false;
            }else if (status.getIsSuccess()){
                return true;
            }else {

                //MyToast.showToast(context,status.getMessage());
                //requestsManager.setMessageForProgressBar(status.getMessage(),id);
                return false;
            }
        }else {
            //MyToast.showToast(context,context.getString(R.string.error));
            //requestsManager.setMessageForProgressBar(context.getString(R.string.error),id);
            return false;
        }
    }

    public boolean checkRequestCode(int responseCode , Context context,int id){
        if (responseCode == 401){
            getToken(context,id);
            return false;
        }else if (responseCode == 200){
            return true;
        }else {
            //MyToast.showToast(context,context.getString(R.string.error));
            //requestsManager.setMessageForProgressBar(context.getString(R.string.error),id);
            return false;
        }
    }


    public boolean checkStatus(Status status){
        Log.i("kk",status.getStatusCode() + " " + status.getIsSuccess());
        if (responseCode == 401){
            getToken(context, id);
            return false;
        }else if (responseCode == 200){
            if (status.getStatusCode() == 16){
                getToken(context,id);
                return false;
            }else if (status.getIsSuccess()){
                return true;
            }else {
                MyToast.showToast(context,status.getMessage());
                requestsManager.setMessageForProgressBar(status.getMessage(),id);
                return false;
            }
        }else {
            MyToast.showToast(context,context.getString(R.string.error));
            requestsManager.setMessageForProgressBar(context.getString(R.string.error),id);
            return false;
        }
    }

    public boolean checkRequestCode(){
        if (responseCode == 401){
            getToken(context,id);
            return false;
        }else if (responseCode == 200){
            return true;
        }else {
            MyToast.showToast(context,context.getString(R.string.error));
            requestsManager.setMessageForProgressBar(context.getString(R.string.error),id);
            return false;
        }
    }

    private void getToken(final Context context, final int id) {

        APIService apiService = ApiUtils.getAPIService();
        LoginSender loginSender = new LoginSender(Memory.loadPhone(), Memory.loadPassword());
        apiService.login(loginSender).enqueue(new Callback<LoginRequest>() {
            @Override
            public void onResponse(Call<LoginRequest> call, Response<LoginRequest> response) {
                Memory.saveToken(response.body().getResult().getTokenId());
                requestsManager.resendRequest(id);
            }

            @Override
            public void onFailure(Call<LoginRequest> call, Throwable t) {
                MyToast.showToast(context,context.getString(R.string.androidErrorRequest));
                requestsManager.setMessageForProgressBar(context.getString(R.string.androidErrorRequest),id);
            }
        });



    }
}
