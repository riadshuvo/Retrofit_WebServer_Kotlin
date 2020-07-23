package com.example.retrofit_restfulapi_globofly.viewmodel.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofit_restfulapi_globofly.R
import com.example.retrofit_restfulapi_globofly.networkHelper.models.Destination
import com.example.retrofit_restfulapi_globofly.networkHelper.networkAPI.DestinationServices
import com.example.retrofit_restfulapi_globofly.networkHelper.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destination_create.*
import kotlinx.android.synthetic.main.activity_destination_detail.collapsing_toolbar
import kotlinx.android.synthetic.main.activity_destination_detail.et_city
import kotlinx.android.synthetic.main.activity_destination_detail.et_country
import kotlinx.android.synthetic.main.activity_destination_detail.et_description
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationCreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_create)

        setSupportActionBar(toolbar)
        collapsing_toolbar.title = "New Destination"
        val context = this

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_add.setOnClickListener {
            val newDestination = Destination()
            newDestination.city = et_city.text.toString()
            newDestination.description = et_description.text.toString()
            newDestination.country = et_country.text.toString()

            val destinationServices = ServiceBuilder.buildService(DestinationServices::class.java)
            val requestCall = destinationServices.createDestination(newDestination)
            requestCall.enqueue(object : Callback<Destination> {

                override fun onFailure(call: Call<Destination>, t: Throwable) {
                    Toast.makeText(
                        this@DestinationCreateActivity,
                        "DestinationCreateActivity: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                    if (response.isSuccessful) {
                        finish()
                        val newlyCreatedDestination = response.body()
                        Toast.makeText(
                            this@DestinationCreateActivity,
                            "JSON Successfully Created",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else{
                        Toast.makeText(
                            this@DestinationCreateActivity,
                            "Failed To Add Item",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        }
    }

}