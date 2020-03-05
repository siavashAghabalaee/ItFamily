package com.zavosh.itfamily.retrofit;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.zavosh.itfamily.R;
import com.zavosh.itfamily.helper.Memory;
import com.zavosh.itfamily.myviews.MyToast;
import com.zavosh.itfamily.retrofit.mymodels.bloglistrequest.BlogListRequest;
import com.zavosh.itfamily.retrofit.mymodels.homeRequest.HomeRequest;
import com.zavosh.itfamily.retrofit.mymodels.homeRequest.HomeSender;
import com.zavosh.itfamily.retrofit.mymodels.magazinerequest.MagazineRequest;
import com.zavosh.itfamily.retrofit.mymodels.podcastlistrequest.PodcastListRequest;
import com.zavosh.itfamily.retrofit.mymodels.postprofilerequest.PostProfileRequest;
import com.zavosh.itfamily.retrofit.mymodels.postprofilerequest.PostProfileSender;
import com.zavosh.itfamily.retrofit.mymodels.postquestionrequest.PostQuestionRequest;
import com.zavosh.itfamily.retrofit.mymodels.postquestionrequest.PostRequestSender;
import com.zavosh.itfamily.retrofit.mymodels.questionListlistrequest.QuestionListRequest;
import com.zavosh.itfamily.retrofit.mymodels.registerphone.RegisterResponse;
import com.zavosh.itfamily.retrofit.mymodels.registerphone.RegisterSender;
import com.zavosh.itfamily.retrofit.mymodels.verifycode.VerifyCodeResponse;
import com.zavosh.itfamily.retrofit.mymodels.verifycode.VerifyCodeSender;
import com.zavosh.itfamily.retrofit.mymodels.videolistrequest.VideoListRequest;
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
    public void getHome(String version,String osType, final ProgressBar loader
            , final com.zavosh.itfamily.retrofit.mymodels.Callback.Home callback){
        this.loader = loader;   this.version = version;  this.osType = osType;  this.callbackHome = callback;
        this.loader.setVisibility(View.VISIBLE);
        apiService.getHome(Memory.loadToken(),new HomeSender(version,osType)).enqueue(new Callback<HomeRequest>() {
            @Override
            public void onResponse(Call<HomeRequest> call, Response<HomeRequest> response) {
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

    //id = 4
    public void sendProfile(String fullName, String email, String isMail ,final ProgressBar loader, final com.zavosh.itfamily.retrofit.mymodels.Callback.PostProfile callback){
        this.loader = loader;   this.fullName = fullName;  this.email = email;   this.isMail = isMail; this.postProfileCallback = callback;
        this.loader.setVisibility(View.VISIBLE);
        apiService.postProfile(Memory.TOKEN,new PostProfileSender(fullName,email,isMail)).enqueue(new Callback<PostProfileRequest>() {
            @Override
            public void onResponse(Call<PostProfileRequest> call, Response<PostProfileRequest> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(),context,4,Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())){
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<PostProfileRequest> call, Throwable t) {
                loader.setVisibility(View.GONE);
                MyToast.showToast(context, context.getString(R.string.error));
            }
        });
    }

    //id = 5
    public void getMagazineList(final ProgressBar loader, final com.zavosh.itfamily.retrofit.mymodels.Callback.MagazineList callback){
        this.loader = loader;
        this.magazineListCallback = callback;
        this.loader.setVisibility(View.VISIBLE);
        apiService.getMagazineList(Memory.loadToken()).enqueue(new Callback<MagazineRequest>() {
            @Override
            public void onResponse(Call<MagazineRequest> call, Response<MagazineRequest> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(),context,5,Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())){

                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<MagazineRequest> call, Throwable t) {
                loader.setVisibility(View.GONE);
                MyToast.showToast(context, context.getString(R.string.error));
            }
        });
    }

    //id = 6
    public void getBlogList(final ProgressBar loader, final com.zavosh.itfamily.retrofit.mymodels.Callback.BlogList callback){
        this.loader = loader;
        this.blogListCallback = callback;
        loader.setVisibility(View.VISIBLE);
        apiService.getBlogList(Memory.loadToken()).enqueue(new Callback<BlogListRequest>() {
            @Override
            public void onResponse(Call<BlogListRequest> call, Response<BlogListRequest> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(),context,6,Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())){
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<BlogListRequest> call, Throwable t) {
                loader.setVisibility(View.GONE);
                MyToast.showToast(context, context.getString(R.string.error));
            }
        });

    }

    //id = 7
    public void getVideoList(final ProgressBar loader, final com.zavosh.itfamily.retrofit.mymodels.Callback.VideoList callback){
        this.loader = loader;
        this.videoListCallback = callback;
        loader.setVisibility(View.VISIBLE);
        apiService.getVideoList(Memory.loadToken()).enqueue(new Callback<VideoListRequest>() {
            @Override
            public void onResponse(Call<VideoListRequest> call, Response<VideoListRequest> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(),context,7,Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())){
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<VideoListRequest> call, Throwable t) {
                loader.setVisibility(View.GONE);
                MyToast.showToast(context, context.getString(R.string.error));
            }
        });
    }

    //id = 8
    public void getPodcastList(final ProgressBar loader, final com.zavosh.itfamily.retrofit.mymodels.Callback.PodcastList callback){
        this.loader = loader;
        this.podcastListCallback = callback;
        loader.setVisibility(View.VISIBLE);
        apiService.getPodcastList(Memory.loadToken()).enqueue(new Callback<PodcastListRequest>() {
            @Override
            public void onResponse(Call<PodcastListRequest> call, Response<PodcastListRequest> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(),context,8,Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())){
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<PodcastListRequest> call, Throwable t) {
                loader.setVisibility(View.GONE);
                MyToast.showToast(context, context.getString(R.string.error));
            }
        });
    }

    //id = 9
    public void getQuestionList(final ProgressBar loader, final com.zavosh.itfamily.retrofit.mymodels.Callback.QuestionList callback){
        this.loader = loader;
        this.questionListCallback = callback;
        loader.setVisibility(View.VISIBLE);
        apiService.getQuestionList(Memory.loadToken()).enqueue(new Callback<QuestionListRequest>() {
            @Override
            public void onResponse(Call<QuestionListRequest> call, Response<QuestionListRequest> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(),context,9,Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())){
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<QuestionListRequest> call, Throwable t) {
                loader.setVisibility(View.GONE);
                MyToast.showToast(context, context.getString(R.string.error));
            }
        });
    }

    //id = 9
    public void postQuestion(String subject, String message , final ProgressBar loader, final com.zavosh.itfamily.retrofit.mymodels.Callback.PostQuestion callback){
        this.loader = loader;
        this.postQuestionCallback = callback;
        this.message = message;
        this.subject = subject;
        apiService.postQuestion(Memory.loadToken(),new PostRequestSender(subject,message)).enqueue(new Callback<PostQuestionRequest>() {
            @Override
            public void onResponse(Call<PostQuestionRequest> call, Response<PostQuestionRequest> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(),context,10,Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())){
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<PostQuestionRequest> call, Throwable t) {
                loader.setVisibility(View.GONE);
                MyToast.showToast(context, context.getString(R.string.error));
            }
        });
    }


    @Override
    public void resendRequest(int id) {
        switch (id){
            case 3:
                getHome(version,osType,loader,callbackHome);
                break;
            case 4:
                sendProfile(fullName,email,isMail,loader,postProfileCallback);
                break;
            case 5:
                getMagazineList(loader,magazineListCallback);
                break;
            case 6:
                getBlogList(loader,blogListCallback);
                break;
            case 7:
                getVideoList(loader,videoListCallback);
                break;
            case 8:
                getPodcastList(loader,podcastListCallback);
                break;
            case 9:
                getQuestionList(loader,questionListCallback);
                break;
            case 10:
                postQuestion(subject,message,loader,postQuestionCallback);
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




    //3
    private String version;
    private String osType;
    private com.zavosh.itfamily.retrofit.mymodels.Callback.Home callbackHome;
    //4
    private String fullName;
    private String email;
    private String isMail;
    private com.zavosh.itfamily.retrofit.mymodels.Callback.PostProfile postProfileCallback;
    //5
    private com.zavosh.itfamily.retrofit.mymodels.Callback.MagazineList magazineListCallback;
    //6
    private com.zavosh.itfamily.retrofit.mymodels.Callback.BlogList blogListCallback;
    //7
    private com.zavosh.itfamily.retrofit.mymodels.Callback.VideoList videoListCallback;
    //8
    private com.zavosh.itfamily.retrofit.mymodels.Callback.PodcastList podcastListCallback;
    //9
    private com.zavosh.itfamily.retrofit.mymodels.Callback.QuestionList questionListCallback;
    //10
    private String subject;
    private String message;
    private com.zavosh.itfamily.retrofit.mymodels.Callback.PostQuestion postQuestionCallback;

}
