package com.example.jetpackcomposeanimations.presentation.text_animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeanimations.presentation.ui.theme.YellowAccent
import com.example.jetpackcomposeanimations.presentation.ui.theme.gold

@Composable
fun SplitTextReveal() {
    var startAnimation by remember { mutableStateOf(false) }
    val transition = rememberInfiniteTransition()

    val offsetY by transition.animateFloat(
        initialValue = 0f,
        targetValue = 150f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "offsetY"
    )

    val offsetX by transition.animateFloat(
        initialValue = -100f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "offsetX"
    )

    val particleOpacity by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "particleOpacity"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (!startAnimation) {
            BasicText(
                text = "HELLO EVERYONE",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 60.sp,
                    fontWeight = FontWeight.Bold,
                    color = YellowAccent,
                    letterSpacing = 1.5.sp
                )
            )
        } else {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val paint = android.graphics.Paint().apply {
                    color = YellowAccent.toArgb()
                    textAlign = android.graphics.Paint.Align.CENTER
                    textSize = 120f
                    isAntiAlias = true
                }

                val text = "HELLO EVERYONE"
                val textWidth = paint.measureText(text)
                val x = size.width / 2
                val y = size.height / 2

                val particleCount = 20
                for (i in 0 until particleCount) {
                    val particleX = x + (i - particleCount / 2) * 30 * (1 - particleOpacity)
                    val particleY = y + (i - particleCount / 2) * 5 * (1 - particleOpacity)
                    paint.color = android.graphics.Color.argb(
                        (particleOpacity * 255).toInt(),
                        255,
                        202,
                        89
                    )
                    this.drawContext.canvas.nativeCanvas.drawCircle(particleX, particleY, 3f, paint)
                }

                this.drawContext.canvas.nativeCanvas.apply {
                    val cutRatio = 0.5f
                    val cutHeight = paint.textSize * cutRatio

                    save()
                    translate(x, y - offsetY)
                    clipRect(-textWidth / 2, -cutHeight, textWidth / 2, 0f)
                    drawText(text, 0f, cutHeight, paint)
                    restore()

                    save()
                    translate(x, y + offsetY)
                    clipRect(-textWidth / 2, 0f, textWidth / 2, cutHeight)
                    drawText(text, 0f, cutHeight, paint)
                    restore()
                }
            }

            AnimatedVisibility(
                visible = offsetY >= 50f && offsetY < 150f,
                enter = fadeIn(animationSpec = tween(durationMillis = 500)) + slideInHorizontally(),
                exit = fadeOut(animationSpec = tween(durationMillis = 500)) + slideOutHorizontally()
            ) {
                BasicText(
                    text = "HAVE A GREAT DAY",
                    modifier = Modifier.offset(x = offsetX.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = gold,
                        letterSpacing = 1.5.sp
                    )
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        startAnimation = true
    }
}