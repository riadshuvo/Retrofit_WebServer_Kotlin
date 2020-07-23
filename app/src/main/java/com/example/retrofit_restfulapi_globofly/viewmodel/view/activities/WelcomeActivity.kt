package com.example.retrofit_restfulapi_globofly.viewmodel.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit_restfulapi_globofly.R
import com.example.retrofit_restfulapi_globofly.viewmodel.mainViewModel.WelcomeActivityViewModel
import kotlinx.android.synthetic.main.activity_welcome.*
class WelcomeActivity : AppCompatActivity() {

    private lateinit var viewModel : WelcomeActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        viewModel = ViewModelProvider(this).get(WelcomeActivityViewModel::class.java)

        viewModel.welcomeMessage.observe(this, Observer {
            message.text = it
        })

    }

        fun getStarted(view: View) {
        val intent = Intent(this, DestinationListActivity::class.java)
        startActivity(intent)
        finish()
    }


}