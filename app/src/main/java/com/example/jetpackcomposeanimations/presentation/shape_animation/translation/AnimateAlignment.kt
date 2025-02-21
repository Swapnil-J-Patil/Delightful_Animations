package com.example.jetpackcomposeanimations.presentation.shape_animation.translation

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun AnimateAlignment() {
    // [START android_compose_animate_item_placement]
    var toggled by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .clickable(indication = null, interactionSource = interactionSource) {
                toggled = !toggled
            },
        horizontalAlignment = if (toggled) Alignment.End else Alignment.Start, // Change alignment on toggle
        verticalArrangement = Arrangement.SpaceBetween // Spacing between items
    ) {
        // Animating offset or alignment
        val offsetX by animateDpAsState(
            targetValue = if (toggled) 150.dp else 0.dp,
            animationSpec = tween(durationMillis = 2000, easing = LinearOutSlowInEasing)
        )

        // Boxes with animated offset
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Blue)
                .offset(x = offsetX)
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
                .offset(x = offsetX)
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Blue)
                .offset(x = offsetX)
        )
    }
    // [END android_compose_animate_item_placement]
}