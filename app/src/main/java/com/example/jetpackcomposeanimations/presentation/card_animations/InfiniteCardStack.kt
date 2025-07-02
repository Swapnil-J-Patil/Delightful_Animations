package com.example.jetpackcomposeanimations.presentation.card_animations

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun InfiniteCardStack(cards: List<Color>) {
    val visibleCount = 4
    val baseOffset = listOf(0f, -25f, -50f, -75f)
    val baseScale = listOf(1f, 0.95f, 0.9f, 0.85f)

    var topIndex by remember { mutableIntStateOf(0) } // Start from 0th card
    val scope = rememberCoroutineScope()
    var totalDrag by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp)
            .pointerInput(cards.size) {
                detectVerticalDragGestures(
                    onVerticalDrag = { _, dragAmount ->
                        totalDrag += dragAmount
                    },
                    onDragEnd = {
                        val threshold = 100f
                        val steps = (totalDrag / threshold).toInt()

                        if (steps != 0) {
                            topIndex = (topIndex + steps).mod(cards.size)
                        }

                        totalDrag = 0f
                    }
                )
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        for (i in (visibleCount - 1) downTo 0) {
            val cardIndex = (topIndex + i).mod(cards.size)
            val targetOffset = baseOffset[i]
            val targetScale = baseScale[i]

            val offsetAnim = animateFloatAsState(targetOffset, label = "")
            val scaleAnim = animateFloatAsState(targetScale, label = "")

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .offset(y = offsetAnim.value.dp)
                    .graphicsLayer {
                        scaleX = scaleAnim.value
                        scaleY = scaleAnim.value
                        translationY = 0f
                    }
                    .zIndex(i.toFloat()) // Keep layering
                    .background(color = cards[cardIndex], RoundedCornerShape(12.dp))
            )
        }
    }
}
