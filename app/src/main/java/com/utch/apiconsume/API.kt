package com.utch.apiconsume

import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("/products")
    fun get() : Call<Padre>
}