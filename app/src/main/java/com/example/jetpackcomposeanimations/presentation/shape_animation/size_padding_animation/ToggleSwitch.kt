package com.example.jetpackcomposeanimations.presentation.shape_animation.size_padding_animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@Composable
fun SquishyToggleSwitch(
    color: Color,
    containerHeight: Int = 34,
    containerWidth: Int = 60,
    circleSize: Int = 24,
    padding: Int =4
) {
    var isToggled by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val transition = updateTransition(targetState = isToggled, label = "Switch Transition")

    // Animatable properties
    val thumbPosition = remember { Animatable(0f) }
    val squishX = remember { Animatable(1f) }
    val squishY = remember { Animatable(1f) }
    val trackColor by animateColorAsState(
        targetValue = lerp(Color.Gray, color, thumbPosition.value),
        animationSpec = tween(durationMillis = 100),
        label = "Track Color"
    )

    // Define easing curves for smooth, jelly-like movement
    val stretchEasing = CubicBezierEasing(0.75f, 0f, 1f, 1f)
    val compressEasing = CubicBezierEasing(0f, 0f, 0.2f, 1f) // Softer squash

    fun animateToggle(targetState: Boolean) {
        val targetValue = if (targetState) 1f else 0f
        scope.launch {
            // Step 1: Slight Stretch before movement
            val stretchXJob = launch {
                squishX.animateTo(1.15f, animationSpec = tween(150, easing = stretchEasing))
            }
            val compressYJob = launch {
                squishY.animateTo(0.95f, animationSpec = tween(150, easing = stretchEasing))
            }
            joinAll(stretchXJob, compressYJob)

            // Step 2: Move while keeping squish
            val moveJob = launch {
                thumbPosition.animateTo(targetValue, animationSpec = tween(250, easing = compressEasing))
            }
            joinAll(moveJob)

            // Step 3: Gentle Squash after reaching the other side
            val squashXJob = launch {
                squishX.animateTo(0.95f, animationSpec = tween(150, easing = compressEasing))
            }
            val expandYJob = launch {
                squishY.animateTo(1.05f, animationSpec = tween(150, easing = compressEasing))
            }
            joinAll(squashXJob, expandYJob)

            // Step 4: Restore to normal
            launch { squishX.animateTo(1f, animationSpec = tween(200)) }
            launch { squishY.animateTo(1f, animationSpec = tween(200)) }
        }
    }

    val maxTranslation = with(LocalDensity.current) { (containerWidth - circleSize - (padding * 2)).dp.toPx() }

    Box(
        modifier = Modifier
            .size(containerWidth.dp, containerHeight.dp)
            .clip(CircleShape)
            .background(trackColor)
            .clickable {
                isToggled = !isToggled
                animateToggle(isToggled)
            }
            .padding(padding.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Canvas(
            modifier = Modifier
                .size(circleSize.dp)
                .graphicsLayer(
                    scaleX = squishX.value,
                    scaleY = squishY.value,
                    shape = CircleShape,
                    translationX = thumbPosition.value * maxTranslation, // FIXED TRANSLATION
                    shadowElevation = with(LocalDensity.current) { 6.dp.toPx() } // Outer shadow
                )

        ) {
            drawCircle(
                color = Color.White,
                style = Fill
            )

            // Inset Shadow Effect
            drawCircle(
                color = Color.Black.copy(alpha = 0.2f),
                radius = size.minDimension / 2,
                style = Stroke(width = 1f)
            )
        }
    }
}


@Composable
fun SquishyToggleScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
       /* Text(
            text = "Click on the switch",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(16.dp))*/
        SquishyToggleSwitch(Color(0xFF4CCF59), containerHeight = 95, containerWidth = 200, circleSize = 80, padding = 8) // Green
        Spacer(modifier = Modifier.height(16.dp))
        SquishyToggleSwitch(Color(0xFF3384FB)) // Blue
        Spacer(modifier = Modifier.height(16.dp))
        SquishyToggleSwitch(Color(0xFFFF3372), containerHeight = 100, containerWidth = 200, circleSize = 80, padding = 8) // Red (Boo)
        //                    Spacer(modifier = Modifier.height(20.dp))
    }
}




