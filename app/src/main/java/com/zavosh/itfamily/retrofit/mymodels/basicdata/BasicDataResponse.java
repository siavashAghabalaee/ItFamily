package com.zavosh.itfamily.retrofit.mymodels.basicdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.itfamily.retrofit.mymodels.Status;

public class BasicDataResponse {
    @SerializedName("result")
    @Expose
    private BasicDataResult result;
    @SerializedName("status")
    @Expose
    private Status status;

    public BasicDataResult getResult() {
        return result;
    }

    public void setResult(BasicDataResult result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
