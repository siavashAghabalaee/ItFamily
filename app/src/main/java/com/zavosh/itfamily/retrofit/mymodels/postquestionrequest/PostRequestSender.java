package com.zavosh.itfamily.retrofit.mymodels.postquestionrequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostRequestSender {
    @SerializedName("subject")
    @Expose
    private String subject;

    @SerializedName("message")
    @Expose
    private String message;

    public PostRequestSender(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
