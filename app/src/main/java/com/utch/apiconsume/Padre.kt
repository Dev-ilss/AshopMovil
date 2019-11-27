package com.utch.apiconsume

import com.google.gson.annotations.SerializedName

data class Padre (
    @SerializedName("productos")
    var productos : ArrayList<Products>
)