package com.example.mysmarthome

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.mysmarthome.data.local.datastore.UserPreferences
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltAndroidApp
class MySmartHomeApp : Application() {
    @Inject
    lateinit var userPreferences: UserPreferences
    override fun onCreate() {
        super.onCreate()
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED) {
            CoroutineScope(Dispatchers.IO).launch {
                userPreferences.setAppTheme()
            }
        }
    }
}