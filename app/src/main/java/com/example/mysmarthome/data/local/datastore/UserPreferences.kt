package com.example.mysmarthome.data.local.datastore

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class UserPreferences(var context: Context) {
    private val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES_NAME)

    val firstConnection: Flow<Boolean> = context.dataStore.data.catch { exception ->
        if (exception is IOException)
            emit(emptyPreferences())
        else {
            Log.e("UserPreferences", "${exception.message}")
            //throw exception
        }
    }.map { it: Preferences -> it[PreferencesKeys.FIRST_CONNECTION_KEY] ?: true }

    suspend fun changeConnectionValue(value: Boolean) {
        context.dataStore.edit {
            it[PreferencesKeys.FIRST_CONNECTION_KEY] = value
        }
    }

    suspend fun toggleNightMode(): Boolean {
        context.dataStore.edit {
            it[PreferencesKeys.NIGHT_MODE_KEY] = !(it[PreferencesKeys.NIGHT_MODE_KEY] ?: false)
        }
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        return true
    }

    suspend fun initAppTheme() {
        context.dataStore.edit { it[PreferencesKeys.NIGHT_MODE_KEY] = false }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    suspend fun setAppTheme() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED) {
            Log.d("MySmartHome", "onCreate: default night mode unspecified ?")
            context.dataStore.edit { it[PreferencesKeys.NIGHT_MODE_KEY] = false }
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        var nightModeOn = false
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.data.catch { exception ->
                if (exception is IOException)
                    emit(emptyPreferences())
                else {
                    Log.e("UserPreferences", "${exception.message}")
                    //throw exception
                }
            }.collect {
                nightModeOn = it[PreferencesKeys.NIGHT_MODE_KEY] ?: false
            }
            withContext(Dispatchers.Main) {
                if (!nightModeOn)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                else
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    private object PreferencesKeys {
        val NIGHT_MODE_KEY = booleanPreferencesKey("dark_theme_enabled")
        val FIRST_CONNECTION_KEY = booleanPreferencesKey("first_user_connection")
    }

    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
    }
}