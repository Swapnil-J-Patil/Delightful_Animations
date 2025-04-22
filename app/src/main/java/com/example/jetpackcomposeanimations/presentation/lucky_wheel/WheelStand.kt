package com.example.jetpackcomposeanimations.presentation.lucky_wheel

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun WheelStand(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height

        // Trapezoid part
        val topWidth = width * 0.2f
        val bottomWidth = width * 0.5f
        val trapezoidHeight = height * 0.6f

        val topLeft = Offset((width - topWidth) / 2f, -20f)
        val topRight = Offset((width + topWidth) / 2f, -20f)
        val bottomLeft = Offset((width - bottomWidth) / 2f, trapezoidHeight)
        val bottomRight = Offset((width + bottomWidth) / 2f, trapezoidHeight)

        val trapezoidPath = Path().apply {
            moveTo(topLeft.x, topLeft.y)
            lineTo(topRight.x, topRight.y)
            lineTo(bottomRight.x, bottomRight.y)
            lineTo(bottomLeft.x, bottomLeft.y)
            close()
        }

        drawPath(
            path = trapezoidPath,
            brush = Brush.verticalGradient(
                colors = listOf(Color.Black, Color.DarkGray),
                startY = 0f,
                endY = trapezoidHeight
            )
        )

        // Cylindrical base
        val baseHeight = height * 0.15f
        val baseTop = trapezoidHeight -10f
        drawRoundRect(
            brush = Brush.verticalGradient(
                colors = listOf(Color.Gray, Color.Black),
                startY = baseTop,
                endY = baseTop + baseHeight
            ),
            topLeft = Offset(x = (width - 150f - bottomWidth) / 2f, y = baseTop),
            size = Size(width = bottomWidth + 150f, height = baseHeight),
            cornerRadius = CornerRadius(20f, 20f)
        )
    }
}