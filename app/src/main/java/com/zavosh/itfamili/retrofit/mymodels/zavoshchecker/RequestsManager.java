package com.zavosh.itfamili.retrofit.mymodels.zavoshchecker;

public interface RequestsManager {
    void resendRequest(int id);
    void setMessageForProgressBar(String message , int id);
}
