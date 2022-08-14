package com.frgutawan.dawoodfastingalarm.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.frgutawan.dawoodfastingalarm.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TurnOffAlarmViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

}