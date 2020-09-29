package com.zavosh.itfamily.retrofit.mymodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchSender {
    @SerializedName("SearchQuery")
    @Expose
    private String searchQuery ="";

    public SearchSender(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}
