package com.example.diet

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("homepage_v2/")
    fun getData() : Call<Homepage>
}