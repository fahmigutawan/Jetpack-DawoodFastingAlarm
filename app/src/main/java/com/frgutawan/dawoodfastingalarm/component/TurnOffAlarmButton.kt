package com.frgutawan.dawoodfastingalarm.component

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.frgutawan.dawoodfastingalarm.ui.theme.DarkGray
import com.frgutawan.dawoodfastingalarm.ui.theme.Gray
import com.frgutawan.dawoodfastingalarm.ui.theme.White
import kotlin.math.roundToInt

@Composable
fun SwipeableAlarmButton(
    modifier: Modifier = Modifier,
    width: Dp,
    height: Dp,
    leftContent: @Composable (() -> Unit)? = null,
    rightContent: @Composable (() -> Unit)? = null,
    onReachLeft: () -> Unit,
    onReachRight: () -> Unit,
    leftColor: Color = Gray,
    rightColor: Color = Color.Red,
    shadowColor: Color = White,
    thumbColor: Color = DarkGray,
    thumbContent: @Composable (() -> Unit)? = null
) {
    /**Attrs*/
    val layoutCoordinates = remember { mutableStateOf<LayoutCoordinates?>(null) }
    val leftTrackWidth = remember { mutableStateOf((width / 2)) }
    val rightTrackWidth = remember { mutableStateOf((width / 2)) }
    val animLeftTrackWidth = animateDpAsState(targetValue = leftTrackWidth.value)
    val animRightTrackWidth = animateDpAsState(targetValue = rightTrackWidth.value)

    /**Content*/
    Box(modifier = modifier.onGloballyPositioned { layoutCoordinates.value = it }) {
        /*TRACK*/
        Row() {
            Box(
                modifier = Modifier
                    .height(height)
                    .width(animLeftTrackWidth.value)
                    .clip(
                        RoundedCornerShape(
                            topStart = Int.MAX_VALUE.dp,
                            bottomStart = Int.MAX_VALUE.dp
                        )
                    )
                    .background(brush = Brush.horizontalGradient(listOf(leftColor, shadowColor))),
                contentAlignment = Alignment.CenterStart
            ) {
                leftContent?.let {
                    it()
                }
            }
            Box(
                modifier = Modifier
                    .height(height)
                    .width(animRightTrackWidth.value)
                    .clip(
                        RoundedCornerShape(
                            topEnd = Int.MAX_VALUE.dp,
                            bottomEnd = Int.MAX_VALUE.dp
                        )
                    )
                    .background(brush = Brush.horizontalGradient(listOf(shadowColor, rightColor))),
                contentAlignment = Alignment.CenterEnd
            ) {
                rightContent?.let {
                    it()
                }
            }
        }

        /*THUMB*/
        layoutCoordinates.value?.let {
            Thumb(
                layoutCoordinates = it,
                size = height,
                color = thumbColor,
                content = thumbContent,
                leftTrackWidthState = leftTrackWidth,
                rightTrackWidthState = rightTrackWidth,
                onReachLeft = onReachLeft,
                onReachRight = onReachRight
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Thumb(
    layoutCoordinates: LayoutCoordinates,
    size: Dp,
    color: Color,
    content: @Composable() (() -> Unit)? = null,
    onReachLeft: () -> Unit,
    onReachRight: () -> Unit,
    leftTrackWidthState: MutableState<Dp>,
    rightTrackWidthState: MutableState<Dp>
) {
    /**Attrs*/
    val swipeableState = rememberSwipeableState(1)
    val localDensity = LocalDensity.current
    val startTrackCoordinateX = layoutCoordinates.positionInRoot().x
    val endTrackCoordinateX = startTrackCoordinateX + layoutCoordinates.size.width
    val initialThumbCoordinates =
        startTrackCoordinateX + ((endTrackCoordinateX - startTrackCoordinateX) / 2)
    val anchors = mapOf(
        startTrackCoordinateX to 0,
        (initialThumbCoordinates - (layoutCoordinates.size.height / 2)) to 1,
        (endTrackCoordinateX - layoutCoordinates.size.height) to 2
    )
    val swipeValueState = remember { mutableStateOf(1) }

    /**Function*/
//    leftTrackWidthState.value =
//        localDensity.run { (swipeableState.offset.value + (layoutCoordinates.size.height / 2) - startTrackCoordinateX).toDp()}
//
//    rightTrackWidthState.value =
//        localDensity.run { (endTrackCoordinateX - (swipeableState.offset.value + (layoutCoordinates.size.height / 2))).toDp()}
    when (swipeableState.currentValue) {
        0 -> {
            if (swipeValueState.value != 0) {
                onReachLeft()
            }

            swipeValueState.value = 0
        }
        1 -> {
            swipeValueState.value = 1
        }
        2 -> {
            if (swipeValueState.value != 2) {
                onReachRight()
            }

            swipeValueState.value = 2
        }
    }

    /**Content*/
    Box(
        modifier = Modifier
            .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                orientation = Orientation.Horizontal
            )
            .size(size)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        content?.let {
            it()
        }
    }
}