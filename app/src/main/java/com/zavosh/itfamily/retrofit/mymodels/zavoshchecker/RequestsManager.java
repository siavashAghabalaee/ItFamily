package com.zavosh.itfamily.retrofit.mymodels.zavoshchecker;

public interface RequestsManager {
    void resendRequest(int id);
    void setMessageForProgressBar(String message , int id);
}
