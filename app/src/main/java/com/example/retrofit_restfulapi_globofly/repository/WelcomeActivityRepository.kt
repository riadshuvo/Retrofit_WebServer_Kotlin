package com.example.retrofit_restfulapi_globofly.repository

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.retrofit_restfulapi_globofly.networkHelper.networkAPI.SecondServerMessage
import com.example.retrofit_restfulapi_globofly.networkHelper.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WelcomeActivityRepository(val application: Application) {

    val welcomeMessage = MutableLiveData<String>()

    fun getWelcomeMessage() {

        //Getting Welcome Message From Another Server
        val getMessageInstance = ServiceBuilder.buildService(SecondServerMessage::class.java)
        val getWellcomeMessageClient =
            getMessageInstance.getMessages("http://10.0.2.2:7000/messages")

        getWellcomeMessageClient.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(application, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val messages = response.body()
                welcomeMessage.value = messages
            }

        })

    }

}