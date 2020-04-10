package com.zavosh.itfamili.retrofit.mymodels.sendcommentrequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendCommentSender {
    @SerializedName("ContentId")
    @Expose
    private String contentId;
    @SerializedName("Comment")
    @Expose
    private String comment;

    public SendCommentSender(String contentId, String comment) {
        this.contentId = contentId;
        this.comment = comment;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
