package com.zavosh.itfamili.retrofit.mymodels.loginrequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginSender {
    @SerializedName("CellNumber")
    @Expose
    private String cellNumber;

    @SerializedName("Password")
    @Expose
    private String password;

    public LoginSender(String cellNumber, String password) {
        this.cellNumber = cellNumber;
        this.password = password;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
