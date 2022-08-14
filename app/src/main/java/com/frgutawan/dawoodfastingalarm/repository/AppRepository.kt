package com.frgutawan.dawoodfastingalarm.repository

import android.content.Context
import com.frgutawan.dawoodfastingalarm.data.alarm.AlarmDataSource
import com.frgutawan.dawoodfastingalarm.data.datastore.AppDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val dataStore: AppDataStore,
    private val alarmDataSource: AlarmDataSource,
    ) {

    // Save language into datastore
    suspend fun saveLanguage(lang:String) = dataStore.saveLanguage(lang)

    // Get language from datastore
    fun getLanguage() = dataStore.language

    // Save isDawoodFastingAlarmActive into datastore
    suspend fun saveIsDawoodFastingAlarmActive(isActive:Boolean) = dataStore.saveIsDawoodFastingAlarmActive(isActive)

    // Get isDawoodFastingAlarmActive from datastore
    fun getIsDawoodFastingAlarmActive() = dataStore.IsDawoodFastingAlarmActive

    // Save isDawoodFastingReminderActive into datastore
    suspend fun saveIsDawoodFastingReminderActive(isActive: Boolean) = dataStore.saveIsDawoodFastingReminderActive(isActive)

    // Get isDawoodFastingReminderActive from datastore
    fun getIsDawoodFastingReminderActive() = dataStore.isDawoodFastingReminderActive

    // Save DawoodFastingAlarmClock into datastore
    suspend fun saveDawoodFastingAlarmClock(hour:String, minute:String) = dataStore.saveDawoodAlarmClock(hour, minute)

    // Get DawoodFastingAlarmClock from datastore
    fun getDawoodFastingAlarmClock() = dataStore.dawoodAlarmClock

    // Save DawoodFastingReminder into datastore
    suspend fun saveDawoodFastingReminderClock(hour:String, minute: String) = dataStore.saveDawoodReminderClock(hour, minute)

    // Get DawoodFastingReminder from datastore
    fun getDawoodFastingReminderClock() = dataStore.dawoodReminderClock

    // Set Dawod Fasting Alarm
    fun setDawoodFastingAlarm(hour:Int, minute:Int, context: Context) = alarmDataSource.setDawoodAlarm(hour, minute, context)

    // Cancel Dawood Fasting Alarm
    fun cancelDawoodFastingAlarm(context: Context) = alarmDataSource.cancelDawoodAlarm(context)

    // Set Dawood Fasting Reminder

    // Cancel Dawood Fasting Reminder
}