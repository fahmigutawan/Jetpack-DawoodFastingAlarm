package com.frgutawan.dawoodfastingalarm.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frgutawan.dawoodfastingalarm.component.AppBottomBarItem
import com.frgutawan.dawoodfastingalarm.navigation.MainNavigation
import com.frgutawan.dawoodfastingalarm.repository.AppRepository
import com.frgutawan.dawoodfastingalarm.utils.LangMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    /**Global Attrs*/
    var showBottomAppBar = mutableStateOf(false)
    var currentNavigation by mutableStateOf(MainNavigation.SPLASH_SCREEN.name)
    var bottomAppBarPadding by mutableStateOf(0.dp)

    /**Language*/
    var language by mutableStateOf(LangMapper.englishLang())
    fun updateLanguageState() {
        viewModelScope.launch {
            repository.getLanguage().collect{
                when(it){
                    "indonesia" -> language = LangMapper.indonesiaLang()
                    "english" -> language = LangMapper.englishLang()
                }
            }
        }
    }

    /**Bottom App Bar*/
    val bottomAppBarState = mutableStateOf(AppBottomBarItem.DawoodFasting)
}