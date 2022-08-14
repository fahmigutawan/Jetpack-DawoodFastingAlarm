package com.frgutawan.dawoodfastingalarm.alarm.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import com.frgutawan.dawoodfastingalarm.alarm.services.DawoodAlarmService

class DawoodAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context, p1: Intent) {
        try {
            val serviceIntent = Intent(p0, DawoodAlarmService::class.java)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                p0.startForegroundService(serviceIntent)
            } else {
                p0.startService(serviceIntent)
            }
        } catch (e: Exception) {
            /*TODO*/
        }
    }
}