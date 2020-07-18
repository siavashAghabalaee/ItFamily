package com.zavosh.itfamily.retrofit.mymodels.commentrequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.itfamily.retrofit.mymodels.Status;

import java.util.List;

public class CommentRequest {
    @SerializedName("result")
    @Expose
    private List<CommentResult> result = null;
    @SerializedName("status")
    @Expose
    private Status status;

    public List<CommentResult> getResult() {
        return result;
    }

    public void setResult(List<CommentResult> result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
