package com.example.mysmarthome.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.mysmarthome.R
import com.example.mysmarthome.repository.DeviceRepository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getDeviceLight().observe(this){
            Log.d("MainActivity", "onCreate: LIGHT LIST = $it")
        }

        viewModel.getUser().observe(this){
            Log.d("MainActivity", "onCreate: USER = $it")
        }
    }
}