package com.zavosh.itfamily.retrofit.mymodels.loginrequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.itfamily.retrofit.mymodels.Status;

public class LoginRequest {
    @SerializedName("result")
    @Expose
    private LoginResult result;

    @SerializedName("status")
    @Expose
    private Status status;

    public LoginRequest(LoginResult result, Status status) {
        this.result = result;
        this.status = status;
    }

    public LoginResult getResult() {
        return result;
    }

    public void setResult(LoginResult result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
