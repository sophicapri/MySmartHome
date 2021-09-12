package com.example.mysmarthome.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.mysmarthome.R
import com.example.mysmarthome.databinding.ActivityMainBinding
import com.example.mysmarthome.model.Device
import com.example.mysmarthome.model.Light
import com.example.mysmarthome.model.ProductType
import com.example.mysmarthome.repository.DeviceRepository
import com.example.mysmarthome.ui.devicelist.DeviceListVM
import dagger.hilt.android.AndroidEntryPoint

// OPTIONAL TODO: Add SavedStateHandle() in all the view models
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}