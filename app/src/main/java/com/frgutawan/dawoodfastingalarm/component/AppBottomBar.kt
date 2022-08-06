package com.frgutawan.dawoodfastingalarm.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.frgutawan.dawoodfastingalarm.R
import com.frgutawan.dawoodfastingalarm.navigation.MainNavigation
import com.frgutawan.dawoodfastingalarm.ui.theme.Gray
import com.frgutawan.dawoodfastingalarm.ui.theme.LightGreen
import com.frgutawan.dawoodfastingalarm.ui.theme.VeryDarkGray
import com.frgutawan.dawoodfastingalarm.viewmodel.MainViewModel

@Composable
fun AppBottomBar(mainViewModel: MainViewModel, navController: NavController) {
    BottomAppBar(elevation = 4.dp, backgroundColor = VeryDarkGray) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            AppBottomBarItem.values().forEach { item ->
                IconButton(
                    onClick = {
                        mainViewModel.bottomAppBarState.value = item
                        navController.navigate(route = item.navRoute)
                    }
                ) {
                    Icon(
                        painter = rememberAsyncImagePainter(model = item.iconId),
                        contentDescription = item.name,
                        tint = when (mainViewModel.bottomAppBarState.value) {
                            item -> LightGreen
                            else -> Gray
                        }
                    )
                }
            }
        }
    }
}

enum class AppBottomBarItem(val iconId: Int, val navRoute: String) {
    DawoodFasting(
        R.drawable.ic_botmenu_mosque,
        MainNavigation.DAWOOD_FASTING_SCREEN.name
    ),
    NormalAlarm(
        R.drawable.ic_botmenu_clock,
        MainNavigation.NORMAL_ALARM_SCREEN.name
    ),
    Setting(
        R.drawable.ic_botmenu_setting,
        MainNavigation.SETTINGS_SCREEN.name
    )
}