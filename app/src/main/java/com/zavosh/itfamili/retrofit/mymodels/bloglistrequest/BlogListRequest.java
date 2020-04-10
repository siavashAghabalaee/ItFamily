
package com.zavosh.itfamili.retrofit.mymodels.bloglistrequest;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.itfamili.retrofit.mymodels.Status;

public class BlogListRequest {

    @SerializedName("result")
    @Expose
    private List<BlogListResult> result = null;
    @SerializedName("status")
    @Expose
    private Status status;

    public List<BlogListResult> getResult() {
        return result;
    }

    public void setResult(List<BlogListResult> result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
