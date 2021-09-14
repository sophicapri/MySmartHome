package com.example.mysmarthome.data.local.datastore

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
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

    suspend fun toggleNightMode() : Boolean {
        context.dataStore.edit {
            it[PreferencesKeys.NIGHT_MODE_KEY] = !(it[PreferencesKeys.NIGHT_MODE_KEY] ?: false)
        }
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        return true
    }

    suspend fun setAppTheme() {
        context.dataStore.edit { it[PreferencesKeys.NIGHT_MODE_KEY] = false }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private object PreferencesKeys {
        val NIGHT_MODE_KEY = booleanPreferencesKey("dark_theme_enabled")
        val FIRST_CONNECTION_KEY = booleanPreferencesKey("first_user_connection")
    }

    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
    }
}