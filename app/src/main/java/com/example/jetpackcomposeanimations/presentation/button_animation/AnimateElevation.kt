package com.example.jetpackcomposeanimations.presentation.button_animation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorGreen

@Preview
@Composable
fun AnimateElevation() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // [START android_compose_animation_cookbook_elevation]
        val mutableInteractionSource = remember {
            MutableInteractionSource()
        }
        val pressed = mutableInteractionSource.collectIsPressedAsState()
        val elevation = animateDpAsState(
            targetValue = if (pressed.value) {
                32.dp
            } else {
                8.dp
            },
            label = "elevation"
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.Center)
                .graphicsLayer {
                    this.shadowElevation = elevation.value.toPx()
                }
                .clickable(interactionSource = mutableInteractionSource, indication = null) {
                }
                .background(colorGreen)
        ) {
        }
        // [END android_compose_animation_cookbook_elevation]
    }
}