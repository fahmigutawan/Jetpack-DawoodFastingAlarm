package com.frgutawan.dawoodfastingalarm.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.frgutawan.dawoodfastingalarm.model.DawoodAlarmClock
import com.frgutawan.dawoodfastingalarm.model.DawoodReminderClock
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "dawood_fasting_alarm")

@Singleton
class AppDataStore @Inject constructor(
    private val context: Context
) {
    /**Language Pref*/
    suspend fun saveLanguage(lang: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey("language")] = lang
        }
    }

    val language = context.dataStore.data.map { preferences ->
        preferences[stringPreferencesKey("language")] ?: "english"
    }
    /*[END]*/

    /**IsDawoodFastingAlarmActive Pref*/
    suspend fun saveIsDawoodFastingAlarmActive(isActive: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[booleanPreferencesKey("is_dawood_fasting_alarm_active")] = isActive
        }
    }

    val IsDawoodFastingAlarmActive = context.dataStore.data.map { prefereces ->
        prefereces[booleanPreferencesKey("is_dawood_fasting_alarm_active")] ?: false
    }
    /*[END]*/

    /**IsDawoodFastingReminderActive Pref*/
    suspend fun saveIsDawoodFastingReminderActive(isActive: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[booleanPreferencesKey("is_dawood_fasting_reminder_active")] = isActive
        }
    }

    val isDawoodFastingReminderActive = context.dataStore.data.map { preferences ->
        preferences[booleanPreferencesKey("is_dawood_fasting_reminder_active")] ?: false
    }
    /*[END]*/

    /**DawoodAlarm Clock*/
    suspend fun saveDawoodAlarmClock(hour: String, minute: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey("dawood_alarm_hour")] = hour
            preferences[stringPreferencesKey("dawood_alarm_minute")] = minute
        }
    }

    val dawoodAlarmClock = context.dataStore.data.map { preferences ->
        DawoodAlarmClock(
            hour = preferences[stringPreferencesKey("dawood_alarm_hour")] ?: "00",
            minute = preferences[stringPreferencesKey("dawood_alarm_minute")] ?: "00"
        )
    }
    /*[END]*/

    /**DawoodReminder Clock*/
    suspend fun saveDawoodReminderClock(hour: String, minute: String) =
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey("dawood_reminder_hour")] = hour
            preferences[stringPreferencesKey("dawood_reminder_minute")] = minute
        }

    val dawoodReminderClock = context.dataStore.data.map { preferences ->
        DawoodReminderClock(
            hour = preferences[stringPreferencesKey("dawood_reminder_hour")] ?: "00",
            minute = preferences[stringPreferencesKey("dawood_reminder_minute")] ?: "00"
        )
    }
    /*END*/

    /***/
}
