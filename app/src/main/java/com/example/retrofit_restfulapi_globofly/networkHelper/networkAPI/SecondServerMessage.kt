package com.example.retrofit_restfulapi_globofly.networkHelper.networkAPI

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface SecondServerMessage {

    @GET
    fun getMessages(@Url anotherUrl: String): Call<String>

}