package com.zavosh.itfamily.retrofit.mymodels.commentrequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentSender {
    @SerializedName("ContentId")
    @Expose
    private String contentId;
    @SerializedName("PageId")
    @Expose
    private int pageId;

    public CommentSender(String contentId, int pageId) {
        this.contentId = contentId;
        this.pageId = pageId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
}
