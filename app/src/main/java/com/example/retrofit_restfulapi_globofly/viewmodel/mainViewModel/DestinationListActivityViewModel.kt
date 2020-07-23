package com.example.retrofit_restfulapi_globofly.viewmodel.mainViewModel

import android.app.Application
import android.telecom.Call
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit_restfulapi_globofly.networkHelper.models.Destination
import com.example.retrofit_restfulapi_globofly.repository.DestinationListActivityRepository

class DestinationListActivityViewModel(application: Application) : AndroidViewModel(application) {

    private  var getDestinationList : MutableLiveData<List<Destination>> = MutableLiveData()
    private val mRepository: DestinationListActivityRepository = DestinationListActivityRepository(application)

    init {

        getDestinationList = mRepository.loadDestinations()
    }

    fun loadDestinationList(): LiveData<List<Destination>>{
      return  getDestinationList
    }

}