package com.frgutawan.dawoodfastingalarm

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.frgutawan.dawoodfastingalarm.utils.playAlarmSound
import com.frgutawan.dawoodfastingalarm.utils.stopAlarmSound

class TurnOffAlarmActivity:MainActivity() {
    @Composable
    override fun MyContent(){
        /**Attrs*/
        val context = LocalContext.current

        /**Function*/
        playAlarmSound(context)

        /**Content*/
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Button(onClick = { stopAlarmSound() }) {
                Text(text = "Click Me")
            }
        }
    }
}