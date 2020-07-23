package com.example.retrofit_restfulapi_globofly.viewmodel.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.retrofit_restfulapi_globofly.R
import com.example.retrofit_restfulapi_globofly.networkHelper.models.Destination
import com.example.retrofit_restfulapi_globofly.networkHelper.networkAPI.DestinationServices
import com.example.retrofit_restfulapi_globofly.networkHelper.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destination_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_detail)

       // setSupportActionBar(detail_toolbar)
        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle: Bundle? = intent.extras

        if (bundle?.containsKey(ARG_ITEM_ID)!!) {

            val id = intent.getIntExtra(ARG_ITEM_ID, 0)

            loadDetails(id)

            initUpdateButton(id)

            initDeleteButton(id)
        }
    }

    private fun loadDetails(id: Int) {

         val destinationServices = ServiceBuilder.buildService(DestinationServices::class.java)
        val requestCall = destinationServices.getDestination(id)
        requestCall.enqueue(object : Callback<Destination> {

            override fun onFailure(call: Call<Destination>, t: Throwable) {
                Toast.makeText(this@DestinationDetailActivity,"EROOR: ${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                if(response.isSuccessful){
                    val destination = response.body()
                    destination?.let {
                        et_city.setText(destination.city)
                        et_description.setText(destination.description)
                        et_country.setText(destination.country)

                        collapsing_toolbar.title = destination.city
                    }
                }
            }
        })

    }

    private fun initUpdateButton(id: Int) {

        btn_update.setOnClickListener {

            val city = et_city.text.toString()
            val description = et_description.text.toString()
            val country = et_country.text.toString()

            // To be replaced by retrofit code
            val destination = Destination()
            destination.id = id
            destination.city = city
            destination.description = description
            destination.country = country

            val destinationServices = ServiceBuilder.buildService(DestinationServices::class.java)
            val requestCall = destinationServices.updateDestination(id,city,description,country)
            requestCall.enqueue(object : Callback<Destination> {

                override fun onFailure(call: Call<Destination>, t: Throwable) {
                    Toast.makeText(
                        this@DestinationDetailActivity,
                        "DestinationDetailActivity: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                    if (response.isSuccessful) {
                        finish()

                        val newlyCreatedDestination = response.body() //Do Whatever You Want
                        println("updated jason: ${newlyCreatedDestination.toString()}")
                        Toast.makeText(
                            this@DestinationDetailActivity,
                            "JSON Successfully Updated",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else{
                        Toast.makeText(
                            this@DestinationDetailActivity,
                            "Failed To Add Item",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })

        }
    }

    private fun initDeleteButton(id: Int) {

        btn_delete.setOnClickListener {

            val destinationServices = ServiceBuilder.buildService(DestinationServices::class.java)
            val requestCall = destinationServices.deleteDestination(id)
            requestCall.enqueue(object : Callback<Destination> {

                override fun onFailure(call: Call<Destination>, t: Throwable) {
                    Toast.makeText(
                        this@DestinationDetailActivity,
                        "DestinationDetailActivity: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                    if (response.isSuccessful) {

                        val newlyCreatedDestination = response.body() //Do Whatever You Want
                        println("deleted jason: ${newlyCreatedDestination.toString()}")
                        Toast.makeText(
                            this@DestinationDetailActivity,
                            "Item Deleted",
                            Toast.LENGTH_LONG
                        ).show()
                        finish()
                    }
                    else{
                        Toast.makeText(
                            this@DestinationDetailActivity,
                            "Failed To Add Item",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            navigateUpTo(Intent(this, DestinationListActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    companion object {

        const val ARG_ITEM_ID = "item_id"
    }
}