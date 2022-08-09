package com.frgutawan.dawoodfastingalarm.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frgutawan.dawoodfastingalarm.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DawoodFastingScreenViewModel @Inject constructor(private val repository: AppRepository) :
    ViewModel() {
    val isDawoodFastingAlarmActive = mutableStateOf(false)
    val isDawoodFastingReminderActive = mutableStateOf(false)
    val dawoodFastingAlarmClockHour = mutableStateOf("00")
    val dawoodFastingAlarmClockMinute = mutableStateOf("00")
    var showAboutStartNextAlarm = mutableStateOf(false)
    var showCheckPermissionDialog by mutableStateOf(false)

    /**[GetterSetter]*/
    fun getIsDawoodFastingAlarmActive() {
        viewModelScope.launch {
            repository.getIsDawoodFastingAlarmActive().collect {
                isDawoodFastingAlarmActive.value = it
            }
        }
    }

    fun saveIsDawoodFastingAlarmActive() {
        viewModelScope.launch {
            repository.saveIsDawoodFastingAlarmActive(isDawoodFastingAlarmActive.value)
        }
    }

    fun getIsDawoodFastingReminderActive() {
        viewModelScope.launch {
            repository.getIsDawoodFastingReminderActive().collect {
                isDawoodFastingReminderActive.value = it
            }
        }
    }

    fun saveIsDawoodFastingReminderActive() {
        viewModelScope.launch {
            repository.saveIsDawoodFastingReminderActive(isDawoodFastingReminderActive.value)
        }
    }

    fun getDawoodFastingAlarmClock() {
        viewModelScope.launch {
            repository.getDawoodFastingAlarmClock().collect {
                dawoodFastingAlarmClockHour.value = it.hour
                dawoodFastingAlarmClockMinute.value = it.minute
            }
        }
    }

    fun saveDawoodFastingAlarmClock() {
        viewModelScope.launch {
            repository.saveDawoodFastingAlarmClock(
                dawoodFastingAlarmClockHour.value,
                dawoodFastingAlarmClockMinute.value
            )
        }
    }
    /**[END]*/

    /***/
    fun setDawoodFastingAlarm(hour: Int, minute: Int, context: Context) =
        repository.setDawoodFastingAlarm(hour, minute, context)

    fun cancelDawoodFastingAlarm(context: Context) = repository.cancelDawoodFastingAlarm(context)
}