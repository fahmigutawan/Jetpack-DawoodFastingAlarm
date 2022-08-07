package com.frgutawan.dawoodfastingalarm.utils

import android.content.Context
import android.media.AudioManager
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import com.frgutawan.dawoodfastingalarm.alarmMediaPlayer
import java.util.logging.Handler

fun playAlarmSound(context: Context, uri: Uri? = null) {
    try {
        alarmMediaPlayer.setDataSource(context, uri!!)
    } catch (e: Exception) {
        val defaultUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        alarmMediaPlayer.setDataSource(context, defaultUri)
    }
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
        alarmMediaPlayer.apply {
            setAudioStreamType(AudioManager.STREAM_ALARM)
            prepare()
            isLooping = true
            start()
        }

        android.os.Handler().postDelayed({ alarmMediaPlayer.stop() }, 5000)
    }
}

fun stopAlarmSound() = alarmMediaPlayer.stop()
