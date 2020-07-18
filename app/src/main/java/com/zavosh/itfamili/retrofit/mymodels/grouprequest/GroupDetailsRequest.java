package com.zavosh.itfamili.retrofit.mymodels.grouprequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavosh.itfamili.retrofit.mymodels.Status;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GroupDetailsRequest {
    @SerializedName("result")
    @Expose
    private ArrayList<GroupDetails> result = null;
    @SerializedName("status")
    @Expose
    private Status status;

    public ArrayList<GroupDetails> getResult() {
        return result;
    }

    public void setResult(ArrayList<GroupDetails> result) {
        this.result = result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
