package com.frgutawan.dawoodfastingalarm.viewmodel

import androidx.lifecycle.ViewModel
import com.frgutawan.dawoodfastingalarm.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingScreenViewModel @Inject constructor(repository: AppRepository) : ViewModel() {
}