package com.example.retrofit_restfulapi_globofly.viewmodel.mainViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.retrofit_restfulapi_globofly.repository.WelcomeActivityRepository

class WelcomeActivityViewModel(application: Application): AndroidViewModel(application) {

    private val repository = WelcomeActivityRepository(application)

     val welcomeMessage : LiveData<String>

    init {
        repository.getWelcomeMessage()
        this.welcomeMessage = repository.welcomeMessage
    }

}