package com.frgutawan.dawoodfastingalarm

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.renderscript.Allocation
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.view.WindowManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlarmOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.frgutawan.dawoodfastingalarm.alarm.services.DawoodAlarmService
import com.frgutawan.dawoodfastingalarm.component.SwipeableAlarmButton
import com.frgutawan.dawoodfastingalarm.ui.theme.*
import com.frgutawan.dawoodfastingalarm.utils.playAlarmSound

class TurnOffAlarmActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        } else {
            window.addFlags(
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
            )
        }
        super.onCreate(savedInstanceState)
    }

    @Composable
    override fun MyContent() {
        /**Attrs*/
        val context = LocalContext.current

        /**Function*/
        if (!alarmMediaPlayer.isPlaying) playAlarmSound(context)

        /**Content*/
        Surface(color = DarkGray) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                /**[Background]*/
//                BackgroundContent()

                /**[Content]*/
                ForegroundContent()
            }
        }
    }

    fun stopAlarm(context: Context) {
        val stopIntent = Intent(context, DawoodAlarmService::class.java)

        context.stopService(stopIntent)
        System.exit(0)
    }

    fun snoozeAlarm(context: Context) {

    }

    @Composable
    fun ForegroundContent() {
        /**Attrs*/
        val context = LocalContext.current
        val height = 128.dp
        val width = LocalConfiguration.current.screenWidthDp.dp

        /**Content*/
        SwipeableAlarmButton(
            onReachLeft = { },
            onReachRight = { stopAlarm(context) },
            width = width,
            height = height,
            leftContent = {
                Text(
                    modifier = Modifier.padding(32.dp),
                    text = "Snooze",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            },
            rightContent = {
                Text(
                    modifier = Modifier.padding(32.dp),
                    text = "Stop",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            },
            thumbContent = {
                Icon(imageVector = Icons.Default.AlarmOff, contentDescription = "Off", tint = White)
            }
        )
    }

    @Composable
    fun BackgroundContent() {
        /**Attrs*/
        val context = LocalContext.current
        val resources = LocalContext.current.resources
        val wallpaperManager = WallpaperManager.getInstance(context)

        /**Content*/
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            val bitmap =
                BitmapFactory.decodeResource(resources, R.drawable.img_background_turnoffactivity)
            val rs = RenderScript.create(LocalContext.current)
            val bitmapAlloc = Allocation.createFromBitmap(rs, bitmap)
            ScriptIntrinsicBlur.create(rs, bitmapAlloc.element).apply {
                setRadius(0.5f)
                setInput(bitmapAlloc)
                forEach(bitmapAlloc)
            }
            bitmapAlloc.copyTo(bitmap)
            rs.destroy()

            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "ASDASD")

//            AsyncImage(
//                modifier = Modifier.fillMaxSize(),
//                model = bitmap,
//                contentScale = ContentScale.Crop,
//                contentDescription = "Image"
//            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(30.dp, BlurredEdgeTreatment.Unbounded),
                model = R.drawable.img_background_turnoffactivity,
                contentScale = ContentScale.Crop,
                contentDescription = "Image"
            )
        }
    }
}