package com.frgutawan.dawoodfastingalarm.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OutlinedFlag
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.frgutawan.dawoodfastingalarm.R
import com.frgutawan.dawoodfastingalarm.ui.theme.White
import com.frgutawan.dawoodfastingalarm.viewmodel.SettingScreenViewModel

@Composable
fun SettingScreen() {
    /**Attrs*/
    val viewModel = hiltViewModel<SettingScreenViewModel>()
    val isLanguageShow = remember { mutableStateOf(false) }

    /**Content*/
    LazyColumn(modifier = Modifier.padding(start = 32.dp, end = 32.dp)) {
        item {
            Spacer(modifier = Modifier.height(32.dp))
        }

        item {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.OutlinedFlag,
                    contentDescription = "flag",
                    tint = White
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(color = Color.White),
                            onClick = { isLanguageShow.value = true }
                        ),
                    text = "Language",
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 12.sp
                )
            }
        }
    }

    if (isLanguageShow.value)
        AlertDialog(onDismissRequest = { isLanguageShow.value = false }, buttons = {})
}