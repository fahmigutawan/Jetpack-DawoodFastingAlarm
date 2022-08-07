package com.frgutawan.dawoodfastingalarm.alarmreceiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.frgutawan.dawoodfastingalarm.R

class DawoodReminderReceiver:BroadcastReceiver() {
    override fun onReceive(p0: Context, p1: Intent) {
        try {
            val manager = p0.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val channelName = "DawoodReminder"
            val channelId = "DawoodReminder_ID"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                manager.deleteNotificationChannel("DawoodReminder_ID")

                val channel =
                    NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)

                manager.createNotificationChannel(channel)
            }

            val builder =
                NotificationCompat
                    .Builder(p0, channelId)
                    .setSmallIcon(R.drawable.ic_botmenu_mosque)
                    .setContentTitle(p1.getStringExtra("content_title") ?: "No Title")
                    .setContentText(p1.getStringExtra("content_text") ?: "No Text")

            manager.notify(1, builder.build())
        }catch (e:Exception){
            /*TODO*/
        }
    }
}