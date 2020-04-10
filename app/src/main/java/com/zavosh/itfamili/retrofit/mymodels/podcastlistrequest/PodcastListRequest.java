
package com.zavosh.itfamili.retrofit.mymodels.podcastlistrequest;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.itfamili.retrofit.mymodels.Status;

public class PodcastListRequest {

    @SerializedName("result")
    @Expose
    private List<PodcastListResult> result = null;
    @SerializedName("status")
    @Expose
    private Status status;

    public List<PodcastListResult> getResult() {
        return result;
    }

    public void setResult(List<PodcastListResult> result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
