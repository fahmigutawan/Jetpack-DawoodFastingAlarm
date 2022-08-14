package com.frgutawan.dawoodfastingalarm.alarm.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.frgutawan.dawoodfastingalarm.R
import com.frgutawan.dawoodfastingalarm.TurnOffAlarmActivity
import com.frgutawan.dawoodfastingalarm.utils.playAlarmSound

class DawoodAlarmService : Service() {
    override fun onCreate() {
        super.onCreate()

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "DawoodFasting_ID"

        var builder = NotificationCompat
            .Builder(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificatioChannel = NotificationChannel(
                channelId,
                "Dawood Fasting Alarm",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificatioChannel)

            builder = NotificationCompat
                .Builder(this, channelId)
        }
        val intentActivity = Intent(this, TurnOffAlarmActivity::class.java)
        val pendingIntentActivity = PendingIntent.getActivity(this, 0, intentActivity, 0)
        builder.apply {
            setContentTitle("Title")
            setContentText("Text")
            setSmallIcon(R.drawable.ic_botmenu_mosque)
            setPriority(NotificationCompat.PRIORITY_HIGH)
            setFullScreenIntent(pendingIntentActivity, true)
            setCategory(NotificationCompat.CATEGORY_ALARM)
        }

        notificationManager.notify(100, builder.build())
        startForeground(100, builder.build())

        intentActivity.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intentActivity)
    }

    override fun onBind(p0: Intent): IBinder? {
        return null
    }
}