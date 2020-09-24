package com.zavosh.itfamily.retrofit.mymodels.getprofileresult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetProfileResult {
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("cellNumber")
    @Expose
    private String cellNumber;
    @SerializedName("ageRange")
    @Expose
    private String ageRange;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("isMale")
    @Expose
    private String isMale;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("educationRate")
    @Expose
    private String educationRate;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getIsMale() {
        return isMale;
    }

    public void setIsMale(String isMale) {
        this.isMale = isMale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducationRate() {
        return educationRate;
    }

    public void setEducationRate(String educationRate) {
        this.educationRate = educationRate;
    }
}
