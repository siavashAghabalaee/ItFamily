
package com.zavosh.itfamili.retrofit.mymodels.questionListlistrequest;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.itfamili.retrofit.mymodels.Status;

public class QuestionListRequest {

    @SerializedName("result")
    @Expose
    private List<QuestionListResult> result = null;
    @SerializedName("status")
    @Expose
    private Status status;

    public List<QuestionListResult> getResult() {
        return result;
    }

    public void setResult(List<QuestionListResult> result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
