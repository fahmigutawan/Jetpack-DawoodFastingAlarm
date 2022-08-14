package com.frgutawan.dawoodfastingalarm.screen

import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.frgutawan.dawoodfastingalarm.R
import com.frgutawan.dawoodfastingalarm.component.ButtonField
import com.frgutawan.dawoodfastingalarm.component.ToggleSwitchField
import com.frgutawan.dawoodfastingalarm.mainViewModel
import com.frgutawan.dawoodfastingalarm.ui.theme.*
import com.frgutawan.dawoodfastingalarm.utils.DrawOverOtherApp
import com.frgutawan.dawoodfastingalarm.viewmodel.DawoodFastingScreenViewModel
import java.util.*

@Composable
fun DawoodFastingScreen(navController: NavController) {
    /**Attrs*/
    val viewModel = hiltViewModel<DawoodFastingScreenViewModel>()

    /**Content*/
    LazyColumn {
        item { TopBackground() }
        item { BelowContent(navController, viewModel) }
    }

    //Dialog
    if (viewModel.showAboutStartNextAlarm.value) AboutStartFromNextAlarm(viewModel = viewModel)
    if (viewModel.showCheckPermissionDialog) CheckForPermission(viewModel = viewModel)
    if (viewModel.showXiaomiDeviceAlert) AlertForXiaomiDevices(viewModel = viewModel)
}

@Composable
private fun TopBackground() {
    /**Attrs*/
    val height = LocalConfiguration.current.screenHeightDp / 3.5

    /**Content*/
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val scrWidth = LocalConfiguration.current.screenWidthDp

            Column(
                modifier = Modifier
                    .width((scrWidth * 2 / 3).dp)
                    .fillMaxHeight()
                    .padding(top = 0.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = mainViewModel.language.dawoodFastingScreen.hadith,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    color = White,
                    fontSize = 10.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = mainViewModel.language.dawoodFastingScreen.hadithNarator,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    color = LightGreen,
                    fontSize = 12.sp
                )
            }
            AsyncImage(
                modifier = Modifier.padding(top = 32.dp),
                model = R.drawable.ic_colored_mosque,
                contentDescription = "Mosque"
            )
        }
    }
}

