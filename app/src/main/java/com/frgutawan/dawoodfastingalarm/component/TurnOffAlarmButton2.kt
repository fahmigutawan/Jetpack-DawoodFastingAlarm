//package com.frgutawan.dawoodfastingalarm.component
//
//import android.annotation.SuppressLint
//import android.util.Log
//import androidx.compose.foundation.background
//import androidx.compose.foundation.gestures.Orientation
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.layout.LayoutCoordinates
//import androidx.compose.ui.layout.onGloballyPositioned
//import androidx.compose.ui.layout.positionInRoot
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.IntOffset
//import androidx.compose.ui.unit.dp
//import com.frgutawan.dawoodfastingalarm.ui.theme.Black
//import com.frgutawan.dawoodfastingalarm.ui.theme.Gray
//import kotlin.math.roundToInt
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun SwipeableAlarmButton(
//    modifier: Modifier = Modifier
//        .height(64.dp)
//        .width(256.dp),
//    trackColor: Color = Gray,
//    shape: Shape = RoundedCornerShape(Int.MAX_VALUE.dp),
//    thumbMiddleContent: @Composable (() -> Unit)? = null,
//    thumbColor: Color = Black,
//    thumbPadding: Dp = 2.dp
//) {
//    SwipableButtonTrack(
//        modifier = modifier,
//        trackColor = trackColor,
//        shape = shape
//    ) {
//        SwipableButtonThumb(
//            thumbMiddleContent = thumbMiddleContent,
//            padding = thumbPadding,
//            layoutCoordinates = it,
//            color = thumbColor,
//            shape = shape
//        )
//    }
//}
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//private fun SwipableButtonTrack(
//    modifier: Modifier,
//    trackColor: Color,
//    shape: Shape,
//    thumb: @Composable (LayoutCoordinates) -> Unit
//) {
//    /**Attrs*/
//    val layoutCoordinates = remember { mutableStateOf<LayoutCoordinates?>(null) }
//
//    /**Content*/
//    Box(
//        modifier = modifier
//            .onGloballyPositioned { layoutCoordinates.value = it }
//            .clip(shape)
//            .background(trackColor)
//    ) {
//        layoutCoordinates.value?.let {
//            thumb(it)
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//private fun SwipableButtonThumb(
//    thumbMiddleContent: @Composable (() -> Unit)? = null,
//    padding: Dp,
//    color: Color,
//    shape: Shape,
//    layoutCoordinates: LayoutCoordinates
//) {
//    /**Attrs*/
//    val swipeableState = rememberSwipeableState(1)
//    val localDensity = LocalDensity.current
//    val startTrackCoordinateX = layoutCoordinates.positionInRoot().x
//    val endTrackCoordinateX = startTrackCoordinateX + layoutCoordinates.size.width
//    val initialThumbCoordinates =
//        startTrackCoordinateX + ((endTrackCoordinateX - startTrackCoordinateX) / 2)
//    val anchors = mapOf(
//        startTrackCoordinateX to 0,
//        (initialThumbCoordinates - (layoutCoordinates.size.height/2) - localDensity.run { padding.toPx() }) to 1,
//        (endTrackCoordinateX - layoutCoordinates.size.height - (localDensity.run { padding.toPx() } * 2)) to 2
//    )
//
//    /**Content*/
//    Box(
//        modifier = Modifier
//            .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
//            .swipeable(
//                state = swipeableState,
//                anchors = anchors,
//                orientation = Orientation.Horizontal
//            )
//            .padding(padding)
//            .clip(shape)
//            .background(color)
//            .size(localDensity.run { layoutCoordinates.size.height.toDp() }),
//        contentAlignment = Alignment.Center
//    ) {
//        thumbMiddleContent?.let {
//            it()
//        }
//    }
//}