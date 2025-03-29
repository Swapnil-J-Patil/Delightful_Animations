package com.example.jetpackcomposeanimations.presentation.color_animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InfinitelyRepeatableGradientColors() {
    // Gradient colors
    val brushColors = listOf(Color(0xFF23af92), Color(0xFF0E5C4C))

    // Animation state
    val infiniteTransition = rememberInfiniteTransition(label = "background")
    val targetOffset = with(LocalDensity.current){
        1000.dp.toPx()
    }
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = targetOffset, // Adjust based on desired movement range
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offset"
    )

    // Composable with animated gradient
    Column(
        modifier = Modifier
            .fillMaxSize()
            .blur(40.dp)
            .drawWithCache {
                val brushSize = 400f
                val brush = Brush.linearGradient(
                    colors = brushColors,
                    start = Offset(animatedOffset, animatedOffset),
                    end = Offset(animatedOffset + brushSize, animatedOffset + brushSize),
                    tileMode = TileMode.Mirror
                )
                onDrawBehind {
                    drawRect(brush)
                }
            }
    ) {
        // Add your other composables here
    }
}