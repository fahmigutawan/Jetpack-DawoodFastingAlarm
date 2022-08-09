package com.frgutawan.dawoodfastingalarm.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.frgutawan.dawoodfastingalarm.ui.theme.Black
import com.frgutawan.dawoodfastingalarm.ui.theme.LightGray
import com.frgutawan.dawoodfastingalarm.ui.theme.LightGreen

@Composable
fun ButtonField(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier.padding(
        start = 16.dp,
        end = 16.dp,
        top = 8.dp,
        bottom = 8.dp
    ),
    contentAlignment: Alignment = Alignment.Center,
    color: Color = LightGreen,
    clickRipple: Color = Black,
    shape: Shape = RoundedCornerShape(CornerSize(4.dp)),
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(color, shape)
            .clip(shape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = clickRipple),
                onClick = onClick
            ),
        contentAlignment = contentAlignment
    ) {
        Box(modifier = contentModifier, contentAlignment = Alignment.Center){
            content()
        }
    }
}