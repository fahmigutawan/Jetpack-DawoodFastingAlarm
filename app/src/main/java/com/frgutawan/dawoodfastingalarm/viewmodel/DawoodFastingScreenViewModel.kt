package com.frgutawan.dawoodfastingalarm.viewmodel

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frgutawan.dawoodfastingalarm.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DawoodFastingScreenViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    val isDawoodFastingAlarmActive = mutableStateOf(false)
    val isDawoodFastingReminderActive = mutableStateOf(false)

    fun getIsDawoodFastingAlarmActive(){
        viewModelScope.launch {
            repository.getIsDawoodFastingAlarmActive().collect{
                isDawoodFastingAlarmActive.value = it
            }
        }
    }
    fun saveIsDawoodFastingAlarmActive(){
        viewModelScope.launch {
            repository.saveIsDawoodFastingAlarmActive(isDawoodFastingAlarmActive.value)
        }
    }

    fun getIsDawoodFastingReminderActive(){
        viewModelScope.launch {
            repository.getIsDawoodFastingReminderActive().collect{
                isDawoodFastingReminderActive.value = it
            }
        }
    }
    fun saveIsDawoodFastingReminderActive(){
        viewModelScope.launch {
            repository.saveIsDawoodFastingReminderActive(isDawoodFastingReminderActive.value)
        }
    }

    fun test() = repository.test()
}