package com.frgutawan.dawoodfastingalarm.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.frgutawan.dawoodfastingalarm.ui.theme.*

@Composable
fun ToggleSwitchField(
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Horizontal = Arrangement.Start,
    leadingContent: @Composable () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    checkedState: MutableState<Boolean>
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = arrangement
    ) {
        leadingContent()
        Switch(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
                onCheckedChange(it)
            },
            colors = SwitchDefaults.colors(
                checkedTrackColor = LightGreen,
                checkedThumbColor = LightGray,
                uncheckedTrackColor = Gray,
                uncheckedThumbColor = LightGray
            )
        )
    }
}