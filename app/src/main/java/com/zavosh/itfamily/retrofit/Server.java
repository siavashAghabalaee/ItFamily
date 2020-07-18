package com.zavosh.itfamily.retrofit;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.zavosh.itfamily.R;
import com.zavosh.itfamily.helper.Memory;
import com.zavosh.itfamily.myviews.MyToast;
import com.zavosh.itfamily.retrofit.mymodels.bloglistrequest.BlogListRequest;
import com.zavosh.itfamily.retrofit.mymodels.commentrequest.CommentRequest;
import com.zavosh.itfamily.retrofit.mymodels.commentrequest.CommentSender;
import com.zavosh.itfamily.retrofit.mymodels.contentlist.ContentRequest;
import com.zavosh.itfamily.retrofit.mymodels.grouprequest.GroupDetailsRequest;
import com.zavosh.itfamily.retrofit.mymodels.grouprequest.GroupDetailsSender;
import com.zavosh.itfamily.retrofit.mymodels.homeRequest.HomeRequest;
import com.zavosh.itfamily.retrofit.mymodels.homeRequest.HomeSender;
import com.zavosh.itfamily.retrofit.mymodels.likerequest.LikeRequest;
import com.zavosh.itfamily.retrofit.mymodels.likerequest.LikeSender;
import com.zavosh.itfamily.retrofit.mymodels.magazinerequest.MagazineRequest;
import com.zavosh.itfamily.retrofit.mymodels.podcastlistrequest.PodcastListRequest;
import com.zavosh.itfamily.retrofit.mymodels.postprofilerequest.PostProfileRequest;
import com.zavosh.itfamily.retrofit.mymodels.postprofilerequest.PostProfileSender;
import com.zavosh.itfamily.retrofit.mymodels.postquestionrequest.PostQuestionRequest;
import com.zavosh.itfamily.retrofit.mymodels.postquestionrequest.PostRequestSender;
import com.zavosh.itfamily.retrofit.mymodels.questionListlistrequest.QuestionListRequest;
import com.zavosh.itfamily.retrofit.mymodels.registerphone.RegisterResponse;
import com.zavosh.itfamily.retrofit.mymodels.registerphone.RegisterSender;
import com.zavosh.itfamily.retrofit.mymodels.sendcommentrequest.SendCommentRequest;
import com.zavosh.itfamily.retrofit.mymodels.sendcommentrequest.SendCommentSender;
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

    private Server(Context context) {
        this.context = context;
    }

    public static Server getInstance(Context context) {
        if (server == null) {
            Log.i("oawdiadiwjawd", "121");
            return server = new Server(context);
        } else {
            Log.i("oawdiadiwjawd", "122");
            return server;
        }
    }

    //id = 1
    public void sendPhone(String phone, final ProgressBar loader,
                          final com.zavosh.itfamily.retrofit.mymodels.Callback.Register callback) {
        this.loader = loader;
        this.loader.setVisibility(View.VISIBLE);
        apiService.registerRequest(new RegisterSender(phone)).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(), context, 1, Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())) {
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                if (context != null) {
                    loader.setVisibility(View.GONE);
                    MyToast.showToast(context, context.getString(R.string.error));
                }
            }
        });
    }

    //id = 2
    public void sendVerifyCode(String tokenId, String activationCode, String deviceId, String deviceModel, String osType, String osVersion, final ProgressBar loader,
                               final com.zavosh.itfamily.retrofit.mymodels.Callback.VerfyCode callback) {
        this.loader = loader;
        this.loader.setVisibility(View.VISIBLE);
        apiService.sendVerify(new VerifyCodeSender(tokenId, activationCode, deviceId, deviceModel, osType, osVersion)).enqueue(new Callback<VerifyCodeResponse>() {
            @Override
            public void onResponse(Call<VerifyCodeResponse> call, Response<VerifyCodeResponse> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(), context, 2, Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())) {
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<VerifyCodeResponse> call, Throwable t) {
                if (context != null) {
                    loader.setVisibility(View.GONE);
                    MyToast.showToast(context, context.getString(R.string.error));
                }
            }
        });
    }

    //id = 3
    public void getHome(String version, String osType, final ProgressBar loader
            , final com.zavosh.itfamily.retrofit.mymodels.Callback.Home callback) {
        this.loader = loader;
        this.version = version;
        this.osType = osType;
        this.callbackHome = callback;
        this.loader.setVisibility(View.VISIBLE);
        apiService.getHome(Memory.loadToken(), new HomeSender(version, osType)).enqueue(new Callback<HomeRequest>() {
            @Override
            public void onResponse(Call<HomeRequest> call, Response<HomeRequest> response) {
                Log.i("oawdiadiwjawd", "126");
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(), context, 3, Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())) {
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<HomeRequest> call, Throwable t) {
                Log.i("oawdiadiwjawd", "127");
                if (context != null) {
                    loader.setVisibility(View.GONE);
                    MyToast.showToast(context, context.getString(R.string.error));
                }
            }
        });
    }

    //id = 4
    public void sendProfile(String fullName, String email, String isMail, final ProgressBar loader, final com.zavosh.itfamily.retrofit.mymodels.Callback.PostProfile callback) {
        this.loader = loader;
        this.fullName = fullName;
        this.email = email;
        this.isMail = isMail;
        this.postProfileCallback = callback;
        this.loader.setVisibility(View.VISIBLE);

        PostProfileSender sender = new PostProfileSender(fullName, email, isMail);
        apiService.postProfile(Memory.loadToken(),sender ).enqueue(new Callback<PostProfileRequest>() {
            @Override
            public void onResponse(Call<PostProfileRequest> call, Response<PostProfileRequest> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(), context, 4, Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())) {
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<PostProfileRequest> call, Throwable t) {
                Log.i("sefsefsdfs","error");
                loader.setVisibility(View.GONE);
                MyToast.showToast(context, context.getString(R.string.error));
            }
        });
    }

    //id = 5
    public void getMagazineList(final ProgressBar loader, final com.zavosh.itfamily.retrofit.mymodels.Callback.MagazineList callback) {
        this.loader = loader;
        this.magazineListCallback = callback;
        this.loader.setVisibility(View.VISIBLE);
        apiService.getMagazineList(Memory.loadToken()).enqueue(new Callback<MagazineRequest>() {
            @Override
            public void onResponse(Call<MagazineRequest> call, Response<MagazineRequest> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(), context, 5, Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())) {

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
    public void getBlogList(final ProgressBar loader, final com.zavosh.itfamily.retrofit.mymodels.Callback.BlogList callback) {
        this.loader = loader;
        this.blogListCallback = callback;
        loader.setVisibility(View.VISIBLE);
        apiService.getBlogList(Memory.loadToken()).enqueue(new Callback<BlogListRequest>() {
            @Override
            public void onResponse(Call<BlogListRequest> call, Response<BlogListRequest> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(), context, 6, Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())) {
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
    public void getVideoList(final ProgressBar loader, final com.zavosh.itfamily.retrofit.mymodels.Callback.VideoList callback) {
        this.loader = loader;
        this.videoListCallback = callback;
        loader.setVisibility(View.VISIBLE);
        apiService.getVideoList(Memory.loadToken()).enqueue(new Callback<VideoListRequest>() {
            @Override
            public void onResponse(Call<VideoListRequest> call, Response<VideoListRequest> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(), context, 7, Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())) {
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
    public void getPodcastList(final ProgressBar loader, final com.zavosh.itfamily.retrofit.mymodels.Callback.PodcastList callback) {
        this.loader = loader;
        this.podcastListCallback = callback;
        loader.setVisibility(View.VISIBLE);
        apiService.getPodcastList(Memory.loadToken()).enqueue(new Callback<PodcastListRequest>() {
            @Override
            public void onResponse(Call<PodcastListRequest> call, Response<PodcastListRequest> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(), context, 8, Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())) {
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
    public void getQuestionList(final ProgressBar loader, final com.zavosh.itfamily.retrofit.mymodels.Callback.QuestionList callback) {
        this.loader = loader;
        this.questionListCallback = callback;
        loader.setVisibility(View.VISIBLE);
        apiService.getQuestionList(Memory.loadToken()).enqueue(new Callback<QuestionListRequest>() {
            @Override
            public void onResponse(Call<QuestionListRequest> call, Response<QuestionListRequest> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(), context, 9, Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())) {
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

    //id = 10
    public void postQuestion(String subject, String message, final ProgressBar loader, final com.zavosh.itfamily.retrofit.mymodels.Callback.PostQuestion callback) {
        this.loader = loader;
        this.postQuestionCallback = callback;
        this.message = message;
        this.subject = subject;
        loader.setVisibility(View.VISIBLE);
        apiService.postQuestion(Memory.loadToken(), new PostRequestSender(subject, message)).enqueue(new Callback<PostQuestionRequest>() {
            @Override
            public void onResponse(Call<PostQuestionRequest> call, Response<PostQuestionRequest> response) {
                Server.this.loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(), context, 10, Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())) {
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

    //id = 11
    public void like(String id, boolean likeStatus) {
        this.idLike = id;
        this.likeStatus = likeStatus;
        apiService.likePost(Memory.loadToken(), new LikeSender(id, likeStatus)).enqueue(new Callback<LikeRequest>() {
            @Override
            public void onResponse(Call<LikeRequest> call, Response<LikeRequest> response) {
                CheckResponse checkResponse = new CheckResponse(response.code(), context, 11, Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())) {
//                    Log.i("isChangeLikeStatus","ok");
                }
            }

            @Override
            public void onFailure(Call<LikeRequest> call, Throwable t) {
                MyToast.showToast(context, context.getString(R.string.error));
            }
        });
    }

    //id = 12
    public void getComments(String id, int page, com.zavosh.itfamily.retrofit.mymodels.Callback.GetComment callback) {
        this.idComment = id;
        this.page = page;
        this.getCommentCallback = callback;
        apiService.getComments(Memory.loadToken(), new CommentSender(id, page)).enqueue(new Callback<CommentRequest>() {
            @Override
            public void onResponse(Call<CommentRequest> call, Response<CommentRequest> response) {
                CheckResponse checkResponse = new CheckResponse(response.code(), context, 12, Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())) {
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<CommentRequest> call, Throwable t) {
                MyToast.showToast(context, context.getString(R.string.error));
            }
        });
    }

    //id = 13
    public void sendComment(String idCommentSend, String comment, com.zavosh.itfamily.retrofit.mymodels.Callback.SendComment callback) {
        this.idCommentSend = idCommentSend;
        this.comment = comment;
        sendCommentCallback = callback;
        apiService.sendComment(Memory.loadToken(), new SendCommentSender(idCommentSend, comment)).enqueue(new Callback<SendCommentRequest>() {
            @Override
            public void onResponse(Call<SendCommentRequest> call, Response<SendCommentRequest> response) {
                CheckResponse checkResponse = new CheckResponse(response.code(), context, 13, Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())) {
                    callback.callback();
                }
            }

            @Override
            public void onFailure(Call<SendCommentRequest> call, Throwable t) {
                MyToast.showToast(context, context.getString(R.string.error));
            }
        });
    }

    //id = 14
    public void getContentGroupList(ProgressBar loader,com.zavosh.itfamily.retrofit.mymodels.Callback.SetContentList callback) {
        this.contentGooupListCallback = callback;
        this.loader = loader;
        loader.setVisibility(View.VISIBLE);
        apiService.getContentList().enqueue(new Callback<ContentRequest>() {
            @Override
            public void onResponse(Call<ContentRequest> call, Response<ContentRequest> response) {
                loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(), context, 14, Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())) {
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<ContentRequest> call, Throwable t) {
                loader.setVisibility(View.GONE);
                MyToast.showToast(context, context.getString(R.string.error));
            }
        });
    }

    //id = 15
    public void getGroup(ProgressBar loader,String id,com.zavosh.itfamily.retrofit.mymodels.Callback.SetGroupDetails callback) {
        this.id = id; this.setGroupDetails = callback; this.loader = loader;
        loader.setVisibility(View.VISIBLE);
        apiService.getGroup(Memory.loadToken(),new GroupDetailsSender(id)).enqueue(new Callback<GroupDetailsRequest>() {
            @Override
            public void onResponse(Call<GroupDetailsRequest> call, Response<GroupDetailsRequest> response) {
                loader.setVisibility(View.GONE);
                CheckResponse checkResponse = new CheckResponse(response.code(), context, 15, Server.this);
                if (checkResponse.checkRequestCode() && checkResponse.checkStatus(response.body().getStatus())) {
                    callback.callback(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<GroupDetailsRequest> call, Throwable t) {
                loader.setVisibility(View.GONE);
                MyToast.showToast(context, context.getString(R.string.error));
            }
        });
    }

    @Override
    public void resendRequest(int id) {
        switch (id) {
            case 3:
                getHome(version, osType, loader, callbackHome);
                break;
            case 4:
                sendProfile(fullName, email, isMail, loader, postProfileCallback);
                break;
            case 5:
                getMagazineList(loader, magazineListCallback);
                break;
            case 6:
                getBlogList(loader, blogListCallback);
                break;
            case 7:
                getVideoList(loader, videoListCallback);
                break;
            case 8:
                getPodcastList(loader, podcastListCallback);
                break;
            case 9:
                getQuestionList(loader, questionListCallback);
                break;
            case 10:
                postQuestion(subject, message, loader, postQuestionCallback);
                break;
            case 11:
                like(idLike, likeStatus);
                break;
            case 12:
                getComments(idComment, page, getCommentCallback);
                break;
            case 13:
                sendComment(idCommentSend, comment, sendCommentCallback);
                break;
            case 14:
                getContentGroupList(loader,contentGooupListCallback);
                break;
            case 15:
                getGroup(loader,this.id,setGroupDetails);
                break;

        }
    }

    @Override
    public void setMessageForProgressBar(String message, int id) {
        if (context != null) {
            Log.i("ServerLog", "7");
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
    //11
    private String idLike;
    private boolean likeStatus;
    //12
    private String idComment;
    private int page;
    private com.zavosh.itfamily.retrofit.mymodels.Callback.GetComment getCommentCallback;
    //13
    private String idCommentSend;
    private String comment;
    private com.zavosh.itfamily.retrofit.mymodels.Callback.SendComment sendCommentCallback;
    //14
    private com.zavosh.itfamily.retrofit.mymodels.Callback.SetContentList contentGooupListCallback;
    //15
    private com.zavosh.itfamily.retrofit.mymodels.Callback.SetGroupDetails setGroupDetails;
    private String id;
}
