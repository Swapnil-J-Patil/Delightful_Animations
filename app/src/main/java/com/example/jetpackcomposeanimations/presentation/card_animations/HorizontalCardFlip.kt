package com.example.jetpackcomposeanimations.presentation.card_animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorGreen
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@Composable
fun HorizontalCardFlip(modifier: Modifier = Modifier) {
    val priorities = remember { mutableStateListOf(1f, 0.95f, 0.9f, 0.85f) }
    val colors = remember {
        mutableStateListOf(
            Color(0xFF459FF7), Color(0xFFF86934), Color(0xFFF0D442), colorGreen
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        for (i in 3 downTo 0) {
            HorizontalCardContainer(
                color = colors[i],
                priority = priorities[i],
                index = i,
                onMoveToBack = {
                    priorities.add(priorities.removeAt(0))
                    colors.add(colors.removeAt(0))
                }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HorizontalCardContainer(
    color: Color,
    priority: Float,
    index: Int,
    onMoveToBack: () -> Unit
) {
    val xOffset = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }
    val scale = remember { Animatable(priority) }
    var isRightFlick by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    val getInitialOffset = when (index) {
        0 -> 0f
        1 -> -25f
        2 -> -50f
        3 -> -75f
        else -> 0f
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .offset(
                x = (xOffset.value).dp,
                y = (getInitialOffset).dp
            )
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
                            xOffset.snapTo(xOffset.value + dragAmount.x)
                            rotation.snapTo(xOffset.value / 5) // rotate based on horizontal movement
                        }
                    },
                    onDragEnd = {
                        scope.launch {
                            coroutineScope {
                                // Step 1: Animate card off-screen (left/right)
                                val direction = if (isRightFlick) 300f else -300f
                                val offsetJob = launch {
                                    xOffset.animateTo(
                                        targetValue = direction,
                                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                                    )
                                }
                                val rotationJob = launch {
                                    rotation.animateTo(
                                        targetValue = if (isRightFlick) 360f else -360f,
                                        animationSpec = tween(400, easing = FastOutSlowInEasing)
                                    )
                                }
                                val scaleJob = launch {
                                    scale.animateTo(
                                        targetValue = 0.8f,
                                        animationSpec = tween(300)
                                    )
                                }
                                joinAll(offsetJob, rotationJob, scaleJob)
                            }

                            onMoveToBack()

                            // Step 3: Reset animations
                            xOffset.snapTo(0f)
                            rotation.snapTo(0f)
                            scale.snapTo(priority)
                        }
                    }
                )
            }
    )
}


