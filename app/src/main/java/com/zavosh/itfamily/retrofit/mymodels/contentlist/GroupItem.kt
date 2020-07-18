package com.zavosh.itfamily.retrofit.mymodels.contentlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GroupItem (@SerializedName("id") @Expose
                      var id : String,
                      @SerializedName("title") @Expose
                      var title : String)