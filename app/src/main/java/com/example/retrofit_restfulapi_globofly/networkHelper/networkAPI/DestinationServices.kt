package com.example.retrofit_restfulapi_globofly.networkHelper.networkAPI

import com.example.retrofit_restfulapi_globofly.networkHelper.models.Destination
import retrofit2.Call
import retrofit2.http.*

interface DestinationServices {

    //    //Query With single value/key
//    @GET("destination")
//    fun getDestinatonList(@Query("country") country: String?): Call<List<Destination>>

    //Query With multiple value/key we hava to use QueryMap
    @GET("destination")
    fun getDestinatonList(@QueryMap queryData: HashMap<String, String>): Call<List<Destination>>

    @GET("destination/{id}")
    fun getDestination(@Path("id") id: Int): Call<Destination>

    @POST("destination")
    fun createDestination(@Body newDestination: Destination): Call<Destination>

    //To Have a Body Of FormUrl Encoded Format
    @FormUrlEncoded
    @PUT("destination/{id}")
    fun updateDestination(
        @Path("id") id: Int,
        @Field("city") city: String,
        @Field("description") des: String,
        @Field("country") country: String
    ): Call<Destination>

    @DELETE("destination/{id}")
    fun deleteDestination(@Path("id") id: Int): Call<Destination>

}