package com.example.jetpackcomposeanimations.presentation.button_animation

import android.graphics.PathMeasure
import android.graphics.RectF
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.ui.theme.electricBlue

import android.graphics.BlurMaskFilter
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.animation.core.*
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.TextUnit
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.material3.Text
import androidx.compose.animation.core.*
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import com.example.jetpackcomposeanimations.R
import com.example.jetpackcomposeanimations.presentation.ui.theme.NeonFont

@Composable
fun NeonShimmerPipeOnPath() {
    val pathFraction = remember { Animatable(0f) }

    // Animate progress along path
    LaunchedEffect(Unit) {
        pathFraction.animateTo(
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(300.dp, 200.dp)) {
            val cornerRadius = 40.dp.toPx()
            val beamWidth = 8.dp.toPx()
            val glowWidth = 24.dp.toPx()
            val beamFraction = 1f

            val rect = RectF(0f, 0f, size.width, size.height)
            val androidPath = android.graphics.Path().apply {
                addRoundRect(rect, cornerRadius, cornerRadius, android.graphics.Path.Direction.CW)
            }

            val pm = PathMeasure(androidPath, false)
            val totalLength = pm.length
            val animatedDistance = pathFraction.value * totalLength
            val beamLength = beamFraction * totalLength

            val start = (animatedDistance - beamLength + totalLength) % totalLength
            val end = animatedDistance

            val beamPath = android.graphics.Path()

            if (start < end) {
                pm.getSegment(start, end, beamPath, true)
            } else {
                val part1 = android.graphics.Path()
                val part2 = android.graphics.Path()
                pm.getSegment(start, totalLength, part1, true)
                pm.getSegment(0f, end, part2, true)
                beamPath.addPath(part1)
                beamPath.addPath(part2)
            }

            drawIntoCanvas { canvas ->
                val nativeCanvas = canvas.nativeCanvas

                // 1. Glow Layer
                val glowPaint = Paint().apply {
                    color = Color.Cyan.copy(alpha = 0.4f).toArgb()
                    style = Paint.Style.STROKE
                    strokeWidth = glowWidth
                    isAntiAlias = true
                    strokeCap = Paint.Cap.ROUND
                    strokeJoin = Paint.Join.ROUND
                    maskFilter = BlurMaskFilter(glowWidth, BlurMaskFilter.Blur.NORMAL)
                }
                nativeCanvas.drawPath(beamPath, glowPaint)

                // 2. Solid Beam
                val solidPaint = Paint().apply {
                    color = Color.Cyan.toArgb()
                    style = Paint.Style.STROKE
                    strokeWidth = beamWidth
                    isAntiAlias = true
                    strokeCap = Paint.Cap.ROUND
                    strokeJoin = Paint.Join.ROUND
                }
                nativeCanvas.drawPath(beamPath, solidPaint)

                // 3. Shimmer Overlay using native LinearGradient
                val shimmerPaint = Paint().apply {
                    shader = LinearGradient(
                        0f,
                        0f,
                        size.width,
                        size.height,
                        intArrayOf(
                            Color.Transparent.toArgb(),
                            Color.White.copy(alpha = 0.8f).toArgb(),
                            Color.Transparent.toArgb()
                        ),
                        floatArrayOf(0f, 0.5f, 1f),
                        Shader.TileMode.CLAMP
                    )
                    style = Paint.Style.STROKE
                    strokeWidth = beamWidth
                    isAntiAlias = true
                    strokeCap = Paint.Cap.ROUND
                    strokeJoin = Paint.Join.ROUND
                }
                nativeCanvas.drawPath(beamPath, shimmerPaint)
            }
        }
    }
}

