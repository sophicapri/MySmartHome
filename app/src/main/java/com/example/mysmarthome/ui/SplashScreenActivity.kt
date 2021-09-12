package com.example.mysmarthome.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mysmarthome.data.local.roomdatabase.MySmartHomeDatabase.Companion.DATABASE_NAME
import dagger.hilt.android.AndroidEntryPoint


//OPTIONAL TODO : Use SplashScreen API instead of a custom one
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        deleteDatabase(DATABASE_NAME)

        viewModel.userFirstConnection.observe(this){
            Log.d("MainActivity", "onCreate: firstConnection = $it ")
            if (it == true) {
                viewModel.changeConnectionValue(false)
                //viewModel.loadDataFromRemote()
            }
            viewModel.loadDataFromRemote()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        //
    }

}