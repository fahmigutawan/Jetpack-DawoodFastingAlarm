package com.frgutawan.dawoodfastingalarm

import android.media.MediaPlayer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.frgutawan.dawoodfastingalarm.component.AppBottomBar
import com.frgutawan.dawoodfastingalarm.navigation.MainNavigation
import com.frgutawan.dawoodfastingalarm.repository.AppRepository
import com.frgutawan.dawoodfastingalarm.screen.DawoodFastingScreen
import com.frgutawan.dawoodfastingalarm.screen.SettingScreen
import com.frgutawan.dawoodfastingalarm.screen.SplashScreen
import com.frgutawan.dawoodfastingalarm.viewmodel.MainViewModel
import javax.inject.Inject

lateinit var mainViewModel: MainViewModel
lateinit var alarmMediaPlayer: MediaPlayer

@Composable
fun AppContent() {
    /**Attrs*/
    mainViewModel = hiltViewModel()
    alarmMediaPlayer = MediaPlayer()
    val navController = rememberNavController()

    /**Content*/
    Scaffold(
        bottomBar = {
            if (mainViewModel.showBottomAppBar.value)
                AppBottomBar(
                    mainViewModel = mainViewModel,
                    navController = navController
                )
        }
    ) {
        mainViewModel.bottomAppBarPadding = it.calculateBottomPadding()
        AppNavigationHost(modifier = Modifier.padding(it), navController = navController)
    }
}

@Composable
private fun AppNavigationHost(modifier: Modifier = Modifier, navController: NavHostController) {
    /**Attrs*/

    /**Function*/
    when (mainViewModel.currentNavigation) {
        MainNavigation.DAWOOD_FASTING_SCREEN.name -> {
            mainViewModel.showBottomAppBar.value = true
        }
        MainNavigation.NORMAL_ALARM_SCREEN.name -> {
            mainViewModel.showBottomAppBar.value = true
        }
        MainNavigation.SETTINGS_SCREEN.name -> {
            mainViewModel.showBottomAppBar.value = true
        }
        else -> {
            mainViewModel.showBottomAppBar.value = false
        }
    }

    /**Content*/
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainNavigation.SPLASH_SCREEN.name
    ) {
        /**MAIN SCREEN*/
        composable(route = MainNavigation.SPLASH_SCREEN.name) {
            mainViewModel.currentNavigation = MainNavigation.SPLASH_SCREEN.name
            SplashScreen(navController = navController)
        }

        composable(route = MainNavigation.DAWOOD_FASTING_SCREEN.name) {
            mainViewModel.currentNavigation = MainNavigation.DAWOOD_FASTING_SCREEN.name
            DawoodFastingScreen(navController = navController)
        }

        composable(route = MainNavigation.NORMAL_ALARM_SCREEN.name) {
            mainViewModel.currentNavigation = MainNavigation.NORMAL_ALARM_SCREEN.name
        }

        composable(route = MainNavigation.SETTINGS_SCREEN.name) {
            mainViewModel.currentNavigation = MainNavigation.SETTINGS_SCREEN.name
            SettingScreen()
        }

        /**BRANCH SCREEN*/
    }
}