@Composable
fun PipeMovingOnRoundedRectBorder() {
    val pathFraction = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        pathFraction.animateTo(
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 2000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }
    val context = LocalContext.current
    val cursiveTypeface = remember {
        ResourcesCompat.getFont(context, R.font.cursive)!!
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        //NeonGlowingBannerText(text = "Neon Magic", fontSize = 50)
        NeonGlowingBannerTextWithCursive(
            text = "Neon Magic",
            cursiveTypeface = cursiveTypeface,
            fontSize = 55
        )

        Canvas(modifier = Modifier.size(300.dp, 200.dp)) {
            val cornerRadius = 40.dp.toPx()
            val beamLengthFraction = 1f
            val glowColor = android.graphics.Color.CYAN
            val strokeWidthPx = 6.dp.toPx()

            val rect = RectF(0f, 0f, size.width, size.height)

            // Path for measuring
            val androidPath = android.graphics.Path().apply {
                addRoundRect(rect, cornerRadius, cornerRadius, android.graphics.Path.Direction.CW)
            }

            val pm = PathMeasure(androidPath, false)
            val totalLength = pm.length
            val animatedDistance = pathFraction.value * totalLength
            val beamLength = beamLengthFraction * totalLength

            val pipePath = android.graphics.Path()
            val start = (animatedDistance - beamLength).let { if (it < 0) it + totalLength else it }
            val end = animatedDistance

            // Compose visible border
            val drawPath = Path().apply {
                addRoundRect(
                    androidx.compose.ui.geometry.RoundRect(
                        left = 0f,
                        top = 0f,
                        right = size.width,
                        bottom = size.height,
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius)
                    )
                )
            }
            drawPath(
                path = drawPath,
                color = Color.DarkGray,
                style = Stroke(width = 6.dp.toPx())
            )

            // Extract segment
            if (start < end) {
                pm.getSegment(start, end, pipePath, true)
            } else {
                pm.getSegment(start, totalLength, pipePath, true)
                pm.getSegment(0f, end, pipePath, true)
            }

            // Draw glow + core
            drawIntoCanvas { canvas ->
                val nativeCanvas = canvas.nativeCanvas

                // 🌟 Glow layer (blurred, thick, semi-transparent)
                val glowPaint = android.graphics.Paint().apply {
                    color = glowColor
                    style = android.graphics.Paint.Style.STROKE
                    strokeWidth = strokeWidthPx * 4f
                    isAntiAlias = true
                    strokeCap = android.graphics.Paint.Cap.ROUND
                    strokeJoin = android.graphics.Paint.Join.ROUND
                    maskFilter = android.graphics.BlurMaskFilter(strokeWidthPx * 2f, android.graphics.BlurMaskFilter.Blur.NORMAL)
                    alpha = 100
                }
                nativeCanvas.drawPath(pipePath, glowPaint)

                // 💠 Core beam (sharp)
                val corePaint = android.graphics.Paint().apply {
                    color = glowColor
                    style = android.graphics.Paint.Style.STROKE
                    strokeWidth = strokeWidthPx
                    isAntiAlias = true
                    strokeCap = android.graphics.Paint.Cap.ROUND
                    strokeJoin = android.graphics.Paint.Join.ROUND
                }
                nativeCanvas.drawPath(pipePath, corePaint)
            }
        }
    }
}

@Composable
fun NeonGlowingBannerText(
    text: String,
    fontSize: Int = 16,
    fontColor: Color = electricBlue,
    fontWeight: FontWeight = FontWeight.Bold,
    alignment: Alignment = Alignment.Center,
    fontFamily: FontFamily = NeonFont,
    shadowColor: Color = electricBlue.copy(alpha = 0.2f),
    outlineColor: Color = electricBlue.copy(alpha = 0.5f),
    borderStrokeWidth: Float = 8f,
    textAlign: TextAlign = TextAlign.Center,
    blurRadius: Float = 10f
) {

    Box(
        modifier = Modifier,
        contentAlignment = alignment
    ) {
        // Gray border (outline effect)
        Text(
            text = text,
            style = TextStyle(
                fontSize = fontSize.sp,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                color = outlineColor, // Gray outline color
                shadow = Shadow(
                    color = shadowColor,
                ),
                drawStyle = Stroke(width = borderStrokeWidth) // Border effect
            ),
            textAlign = textAlign,
        )

        // Golden gradient text
        Text(
            text = text,
            style = TextStyle(
                fontSize = fontSize.sp,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                color = fontColor // Gold
                ,
                shadow = Shadow(
                    color = shadowColor,
                    blurRadius = blurRadius
                )
            ),
            textAlign = textAlign,
        )
    }
}


@Composable
fun NeonGlowingBannerTextWithCursive(
    text: String,
    fontSize: Int = 64,
    fontColor: Color = electricBlue,
    fontFamily: FontFamily = NeonFont,
    cursiveTypeface: Typeface,
    blurRadius: Float = 50f,        // Higher blur for strong glow
    glowAlpha: Int = 200,           // 0–255, adjust glow transparency
    glowStrokeWidthMultiplier: Float = 2.5f // Emulate outer beam thickness
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .drawBehind {
                val nativeCanvas = drawContext.canvas.nativeCanvas

                val textPaint = android.graphics.Paint().apply {
                    color = fontColor.copy(alpha = glowAlpha / 255f).toArgb()
                    textSize = fontSize.sp.toPx()
                    typeface = cursiveTypeface
                    isAntiAlias = true
                    style = android.graphics.Paint.Style.FILL
                    strokeWidth = fontSize.sp.toPx() / 10f * glowStrokeWidthMultiplier
                    maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
                    textAlign = android.graphics.Paint.Align.CENTER
                }

                nativeCanvas.drawText(
                    text,
                    size.width / 2f,
                    size.height / 2f + fontSize.sp.toPx() / 3f,
                    textPaint
                )
            },
        contentAlignment = Alignment.Center
    ) {
        // Core crisp text on top
        Text(
            text = text,
            fontSize = fontSize.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            color = fontColor,
            textAlign = TextAlign.Center
        )
    }
}



