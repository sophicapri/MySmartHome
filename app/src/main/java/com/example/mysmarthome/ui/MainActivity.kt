package com.example.mysmarthome.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mysmarthome.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

// OPTIONAL TODO: Add SavedStateHandle() in all the view models
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}