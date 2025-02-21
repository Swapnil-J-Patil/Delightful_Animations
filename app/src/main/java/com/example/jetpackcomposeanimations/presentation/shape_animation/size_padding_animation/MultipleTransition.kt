package com.example.jetpackcomposeanimations.presentation.shape_animation.size_padding_animation

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateRect
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.util.BoxState


@Preview
@Composable
fun TransitionExampleConcurrent() {
    // [START android_compose_concurrent_transition]
    var currentState by remember { mutableStateOf(BoxState.Collapsed) }
    val transition = updateTransition(targetState = currentState, label = "transition")

    val rect by transition.animateRect(label = "rect") { state ->
        when (state) {
            BoxState.Collapsed -> Rect(0f, 0f, 200f, 200f) // Smaller rect
            BoxState.Expanded -> Rect(300f, 300f, 600f, 600f) // Larger rect
        }
    }
    val borderWidth by transition.animateDp(label = "borderWidth") { state ->
        when (state) {
            BoxState.Collapsed -> 4.dp // Thick border for visibility
            BoxState.Expanded -> 0.dp // No border
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    currentState = if (currentState == BoxState.Collapsed) {
                        BoxState.Expanded
                    } else {
                        BoxState.Collapsed
                    }
                }
        ) {
            // Draw a rectangle using the animated rect values
            drawRect(
                color = Color.Blue,
                topLeft = Offset(rect.left, rect.top),
                size = Size(rect.width - rect.left, rect.height - rect.top), // Ensure proper size
                style = if (borderWidth > 0.dp) {
                    Stroke(width = borderWidth.toPx()) // Animated border
                } else {
                    Fill // Filled rectangle when borderWidth = 0.dp
                }
            )
        }
    }
    // [END android_compose_concurrent_transition]
}