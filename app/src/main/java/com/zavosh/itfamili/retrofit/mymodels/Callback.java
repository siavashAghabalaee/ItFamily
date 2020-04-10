package com.zavosh.itfamili.retrofit.mymodels;

import com.zavosh.itfamili.retrofit.mymodels.bloglistrequest.BlogListResult;
import com.zavosh.itfamili.retrofit.mymodels.commentrequest.CommentResult;
import com.zavosh.itfamili.retrofit.mymodels.homeRequest.HomeResult;
import com.zavosh.itfamili.retrofit.mymodels.magazinerequest.MagazineResult;
import com.zavosh.itfamili.retrofit.mymodels.podcastlistrequest.PodcastListResult;
import com.zavosh.itfamili.retrofit.mymodels.questionListlistrequest.QuestionListResult;
import com.zavosh.itfamili.retrofit.mymodels.registerphone.RegisterResult;
import com.zavosh.itfamili.retrofit.mymodels.verifycode.VerifyCodeResult;
import com.zavosh.itfamili.retrofit.mymodels.videolistrequest.VideoListResult;

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

    interface VideoList{
        void callback(List<VideoListResult> result);
    }

    interface PodcastList{
        void callback(List<PodcastListResult> result);
    }

    interface QuestionList{
        void callback(List<QuestionListResult> result);
    }

    interface PostQuestion{
        void callback(String result);
    }

    interface GetComment{
        void callback(List<CommentResult> result);
    }

    interface SendComment{
        void callback();
    }
}
