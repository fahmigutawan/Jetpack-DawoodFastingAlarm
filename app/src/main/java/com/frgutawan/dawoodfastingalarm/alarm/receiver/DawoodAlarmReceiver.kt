package com.frgutawan.dawoodfastingalarm.alarmreceiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.frgutawan.dawoodfastingalarm.R
import com.frgutawan.dawoodfastingalarm.utils.playAlarmSound

class DawoodAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context, p1: Intent) {
        try {
            val manager = p0.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val channelName = "DawoodAlarm"
            val channelId = "DawoodAlarm_ID"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                manager.deleteNotificationChannel("DawoodAlarm_ID")

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

            playAlarmSound(p0)

            manager.notify(1, builder.build())
        }catch (e:Exception){
            /*TODO*/
        }
    }
}