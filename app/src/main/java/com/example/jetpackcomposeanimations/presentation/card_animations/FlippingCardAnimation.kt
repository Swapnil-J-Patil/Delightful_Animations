package com.example.jetpackcomposeanimations.presentation.card_animations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorGreen
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.sign

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FlippingCardAnimation() {
    val priorities = remember { mutableStateListOf(1f, 0.95f, 0.9f,0.85f) }
    val colors = remember { mutableStateListOf(
        Color(0xFF459FF7), Color(0xFFF86934), Color(0xFFF0D442), colorGreen
    )
    } // Dynamic color list

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom=10.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        for (i in 3 downTo 0) {
            CardContainer(
                color = colors[i],  // Use dynamic colors
                priority = priorities[i],
                index = i, // Pass index to adjust Y-offset correctly
                onMoveToBack = {
                    // Rotate both priority list and color list
                    priorities.add(priorities.removeAt(0))
                    colors.add(colors.removeAt(0)) // Moves front card's color to back
                }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CardContainer(
    color: Color,
    priority: Float,
    index: Int,
    onMoveToBack: () -> Unit
) {
    val yOffset = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }
    val scale = remember { Animatable(priority) }
    var isRightFlick by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    // Offset cards so that the back cards are slightly visible
    val getInitialOffset = when (index) {
        0 -> 0f      // Front card (fully visible)
        1 -> -25f     // Middle card (partially visible)
        2 -> -50f     // Back card (even lower)
        3 -> -75f     // Back card (even lower)
        else -> 0f
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .offset(y = (getInitialOffset + yOffset.value).dp)
            .graphicsLayer(
                rotationZ = rotation.value,
                scaleX = scale.value,
                scaleY = scale.value
            )
            .background(color, RoundedCornerShape(8.dp))
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        isRightFlick = offset.x > size.width / 2
                    },
                    onDrag = { _, dragAmount ->
                        scope.launch {
                            yOffset.snapTo(yOffset.value - 100f)
                            rotation.snapTo(-100f / 5)
                        }
                    },
                    onDragEnd = {
                        scope.launch {
                            coroutineScope {
                                // Step 1: Animate the card off-screen (UP instead of DOWN)
                                val offsetJob=launch {
                                    yOffset.animateTo(
                                        targetValue = -250f, // Moves upwards instead of down
                                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                                    )
                                }
                                val rotationJob=launch {
                                    rotation.animateTo(
                                        targetValue = if (isRightFlick) 360f else -360f,
                                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                                    )
                                }
                                val scaleJob=launch {
                                    scale.animateTo(
                                        targetValue = 0.8f,
                                        animationSpec = tween(300)
                                    )
                                }
                                joinAll(offsetJob, rotationJob,scaleJob) // Wait for both animations to complete

                            }
                            // Step 2: Swap card position (AFTER animation)
                            onMoveToBack()

                            // Step 3: Reset animations
                            yOffset.snapTo(0f)
                            rotation.snapTo(0f)
                            scale.snapTo(priority)
                        }
                    }
                )
            }
    )
}







