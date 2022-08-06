package com.frgutawan.dawoodfastingalarm.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DropdownMenuField(
    modifier: Modifier = Modifier,
    defaultText: String,
    valueState: MutableState<String>,
    isExpanded: MutableState<Boolean>,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(CornerSize(8.dp)))
            .border(
                width = 1.dp,
                color = Color.DarkGray,
                shape = RoundedCornerShape(CornerSize(8.dp))
            )
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            value = valueState.value,
            onValueChange = { valueState.value = it },
            readOnly = true
        ) { innerTextField ->
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //TEXT
                    Box {
                        innerTextField()
                        if (valueState.value.isEmpty()) Text(text = defaultText, fontSize = 12.sp)
                    }

                    //ARROW ICON
                    AnimatedContent(targetState = isExpanded.value) { state ->
                        if (state) {
                            Icon(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(
                                            bounded = true,
                                            color = Color.Black
                                        ),
                                        onClick = { isExpanded.value = !isExpanded.value }
                                    ),
                                imageVector = Icons.Default.ArrowDropUp,
                                contentDescription = "Arrow"
                            )
                        } else {
                            Icon(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(
                                            bounded = true,
                                            color = Color.Black
                                        ),
                                        onClick = { isExpanded.value = !isExpanded.value }
                                    ),
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Arrow"
                            )
                        }
                    }
                }

                DropdownMenu(
                    expanded = isExpanded.value,
                    onDismissRequest = { isExpanded.value = false }
                ) {
                    AnimatedVisibility(visible = isExpanded.value) {
                        Column {
                            content()
                        }
                    }
                }
            }
        }
    }
}