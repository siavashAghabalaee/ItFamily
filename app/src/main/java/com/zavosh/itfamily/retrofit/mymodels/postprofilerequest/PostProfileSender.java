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
    @SerializedName("AgeRange")
    @Expose
    private String age;
    @SerializedName("Job")
    @Expose
    private String job;
    @SerializedName("EducationRate")
    @Expose
    private String education;

    public PostProfileSender(String fullName, String email, String isMale,String age,String job,String education) {
        this.fullName = fullName;
        this.email = email;
        this.isMale = isMale;
        this.age = age;
        this.job = job;
        this.education = education;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
