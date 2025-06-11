package com.example.jetpackcomposeanimations.presentation.card_animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import com.example.jetpackcomposeanimations.presentation.ui.theme.blue
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorBlue
import com.example.jetpackcomposeanimations.presentation.ui.theme.green
import android.graphics.Paint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import com.example.jetpackcomposeanimations.presentation.ui.theme.electricBlue
import com.example.jetpackcomposeanimations.presentation.ui.theme.neonPink

@Composable
fun AnimatedBorderCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(size = 20.dp),
    borderWidth: Dp = 6.dp,
    gradient: Brush = Brush.linearGradient(listOf(electricBlue, neonPink)),
    animationDuration: Int = 5000,
    onCardClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "Infinite Color Animation")
    val degrees by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Infinite Colors"
    )

    Surface(
        modifier = modifier
            .clipToBounds()
            .clip(RoundedCornerShape(20.dp))
            .clickable { onCardClick() }
            .padding(borderWidth)
            .size(300.dp)
            // .animatedBorder()
            .drawBehind {
                rotate(degrees = degrees) {
                    drawCircle(
                        brush = gradient,
                        radius = size.width,
                        blendMode = BlendMode.SrcIn,
                    )
                }
            },
        color = MaterialTheme.colorScheme.surface,
        shape = shape
    ) {
        content()
    }
}




@Composable
fun GlowingCard(
    glowingColor: Color,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    cornersRadius: Dp = 20.dp,
    glowingRadius: Dp = 30.dp,
    xShifting: Dp = 0.dp,
    yShifting: Dp = 0.dp,
    animationDuration: Int = 5000,
    content: @Composable BoxScope.() -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "Infinite Color Animation")

    val degrees by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Infinite Colors"
    )
    Box(
        modifier = modifier
            .drawBehind {
                    val canvasSize = size
                    drawContext.canvas.nativeCanvas.apply {
                        drawRoundRect(
                            0f, // Left
                            0f, // Top
                            canvasSize.width, // Right
                            canvasSize.height, // Bottom
                            cornersRadius.toPx(), // Radius X
                            cornersRadius.toPx(), // Radius Y
                            Paint().apply {
                                color = containerColor.toArgb()
                                isAntiAlias = true
                                setShadowLayer(
                                    glowingRadius.toPx(),
                                    xShifting.toPx(), yShifting.toPx(),
                                    glowingColor.copy(alpha = 0.85f).toArgb()
                                )
                            }
                        )
                    }
            }
            .shadow(spotColor=glowingColor,ambientColor =  glowingColor, elevation = 50.dp, shape = RoundedCornerShape(20.dp))

    ) {
        content()
    }
}

/////////////////////////////////////////////////////////////////////

@Composable
fun ClickableGlowingCard(
    glowingColor: Color,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    cornersRadius: Dp = 0.dp,
    glowingRadius: Dp = 20.dp,
    xShifting: Dp = 0.dp,
    yShifting: Dp = 0.dp,
    onClick:() -> Unit = {},
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .drawBehind {
                val canvasSize = size
                drawContext.canvas.nativeCanvas.apply {
                    drawRoundRect(
                        0f, // Left
                        0f, // Top
                        canvasSize.width, // Right
                        canvasSize.height, // Bottom
                        cornersRadius.toPx(), // Radius X
                        cornersRadius.toPx(), // Radius Y
                        Paint().apply {
                            color = containerColor.toArgb()
                            isAntiAlias = true
                            setShadowLayer(
                                glowingRadius.toPx(),
                                xShifting.toPx(), yShifting.toPx(),
                                glowingColor.copy(alpha = 0.85f).toArgb()
                            )
                        }
                    )
                }
            }
    ) {
        Box(modifier = Modifier.clip(RoundedCornerShape(cornersRadius)).clickable { onClick() }){
            content()
        }
    }
}