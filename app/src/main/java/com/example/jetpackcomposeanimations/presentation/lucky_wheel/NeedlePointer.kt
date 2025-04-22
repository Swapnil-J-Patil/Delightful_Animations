package com.example.jetpackcomposeanimations.presentation.lucky_wheel

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp

@Composable
fun NeedlePointer(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .size(40.dp, 60.dp) // Adjust size as needed
            .offset(y=-30.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val centerX = canvasWidth / 2
        val radius = canvasWidth / 2

        // 🔵 Draw circular top
        drawCircle(
            color = Color.DarkGray, // Orange
            radius = radius,
            center = Offset(centerX, radius)
        )

        // 🔻 Draw triangle pointer
        val trianglePath = Path().apply {
            moveTo(centerX, canvasHeight) // Triangle tip
            lineTo(centerX - radius, radius) // Left base
            lineTo(centerX + radius, radius) // Right base
            close()
        }

        drawPath(
            path = trianglePath,
            color = Color.DarkGray, // Orange
        )
    }
}


