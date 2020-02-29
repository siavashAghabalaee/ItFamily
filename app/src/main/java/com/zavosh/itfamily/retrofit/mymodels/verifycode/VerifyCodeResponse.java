package com.zavosh.itfamily.retrofit.mymodels.verifycode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.itfamily.retrofit.mymodels.Status;

public class VerifyCodeResponse {
    @SerializedName("result")
    @Expose
    private VerifyCodeResult result;
    @SerializedName("status")
    @Expose
    private Status status;

    public VerifyCodeResponse(VerifyCodeResult result, Status status) {
        this.result = result;
        this.status = status;
    }

    public VerifyCodeResult getResult() {
        return result;
    }

    public void setResult(VerifyCodeResult result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
