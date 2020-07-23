package com.example.retrofit_restfulapi_globofly.repository

import android.app.Application
import android.content.Context
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.lifecycle.MutableLiveData
import com.example.retrofit_restfulapi_globofly.networkHelper.models.Destination
import com.example.retrofit_restfulapi_globofly.networkHelper.networkAPI.DestinationServices
import com.example.retrofit_restfulapi_globofly.networkHelper.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationListActivityRepository(val application: Application) {

    val getDestinationList = MutableLiveData<List<Destination>>()

    fun loadDestinations(): MutableLiveData<List<Destination>> {

        //For Query Map
        val querydata = HashMap<String, String>()
//        querydata["country"] = "India"
//        querydata["count"] = "1"

        val destinationService = ServiceBuilder.buildService(DestinationServices::class.java)
        val requestCall = destinationService.getDestinatonList(querydata)

        //If User Wants To Cancel His Request To The Server or Cancel Download Data

//        requestCall.cancel()
//        requestCall.isCanceled //this is boolean

        requestCall.enqueue(object : Callback<List<Destination>> {

            override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
                makeText(
                    application,
                    "DestinationListActivity: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(
                call: Call<List<Destination>>,
                response: Response<List<Destination>>
            ) {
                if (response.isSuccessful) {
                    getDestinationList.value = response.body()

                }
            }
        })

        return getDestinationList

    }


}