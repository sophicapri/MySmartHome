package com.example.mysmarthome.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


//OPTIONAL TODO : Use SplashScreen API instead of a custom one
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.userFirstConnection.observe(this){
            Log.d("MainActivity", "onCreate: firstConnection = $it ")
            if (it == false)
                viewModel.changeConnectionValue(true)
        }
        //
    }

}