package com.example.jetpackcomposeanimations.presentation.shape_animation.size_padding_animation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AnimatePadding() {
    Box {
        // [START android_compose_animation_padding]
        var toggled by remember {
            mutableStateOf(false)
        }
        val animatedPadding by animateDpAsState(
            if (toggled) {
                0.dp
            } else {
                20.dp
            },
            label = "padding"
        )
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxSize()
                .padding(animatedPadding)
                .background(Color(0xff53D9A1))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    toggled = !toggled
                }
        )
        // [END android_compose_animation_padding]
    }
}