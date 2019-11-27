package com.utch.apiconsume

import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("titulo")
    var titulo  : String,
    @SerializedName("url")
    var url : String,
    @SerializedName("precio")
    var precio : String,
    @SerializedName("imgurl")
    var imagen : String
)