@Composable
private fun BelowContent(navController: NavController, viewModel: DawoodFastingScreenViewModel) {
    /**Attr*/
    val scrHeight =
        LocalConfiguration.current.screenHeightDp - mainViewModel.bottomAppBarPadding.value
    val topBgHeight = LocalConfiguration.current.screenHeightDp / 3.5
    val context = LocalContext.current

    /**Function*/
    viewModel.getIsDawoodFastingAlarmActive()
    viewModel.getIsDawoodFastingReminderActive()
    viewModel.getDawoodFastingAlarmClock()
    viewModel.getDawoodFastingReminderClock()

    /**Content*/
    Box(
        modifier = Modifier
            .heightIn(min = (scrHeight - topBgHeight).dp)
            .fillMaxWidth()
            .background(DarkGray)
    ) {
        Column(modifier = Modifier.fillMaxHeight()) {
            /**[DawoodFastingAlarm]*/
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                ToggleSwitchField(
                    modifier = Modifier.fillMaxWidth(),
                    arrangement = Arrangement.SpaceBetween,
                    leadingContent = {
                        Text(
                            text = mainViewModel.language.dawoodFastingScreen.activateDawoodAlarm,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                            fontSize = 14.sp,
                            color = LightGreen
                        )
                    },
                    onCheckedChange = {
                        viewModel.saveIsDawoodFastingAlarmActive()
                        when (it) {
                            true -> {
                                viewModel.setDawoodFastingAlarm(
                                    Integer.parseInt(viewModel.dawoodFastingAlarmClockHour.value),
                                    Integer.parseInt(viewModel.dawoodFastingAlarmClockMinute.value),
                                    context
                                )
                            }
                            else -> {
                                viewModel.cancelDawoodFastingAlarm(context)
                            }
                        }
                    },
                    checkedState = viewModel.isDawoodFastingAlarmActive
                )

                // Check if DrawOverOtherApp has granted
                AnimatedVisibility(visible = viewModel.isDawoodFastingAlarmActive.value) {
                    if (viewModel.isDawoodFastingAlarmActive.value) {
                        DrawOverOtherApp(
                            onGranted = {
                                DawoodAlarmContent(
                                    navController = navController,
                                    viewModel = viewModel
                                )
                            },
                            onDenied = { viewModel.showCheckPermissionDialog = true },
                            context = context
                        )
                    }
                }
            }

            /**[DawoodFastingReminder]*/
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                ToggleSwitchField(
                    modifier = Modifier.fillMaxWidth(),
                    arrangement = Arrangement.SpaceBetween,
                    leadingContent = {
                        Text(
                            text = mainViewModel.language.dawoodFastingScreen.reminderDayBefore,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                            fontSize = 14.sp,
                            color = LightGreen
                        )
                    },
                    onCheckedChange = {
                        viewModel.saveIsDawoodFastingReminderActive()
                        when (it) {
                            true -> {
                                viewModel.setDawoodFastingReminder(
                                    Integer.parseInt(viewModel.dawoodFastingReminderHour.value),
                                    Integer.parseInt(viewModel.dawoodFastingReminderMinute.value),
                                    context
                                )
                            }
                            else -> {
                                viewModel.cancelDawoodFastingReminder(context)
                            }
                        }
                    },
                    checkedState = viewModel.isDawoodFastingReminderActive
                )

                AnimatedVisibility(visible = viewModel.isDawoodFastingReminderActive.value) {
                    if (viewModel.isDawoodFastingReminderActive.value) {
                        DrawOverOtherApp(
                            onGranted = {
                                DawoodReminderContent(
                                    navController = navController,
                                    viewModel = viewModel
                                )
                            },
                            onDenied = { viewModel.showCheckPermissionDialog = true },
                            context = context
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DawoodAlarmContent(
    navController: NavController,
    viewModel: DawoodFastingScreenViewModel
) {
    Column {
        Divider(modifier = Modifier.fillMaxWidth(), color = Gray, thickness = 2.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val context = LocalContext.current
            val timePicker = TimePickerDialog(
                context,
                { _, hour: Int, minute: Int ->
                    if (hour < 10) viewModel.dawoodFastingAlarmClockHour.value = "0$hour"
                    else viewModel.dawoodFastingAlarmClockHour.value = "$hour"

                    if (minute < 10) viewModel.dawoodFastingAlarmClockMinute.value = "0$minute"
                    else viewModel.dawoodFastingAlarmClockMinute.value = "$minute"

                    viewModel.cancelDawoodFastingAlarm(context)
                    viewModel.setDawoodFastingAlarm(hour, minute, context)
                    viewModel.saveDawoodFastingAlarmClock()
                },
                Integer.parseInt(viewModel.dawoodFastingAlarmClockHour.value),
                Integer.parseInt(viewModel.dawoodFastingAlarmClockMinute.value),
                true
            )
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = White),
                    onClick = { timePicker.show() }),
                text = "${viewModel.dawoodFastingAlarmClockHour.value}:${viewModel.dawoodFastingAlarmClockMinute.value}",
                color = White,
                fontSize = 48.sp,
                fontFamily = FontFamily(Font(R.font.poppins_semibold))
            )

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Setting",
                    tint = LightGreen
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            ButtonField(onClick = {}) {
                Text(
                    text = mainViewModel.language.dawoodFastingScreen.startFromNextAlarm,
                    color = Black,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            ButtonField(
                onClick = { viewModel.showAboutStartNextAlarm.value = true },
                shape = CircleShape
            ) {
                Text(
                    text = "?",
                    color = Black,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            }
        }
    }
}

@Composable
private fun DawoodReminderContent(
    navController: NavController,
    viewModel: DawoodFastingScreenViewModel
) {
    Column {
        Divider(modifier = Modifier.fillMaxWidth(), color = Gray, thickness = 2.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val context = LocalContext.current
            val timePicker = TimePickerDialog(
                context,
                { _, hour: Int, minute: Int ->
                    if (hour < 10) viewModel.dawoodFastingReminderHour.value = "0$hour"
                    else viewModel.dawoodFastingReminderHour.value = "$hour"

                    if (minute < 10) viewModel.dawoodFastingReminderMinute.value = "0$minute"
                    else viewModel.dawoodFastingReminderMinute.value = "$minute"

                    viewModel.cancelDawoodFastingReminder(context)
                    viewModel.setDawoodFastingReminder(hour, minute, context)
                    viewModel.saveDawoodFastingReminderClock()
                },
                Integer.parseInt(viewModel.dawoodFastingReminderHour.value),
                Integer.parseInt(viewModel.dawoodFastingReminderMinute.value),
                true
            )

            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = White),
                    onClick = { timePicker.show() }),
                text = "${viewModel.dawoodFastingReminderHour.value}:${viewModel.dawoodFastingReminderMinute.value}",
                color = White,
                fontSize = 48.sp,
                fontFamily = FontFamily(Font(R.font.poppins_semibold))
            )

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Setting",
                    tint = LightGreen
                )
            }
        }
    }
}

