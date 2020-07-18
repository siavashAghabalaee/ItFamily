package com.zavosh.itfamily.retrofit.mymodels.registerphone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResult {
    @SerializedName("userCode")
    @Expose
    private String userCode;

    @SerializedName("tokenId")
    @Expose
    private String tokenId;

    public RegisterResult(String userCode, String tokenId) {
        this.userCode = userCode;
        this.tokenId = tokenId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
