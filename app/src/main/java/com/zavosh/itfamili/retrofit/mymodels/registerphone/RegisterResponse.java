package com.zavosh.itfamili.retrofit.mymodels.registerphone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.itfamili.retrofit.mymodels.Status;

public class RegisterResponse {
    @SerializedName("result")
    @Expose
    private RegisterResult result;

    @SerializedName("status")
    @Expose
    private Status status;

    public RegisterResponse(RegisterResult result, Status status) {
        this.result = result;
        this.status = status;
    }

    public RegisterResult getResult() {
        return result;
    }

    public void setResult(RegisterResult result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
