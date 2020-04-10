
package com.zavosh.itfamili.retrofit.mymodels.videolistrequest;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.itfamili.retrofit.mymodels.Status;

public class VideoListRequest {

    @SerializedName("result")
    @Expose
    private List<VideoListResult> result = null;
    @SerializedName("status")
    @Expose
    private Status status;

    public List<VideoListResult> getResult() {
        return result;
    }

    public void setResult(List<VideoListResult> result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
