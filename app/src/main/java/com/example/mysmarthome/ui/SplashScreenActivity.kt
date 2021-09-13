package com.example.mysmarthome.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mysmarthome.data.local.roomdatabase.MySmartHomeDatabase.Companion.DATABASE_NAME
import dagger.hilt.android.AndroidEntryPoint


//OPTIONAL TODO : Use SplashScreen API instead of a custom one
@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    private val viewModel by viewModels<SplashScreenVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*deleteDatabase(DATABASE_NAME)
        viewModel.changeConnectionValue(true)*/

        viewModel.userFirstConnection.observe(this){ firstConnection ->
            if (firstConnection) {
                viewModel.changeConnectionValue(false)
                viewModel.loadDataFromRemote()
                viewModel.dataRetrieved.observe(this){ dataRetrieved ->
                    if(dataRetrieved)
                        startMainActivity()
                }
            } else startMainActivity()
        }
    }

    private fun startMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }

}