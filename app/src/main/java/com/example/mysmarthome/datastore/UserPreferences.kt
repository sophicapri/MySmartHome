package com.example.mysmarthome.datastore

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferences(var context: Context) {
    private val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES_NAME)
    val firstConnection: Flow<Boolean> = context.dataStore.data.map { it: Preferences -> it[PreferencesKeys.FIRST_CONNECTION_KEY] ?: true }
    val currentTheme: Flow<Boolean?> = context.dataStore.data.map { it: Preferences -> it[PreferencesKeys.NIGHT_MODE_KEY] }

    suspend fun changeConnectionValue(value: Boolean) {

        context.dataStore.edit {
            it[PreferencesKeys.FIRST_CONNECTION_KEY] = value

        }
    }

    suspend fun toggleNightMode() {
        context.dataStore.edit {
            it[PreferencesKeys.NIGHT_MODE_KEY] = !(it[PreferencesKeys.NIGHT_MODE_KEY] ?: false)
        }
    }

    suspend fun setAppTheme() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED) {
            context.dataStore.edit { it[PreferencesKeys.NIGHT_MODE_KEY] = false }
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private object PreferencesKeys {
        val NIGHT_MODE_KEY = booleanPreferencesKey("dark_theme_enabled")
        val FIRST_CONNECTION_KEY = booleanPreferencesKey("first_user_connection")
    }

    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
        private const val TAG = "UserPreferences"
    }
}