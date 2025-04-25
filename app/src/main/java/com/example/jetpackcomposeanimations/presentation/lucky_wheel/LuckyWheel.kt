package com.example.jetpackcomposeanimations.presentation.lucky_wheel

import android.graphics.Typeface
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.R
import com.example.jetpackcomposeanimations.presentation.ui.theme.green
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit
import kotlin.io.path.Path
import kotlin.io.path.moveTo
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun LuckyWheel(
    items: List<String>,
    modifier: Modifier = Modifier,
    onSpinEnd: (Int) -> Unit
) {
    val sweepAngle = 360f / items.size
    val rotation = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()
    var spinning by remember { mutableStateOf(false) }
    val arcGradients = listOf(
        Color(0xFFFFA500) to Color(0xFFFFFF00), // Orange to Yellow
        Color(0xFF0E5C4C) to Color(0xFF23af92),
        Color(0xFFFFA500) to Color(0xFFFFFF00), // Orange to Yellow
        Color(0xFF0E5C4C) to Color(0xFF23af92),
        Color(0xFFFFA500) to Color(0xFFFFFF00), // Orange to Yellow
        Color(0xFF0E5C4C) to Color(0xFF23af92),
        Color(0xFFFFA500) to Color(0xFFFFFF00), // Orange to Yellow
        Color(0xFF0E5C4C) to Color(0xFF23af92),
        Color(0xFFFFA500) to Color(0xFFFFFF00), // Orange to Yellow
    )
    val scaleAnim = remember { Animatable(1f) }
    var isButtonEnabled by remember { mutableStateOf(true) }
    var countdownTime by remember { mutableStateOf(0L) } // in millis
    val countdownDisplay by remember(countdownTime) {
        derivedStateOf {
            val hours = TimeUnit.MILLISECONDS.toHours(countdownTime)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(countdownTime) % 60
            val seconds = TimeUnit.MILLISECONDS.toSeconds(countdownTime) % 60
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        }
    }


    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.aspectRatio(1f)
            .padding(horizontal = 10.dp)
    ) {
        // 🌀 Rotating part
        Box(
            modifier = Modifier
                .fillMaxSize()
                .rotate(rotation.value)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val radius = size.minDimension / 2 - 10
                val strokeWidthNew = 100f // Thickness of the ring
                val center = Offset(size.width / 2, size.height / 2)

                drawCircle(
                    color = Color.DarkGray,
                    radius = radius,
                    center = center,
                    style = Stroke(width = strokeWidthNew)
                )

                drawCircle(
                    color = Color(0xFFFFC107),
                    radius = radius + 50,
                    center = center,
                    style = Stroke(width = 20f)
                )
                drawIntoCanvas { canvas ->
                    val blurPaint = android.graphics.Paint().apply {
                        color = android.graphics.Color.argb(100, 255, 255, 255)
                        style = android.graphics.Paint.Style.STROKE
                        strokeWidth = 20f
                        isAntiAlias = true
                        maskFilter = android.graphics.BlurMaskFilter(
                            10f,
                            android.graphics.BlurMaskFilter.Blur.NORMAL
                        )
                    }


                    canvas.nativeCanvas.drawCircle(
                        center.x,
                        center.y,
                        radius + 10,
                        blurPaint
                    )
                }


            }
            Canvas(modifier = Modifier.fillMaxSize()) {
                val radius = size.minDimension / 2
                val center = Offset(size.width / 2, size.height / 2)

                items.forEachIndexed { index, label ->
                    val angle = index * sweepAngle
                    val (startColor, endColor) = arcGradients[index % arcGradients.size]

                    drawArc(
                        brush = Brush.linearGradient(
                            colors = listOf(startColor, endColor),
                            start = Offset(
                                center.x + radius * cos(Math.toRadians(angle.toDouble())).toFloat(),
                                center.y + radius * sin(Math.toRadians(angle.toDouble())).toFloat()
                            ),
                            end = center
                        ),
                        startAngle = angle,
                        sweepAngle = sweepAngle,
                        useCenter = true,
                        topLeft = Offset(center.x - radius, center.y - radius),
                        size = Size(radius * 2, radius * 2)
                    )

                    val angleRad = Math.toRadians((angle + sweepAngle / 2).toDouble())
                    val textOffset = Offset(
                        center.x + (radius * 0.6f * cos(angleRad)).toFloat(),
                        center.y + (radius * 0.6f * sin(angleRad)).toFloat()
                    )
                    drawIntoCanvas { canvas ->
                        val nativeCanvas = canvas.nativeCanvas
                        val paint = android.graphics.Paint().apply {
                            color = android.graphics.Color.WHITE
                            textAlign = android.graphics.Paint.Align.CENTER
                            textSize = 65f
                            isAntiAlias = true
                            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)

                        }

                        val textAngle = angle + sweepAngle / 2
                        val angleRad = Math.toRadians(textAngle.toDouble())

                        // Text position
                        val textX = center.x + (radius * 0.6f * cos(angleRad)).toFloat()
                        val textY = center.y + (radius * 0.6f * sin(angleRad)).toFloat()

                        nativeCanvas.save()

                        // Rotate the canvas around the text's position
                        nativeCanvas.rotate(textAngle.toFloat(), textX, textY)

                        // Draw upright text
                        nativeCanvas.drawText(
                            label,
                            textX,
                            textY,
                            paint
                        )

                        nativeCanvas.restore()
                    }

                }
            }
        }

        // 🎯 Pointer
        NeedlePointer(
            modifier = Modifier
                .align(Alignment.TopCenter)
        )

        // 🔘 Spin Button

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        )
        {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Splash Image",
                modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds, // Keeps the center portion of the image
            )
        }

    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = {

                if (!spinning) {
                    spinning = true
                    val targetRotation = (360f * (5..10).random()) + (0..360).random()

                    coroutineScope.launch {
                        scaleAnim.animateTo(
                            targetValue = 1.2f,
                            animationSpec = tween(durationMillis = 100)
                        )
                        scaleAnim.animateTo(
                            targetValue = 1f,
                            animationSpec = tween(durationMillis = 100)
                        )
                        rotation.animateTo(
                            targetValue = rotation.value + targetRotation,
                            animationSpec = tween(
                                durationMillis = 4000,
                                easing = FastOutSlowInEasing
                            )
                        )

                       // val finalRotation = rotation.value % 360
                        val adjustedRotation = (270f - (rotation.value % 360) + 360) % 360
                        val selectedIndex = (adjustedRotation / sweepAngle).toInt() % items.size
                        onSpinEnd(selectedIndex)
                        // Disable button and start 3-hour countdown
                        isButtonEnabled = false
                        countdownTime = 3 * 60 * 60 * 1000L // 3 hours in millis

                        while (countdownTime > 0) {
                            delay(1000L)
                            countdownTime -= 1000L
                        }

                        isButtonEnabled = true
                        spinning = false
                    }
                }
            },
            enabled = isButtonEnabled,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .scale(scaleAnim.value)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC107) ,
            ), shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(vertical = 12.dp),
        ) {
            Text(
                text = if (isButtonEnabled) "Spin" else "Next spin in: $countdownDisplay",
                color = Color.White
            )
        }
    }
}


