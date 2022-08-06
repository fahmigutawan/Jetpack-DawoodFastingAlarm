package com.frgutawan.dawoodfastingalarm.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dawood_fasting_alarm")

@Singleton
class AppDataStore @Inject constructor(
    private val context: Context
) {
    /**Language Pref*/
    suspend fun saveLanguage(lang:String){
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey("language")] = lang
        }
    }
    val language = context.dataStore.data.map { preferences ->
        preferences[stringPreferencesKey("language")] ?: "english"
    }

    /**IsDawoodFastingAlarmActive Pref*/
    suspend fun saveIsDawoodFastingAlarmActive(isActive:Boolean){
        context.dataStore.edit { preferences ->
            preferences[booleanPreferencesKey("is_dawood_fasting_alarm_active")] = isActive
        }
    }
    val IsDawoodFastingAlarmActive = context.dataStore.data.map { prefereces ->
        prefereces[booleanPreferencesKey("is_dawood_fasting_alarm_active")] ?: false
    }

    /**IsDawoodFastingReminderActive Pref*/
    suspend fun saveIsDawoodFastingReminderActive(isActive: Boolean){
        context.dataStore.edit { preferences ->
            preferences[booleanPreferencesKey("is_dawood_fasting_reminder_active")] = isActive
        }
    }
    val isDawoodFastingReminderActive = context.dataStore.data.map { preferences ->
        preferences[booleanPreferencesKey("is_dawood_fasting_reminder_active")] ?: false
    }
}