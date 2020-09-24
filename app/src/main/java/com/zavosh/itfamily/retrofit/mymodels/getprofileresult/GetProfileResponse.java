package com.zavosh.itfamily.retrofit.mymodels.getprofileresult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.itfamily.retrofit.mymodels.Status;

public class GetProfileResponse {
    @SerializedName("result")
    @Expose
    private GetProfileResult result;
    @SerializedName("status")
    @Expose
    private Status status;

    public GetProfileResult getResult() {
        return result;
    }

    public void setResult(GetProfileResult result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
