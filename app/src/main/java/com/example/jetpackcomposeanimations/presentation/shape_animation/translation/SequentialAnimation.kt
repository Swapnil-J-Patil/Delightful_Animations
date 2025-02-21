package com.example.jetpackcomposeanimations.presentation.shape_animation.translation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SequentialAnimations() {
    // [START android_compose_animation_sequential]
    val alphaAnimation = remember { Animatable(0f) }
    val yAnimation = remember { Animatable(0f) }

    LaunchedEffect("animationKey") {
        alphaAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500) // Smooth fade-in
        )
        yAnimation.animateTo(
            targetValue = 100f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy, // Bouncy motion
                stiffness = Spring.StiffnessLow                // Smooth and relaxed
            )
        )
        yAnimation.animateTo(
            targetValue = 500f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,  // Even bouncier motion
                stiffness = Spring.StiffnessLow            // Slightly stiffer
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                alpha = alphaAnimation.value
                translationY = yAnimation.value
            }
            .background(Color.Red) // Add a background color to make it visible
    )
    // [END android_compose_animation_sequential]
}