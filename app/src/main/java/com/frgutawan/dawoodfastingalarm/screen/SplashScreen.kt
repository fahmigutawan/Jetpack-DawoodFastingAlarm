package com.frgutawan.dawoodfastingalarm.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.frgutawan.dawoodfastingalarm.R
import com.frgutawan.dawoodfastingalarm.navigation.MainNavigation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavHostController) {
    /**Attrs*/
    val logoSize = LocalConfiguration.current.screenHeightDp / 5
    val scope = rememberCoroutineScope()

    /**Content*/
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AsyncImage(
            modifier = Modifier.size(logoSize.dp),
            model = R.drawable.ic_logo,
            contentDescription = "Logo"
        )
    }

    /**Function*/
    scope.launch {
        delay(2000)
        navController.navigate(route = MainNavigation.DAWOOD_FASTING_SCREEN.name)
    }
}