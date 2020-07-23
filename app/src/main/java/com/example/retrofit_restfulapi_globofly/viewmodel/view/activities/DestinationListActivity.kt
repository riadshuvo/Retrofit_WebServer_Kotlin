package com.example.retrofit_restfulapi_globofly.viewmodel.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_restfulapi_globofly.R
import com.example.retrofit_restfulapi_globofly.viewmodel.view.adapter.DestinationAdapter
import com.example.retrofit_restfulapi_globofly.viewmodel.mainViewModel.DestinationListActivityViewModel
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_destination_list.*

class DestinationListActivity : AppCompatActivity() {

    private lateinit var viewModel: DestinationListActivityViewModel
    private lateinit var mAdapter: DestinationAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_list)
        Stetho.initializeWithDefaults(this)


        viewModel = ViewModelProvider(this).get(DestinationListActivityViewModel::class.java)

        // setSupportActionBar(toolbar)
        toolbar.title = title

        viewModel.loadDestinationList().observe(this, Observer {

            mAdapter.destinationList(it)
        })

        mAdapter = DestinationAdapter(this)
        destiny_recycler_view.adapter = mAdapter


        fab.setOnClickListener {
            val intent = Intent(this, DestinationCreateActivity::class.java)
            startActivity(intent)
        }
    }

}