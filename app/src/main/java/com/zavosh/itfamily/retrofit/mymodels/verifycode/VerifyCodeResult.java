package com.zavosh.itfamily.retrofit.mymodels.verifycode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyCodeResult {
    @SerializedName("tokenId")
    @Expose
    private String tokenId;

    public VerifyCodeResult(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
