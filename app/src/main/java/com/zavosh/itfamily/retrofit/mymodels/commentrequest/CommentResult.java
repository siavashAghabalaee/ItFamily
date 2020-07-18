package com.zavosh.itfamily.retrofit.mymodels.commentrequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentResult {
    @SerializedName("userFullName")
    @Expose
    private String userFullName;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("commentDate")
    @Expose
    private String commentDate;

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

}
