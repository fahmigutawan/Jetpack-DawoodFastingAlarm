package com.frgutawan.dawoodfastingalarm.utils

import android.content.Context
import android.provider.Settings
import androidx.compose.runtime.Composable

@Composable
fun DrawOverOtherApp(onGranted: @Composable () -> Unit, onDenied: @Composable () -> Unit, context: Context){
    when(Settings.canDrawOverlays(context)){
        true -> onGranted()
        false -> onDenied()
    }
}