@Composable
private fun AboutStartFromNextAlarm(viewModel: DawoodFastingScreenViewModel) {
    AlertDialog(
        onDismissRequest = { viewModel.showAboutStartNextAlarm.value = false },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = mainViewModel.language.dawoodFastingScreen.aboutStartFromNextAlarm,
                    color = Black,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
                Spacer(modifier = Modifier.height(8.dp))
                ButtonField(
                    onClick = { viewModel.showAboutStartNextAlarm.value = false },
                    color = DarkGray
                ) {
                    Text(
                        text = mainViewModel.language.dawoodFastingScreen.aboutStartFromNextAlarmExit,
                        color = White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                }
            }
        },
        buttons = {},
        backgroundColor = LightGray,
        shape = RoundedCornerShape(CornerSize(4.dp))
    )
}

@Composable
private fun CheckForPermission(viewModel: DawoodFastingScreenViewModel) {
    /**Attrs*/
    val context = LocalContext.current

    /**Function*/
    viewModel.isDawoodFastingAlarmActive.value = false
    viewModel.saveIsDawoodFastingAlarmActive()

    /**Content*/
    AlertDialog(
        onDismissRequest = { viewModel.showCheckPermissionDialog = false },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "CHECK YOUR PERMISSION",
                    color = Black,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
                Spacer(modifier = Modifier.height(8.dp))

            }
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp), horizontalArrangement = Arrangement.Center
            ) {
                ButtonField(
                    onClick = {
                        val intent = Intent(
                            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:" + context.getPackageName())
                        )

                        context.startActivity(intent)
                        viewModel.showCheckPermissionDialog = false
                    },
                    color = DarkGray
                ) {
                    Text(
                        text = "Get Permission",
                        color = White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    )
                }
            }
        },
        backgroundColor = LightGray,
        shape = RoundedCornerShape(CornerSize(4.dp))
    )
}

@Composable
private fun AlertForXiaomiDevices(viewModel: DawoodFastingScreenViewModel) {
    /**Attrs*/
    val context = LocalContext.current
    val batteryOptimizatonIntent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
    val otherSettingIntent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.parse("package:" + context.getPackageName())
    )

    /**Content*/
    AlertDialog(
        backgroundColor = LightGray,
        shape = RoundedCornerShape(CornerSize(4.dp)),
        onDismissRequest = { viewModel.showXiaomiDeviceAlert = false },
        text = {
            Column(verticalArrangement = Arrangement.Top) {
                //Battery Optimization
                Row(verticalAlignment = Alignment.Top) {
                    Icon(
                        imageVector = Icons.Default.DoubleArrow,
                        contentDescription = "Arrow",
                        tint = Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Change you Battery Optimization preferences...",
                            color = Black,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium))
                        )
                        Text(
                            text = "Make sure that your \"Battery Optimization\" has turned off for this application",
                            color = Black,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular))
                        )
                    }
                }

                //Spacer
                Spacer(modifier = Modifier.height(16.dp))

                //Other Setting
                Row(verticalAlignment = Alignment.Top) {
                    Icon(
                        imageVector = Icons.Default.DoubleArrow,
                        contentDescription = "Arrow",
                        tint = Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "If you are Xiaomi user...",
                            color = Black,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium))
                        )

                        Text(
                            text = "Make sure you have checked \"Show on Lockscreen\" on your settings",
                            color = Black,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular))
                        )

                        Text(
                            text = "You can change it manually by",
                            color = Black,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular))
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Column() {
                            Text(
                                text = ">> App Configuration",
                                color = Black,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold))
                            )

                            Text(
                                text = ">> Other Permissions",
                                color = Black,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold))
                            )

                            Text(
                                text = ">> Show on Lockscreen",
                                color = Black,
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_semibold))
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Or click button below",
                            color = Black,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular))
                        )
                    }
                }
            }
        },
        buttons = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                /*Battery Optimization*/
                ButtonField(onClick = { context.startActivity(batteryOptimizatonIntent) }) {
                    Text(
                        text = "Go to Battery Optimization Setting",
                        color = Black,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                /*Other permission*/
                ButtonField(onClick = { context.startActivity(otherSettingIntent) }) {
                    Text(
                        text = "Go to Detail App Setting",
                        color = Black,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                /*ill do it later*/
                ButtonField(
                    onClick = { viewModel.showXiaomiDeviceAlert = false },
                    color = BlueButton
                ) {
                    Text(
                        text = "Okay",
                        color = Black,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        })
}