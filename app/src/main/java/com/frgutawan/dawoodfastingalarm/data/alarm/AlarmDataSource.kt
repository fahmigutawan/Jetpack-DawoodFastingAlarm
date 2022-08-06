package com.frgutawan.dawoodfastingalarm.data.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.media.MediaPlayer
import com.frgutawan.dawoodfastingalarm.alarmreceiver.DawoodAlarmReceiver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmDataSource @Inject constructor(
    private val context: Context,
    private val calendar: Calendar,
    private val mediaPlayer: MediaPlayer
) {
    val dawoodAlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun test() {
        val timeSec = System.currentTimeMillis() + 3000
        val intent = Intent(context, DawoodAlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        dawoodAlarmManager.set(AlarmManager.RTC_WAKEUP, timeSec, pendingIntent)
    }
}