package com.example.jetpackcomposeanimations.presentation.shape_animation.translation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ConcurrentAnimations() {
    // [START android_compose_animation_concurrent]
    val alphaAnimation = remember { Animatable(0f) }
    val yAnimation = remember { Animatable(0f) }

    LaunchedEffect("animationKey") {
        // Launch concurrent animations
        launch {
            alphaAnimation.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 2000) // Smooth fade-in
            )
        }
        launch {
            yAnimation.animateTo(
                targetValue = 200f, // Visible translation range
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy, // Bouncy vertical movement
                    stiffness = Spring.StiffnessLow                // Smooth and relaxed
                )
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray) // Background to contrast animation
    ) {
        Box(
            modifier = Modifier
                .size(100.dp) // Constrained size for visibility
                .graphicsLayer {
                    alpha = alphaAnimation.value
                    translationY = yAnimation.value
                }
                .background(Color.Blue) // Add a visible color to the animated box
                .align(Alignment.TopCenter) // Align for better visual observation
        )
    }
    // [END android_compose_animation_concurrent]
}