package com.example.mysmarthome.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.mysmarthome.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    private val viewModel by viewModels<SplashScreenVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*  deleteDatabase(DATABASE_NAME)
            viewModel.changeConnectionValue(true)*/

        viewModel.currentTheme.observe(this){ nightMode ->
            when {
                nightMode == null -> viewModel.setAppTheme()
                nightMode -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        viewModel.userFirstConnection.observe(this) { firstConnection ->
            if (firstConnection) {
                viewModel.changeConnectionValue(false)
                viewModel.loadDataFromRemote()
                viewModel.dataRetrieved.observe(this) { dataRetrieved ->
                    if (dataRetrieved)
                        startMainActivity()
                }
            } else startMainActivity()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }
}