package com.zavosh.itfamili.retrofit.mymodels.contentlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zavosh.itfamili.retrofit.mymodels.Status

data class ContentRequest (@SerializedName("result") @Expose
                           var result : ArrayList<GroupItem>,
                           @SerializedName("status") @Expose
                           var status :Status)