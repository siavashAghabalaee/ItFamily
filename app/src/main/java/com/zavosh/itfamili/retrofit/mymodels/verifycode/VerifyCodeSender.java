package com.zavosh.itfamili.retrofit.mymodels.verifycode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyCodeSender {
    @SerializedName("tokenId")
    @Expose
    private String tokenId;

    @SerializedName("activationCode")
    @Expose
    private String activationCode;

    @SerializedName("deviceId")
    @Expose
    private String deviceId;

    @SerializedName("deviceModel")
    @Expose
    private String deviceModel;

    @SerializedName("OsType")
    @Expose
    private String OsType;

    @SerializedName("OsVersion")
    @Expose
    private String OsVersion;

    public VerifyCodeSender(String tokenId, String activationCode, String deviceId, String deviceModel, String osType, String osVersion) {
        this.tokenId = tokenId;
        this.activationCode = activationCode;
        this.deviceId = deviceId;
        this.deviceModel = deviceModel;
        OsType = osType;
        OsVersion = osVersion;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getOsType() {
        return OsType;
    }

    public void setOsType(String osType) {
        OsType = osType;
    }

    public String getOsVersion() {
        return OsVersion;
    }

    public void setOsVersion(String osVersion) {
        OsVersion = osVersion;
    }
}
