package com.zavosh.itfamily.retrofit.mymodels.postprofilerequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostProfileSender {
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("IsMale")
    @Expose
    private String isMale;

    public PostProfileSender(String fullName, String email, String isMale) {
        this.fullName = fullName;
        this.email = email;
        this.isMale = isMale;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsMale() {
        return isMale;
    }

    public void setIsMale(String isMale) {
        this.isMale = isMale;
    }
}
