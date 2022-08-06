package com.frgutawan.dawoodfastingalarm.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.frgutawan.dawoodfastingalarm.R
import com.frgutawan.dawoodfastingalarm.component.ToggleSwitchField
import com.frgutawan.dawoodfastingalarm.mainViewModel
import com.frgutawan.dawoodfastingalarm.ui.theme.DarkGray
import com.frgutawan.dawoodfastingalarm.ui.theme.LightGreen
import com.frgutawan.dawoodfastingalarm.ui.theme.White
import com.frgutawan.dawoodfastingalarm.viewmodel.DawoodFastingScreenViewModel

@Composable
fun DawoodFastingScreen(navController: NavController) {
    /**Attrs*/
    val viewModel = hiltViewModel<DawoodFastingScreenViewModel>()

    /**Content*/
    LazyColumn {
        item {
            TopBackground()
        }

        item {
            BelowContent(viewModel)
        }
    }
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
private fun BelowContent(viewModel: DawoodFastingScreenViewModel) {
    /**Attr*/
    val scrHeight =
        LocalConfiguration.current.screenHeightDp - mainViewModel.bottomAppBarPadding.value
    val topBgHeight = LocalConfiguration.current.screenHeightDp / 3.5

    /**Function*/
    viewModel.getIsDawoodFastingAlarmActive()
    viewModel.getIsDawoodFastingReminderActive()

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
                    onCheckedChange = { viewModel.saveIsDawoodFastingAlarmActive() },
                    checkedState = viewModel.isDawoodFastingAlarmActive
                )
                Button(onClick = { viewModel.test() }) {
                    Text(text = "Click Here")
                }
            }

            /**[DawoodFastingReminder]*/
        }
    }
}