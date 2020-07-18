package com.zavosh.itfamily.retrofit.mymodels.grouprequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupDetailsSender {
    @SerializedName("ContentGroupId")
    @Expose
    private String id;

    public GroupDetailsSender(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
