
package com.zavosh.itfamily.retrofit.mymodels.homeRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.itfamily.retrofit.mymodels.Status;

public class HomeRequest {

    @SerializedName("result")
    @Expose
    private HomeResult result;
    @SerializedName("status")
    @Expose
    private Status status;

    public HomeResult getResult() {
        return result;
    }

    public void setResult(HomeResult result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
