package com.example.jetpackcomposeanimations.presentation.image_animations

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SwingingPainting(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "swing")

    // Time in radians
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (2 * Math.PI).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2800, // slower & calmer
                easing = LinearEasing
            )
        ),
        label = "time"
    )

    // Physics-inspired rotation
    val maxAngle = 4f
    val damping = 0.85f

    val rotation = (sin(time.toDouble()) * maxAngle * damping).toFloat()

    // Subtle depth
    val depth = (cos(time.toDouble()) * 6f).toFloat()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {

        // Nail
        Box(
            modifier = Modifier
                .size(6.dp)
                .offset(y = (-14).dp)
                .background(Color.Black, CircleShape)
                .zIndex(2f)
        )

        // Wire / hook
        Canvas(
            modifier = Modifier
                .size(20.dp)
                .offset(y = (-10).dp)
                .graphicsLayer {
                    rotationZ = rotation
                    rotationX = depth * 0.1f
                    transformOrigin = TransformOrigin(0.5f, -0.2f)
                    cameraDistance = 12 * density
                }
                .zIndex(1f)
        ) {
            rotate(225f) {
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 2f
                )
                drawLine(
                    color = Color.Gray,
                    start = Offset(size.width, 0f),
                    end = Offset(size.width, size.height),
                    strokeWidth = 2f
                )
            }
        }

        // Painting
        Box(
            modifier = modifier
                .graphicsLayer {
                    rotationZ = rotation
                    rotationX = depth * 0.1f
                    transformOrigin = TransformOrigin(0.5f, -0.2f)
                    cameraDistance = 12 * density
                }
                .shadow(8.dp, RectangleShape)
                .border(5.dp, Color(0xFFF8F8F8))
        ) {
            AsyncImage(
                model = imageUrl,
                modifier = modifier,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
    }
}
