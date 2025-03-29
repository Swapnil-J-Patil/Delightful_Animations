package com.example.jetpackcomposeanimations.presentation.text_animation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun ControlledSmoothAnimateText() {
    var isAnimating by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isAnimating) 7f else 1f,
        animationSpec = tween(durationMillis = 1000),
        finishedListener = {
            if (!isAnimating) isAnimating = true // Trigger reverse animation manually
        }
    )
    LaunchedEffect(Unit) {
        isAnimating=!isAnimating
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Hello",
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin.Center
                }
                .align(Alignment.Center),
            style = LocalTextStyle.current.copy()
        )
    }
}