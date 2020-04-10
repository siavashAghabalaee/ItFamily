
package com.zavosh.itfamili.retrofit.mymodels.magazinerequest;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.itfamili.retrofit.mymodels.Status;

public class MagazineRequest {

    @SerializedName("result")
    @Expose
    private List<MagazineResult> result = null;
    @SerializedName("status")
    @Expose
    private Status status;

    public List<MagazineResult> getResult() {
        return result;
    }

    public void setResult(List<MagazineResult> result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
