package com.frgutawan.dawoodfastingalarm

import android.app.Application
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.frgutawan.dawoodfastingalarm.ui.theme.DawoodFastingAlarmTheme
import com.frgutawan.dawoodfastingalarm.ui.theme.VeryDarkGray
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Named

lateinit var alarmMediaPlayer: MediaPlayer

@HiltAndroidApp
class MyApp : Application()

@AndroidEntryPoint
open class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**Attrs*/
        alarmMediaPlayer = MediaPlayer()


        /**Content*/
        setContent {
            DawoodFastingAlarmTheme {
                MyContent()
            }
        }
    }

    @Composable
    open fun MyContent() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = VeryDarkGray),
        ) {
            AppContent()
        }
    }
}