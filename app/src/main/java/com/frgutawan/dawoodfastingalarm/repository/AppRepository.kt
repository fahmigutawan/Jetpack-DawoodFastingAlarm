package com.frgutawan.dawoodfastingalarm.repository

import android.net.Uri
import com.frgutawan.dawoodfastingalarm.data.alarm.AlarmDataSource
import com.frgutawan.dawoodfastingalarm.data.datastore.AppDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val dataStore: AppDataStore,
    private val alarmDataSource: AlarmDataSource
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

    fun test() = alarmDataSource.test()
}