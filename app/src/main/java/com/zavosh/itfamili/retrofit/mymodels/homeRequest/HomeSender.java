package com.zavosh.itfamili.retrofit.mymodels.homeRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeSender {
    @SerializedName("Version")
    @Expose
    private String version;

    @SerializedName("OsType")
    @Expose
    private String osType;

    public HomeSender(String version, String osType) {
        this.version = version;
        this.osType = osType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }
}
