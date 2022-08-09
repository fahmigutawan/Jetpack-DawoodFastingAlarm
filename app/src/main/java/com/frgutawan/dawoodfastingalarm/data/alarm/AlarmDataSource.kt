package com.frgutawan.dawoodfastingalarm.data.alarm

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.frgutawan.dawoodfastingalarm.alarm.receiver.DawoodAlarmReceiver
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmDataSource @Inject constructor() {
    /**Dawood Alarm*/
    fun setDawoodAlarm(hour: Int, minute: Int, context: Context) {
        val dawoodAlarmIntent = Intent(context, DawoodAlarmReceiver::class.java)
        val dawoodAlarmPendingIntent = PendingIntent.getBroadcast(context, 0, dawoodAlarmIntent, 0)

        val cal = Calendar.getInstance()
        val timeNow = Calendar.getInstance()

        cal.set(Calendar.HOUR_OF_DAY, hour)
        cal.set(Calendar.MINUTE, minute)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)

        if (timeNow > cal) cal.set(Calendar.DAY_OF_MONTH, (cal.get(Calendar.DAY_OF_MONTH) + 1))

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.setAlarmClock(AlarmManager.AlarmClockInfo(cal.timeInMillis, dawoodAlarmPendingIntent), dawoodAlarmPendingIntent)
//        alarmManager.setInexactRepeating(
//            AlarmManager.RTC_WAKEUP,
//            cal.timeInMillis,
//            2 * 24 * 3600 * 1000,
//            dawoodAlarmPendingIntent
//        )
    }
    fun cancelDawoodAlarm(context: Context) {
        val dawoodAlarmIntent = Intent(context, DawoodAlarmReceiver::class.java)
        val dawoodAlarmPendingIntent = PendingIntent.getBroadcast(context, 0, dawoodAlarmIntent, 0)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(dawoodAlarmPendingIntent)
    }
}