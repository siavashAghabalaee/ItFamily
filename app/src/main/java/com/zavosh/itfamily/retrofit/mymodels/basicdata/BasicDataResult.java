package com.zavosh.itfamily.retrofit.mymodels.basicdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BasicDataResult {
    @SerializedName("ageRangeList")
    @Expose
    private ArrayList<String> ageRangeList = null;
    @SerializedName("educationRateList")
    @Expose
    private ArrayList<String> educationRateList = null;
    @SerializedName("jobTypeList")
    @Expose
    private ArrayList<String> jobTypeList = null;

    public ArrayList<String> getAgeRangeList() {
        return ageRangeList;
    }

    public void setAgeRangeList(ArrayList<String> ageRangeList) {
        this.ageRangeList = ageRangeList;
    }

    public ArrayList<String> getEducationRateList() {
        return educationRateList;
    }

    public void setEducationRateList(ArrayList<String> educationRateList) {
        this.educationRateList = educationRateList;
    }

    public ArrayList<String> getJobTypeList() {
        return jobTypeList;
    }

    public void setJobTypeList(ArrayList<String> jobTypeList) {
        this.jobTypeList = jobTypeList;
    }
}
