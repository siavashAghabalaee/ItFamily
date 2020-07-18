package com.zavosh.itfamily.retrofit.mymodels.registerphone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterSender {
    @SerializedName("CellNumber")
    @Expose
    private String cellNumber;


    public RegisterSender(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }



}
