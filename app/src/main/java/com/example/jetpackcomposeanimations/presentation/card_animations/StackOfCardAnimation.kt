package com.example.jetpackcomposeanimations.presentation.card_animations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun StackOfCardAnimation() {
    val scope = rememberCoroutineScope()
    val animatableOffset = remember { Animatable(0f) }
    val animatableScale = remember { Animatable(1f) }
    val stackScale = remember { Animatable(1f) } // Controls scaling for rest of the cards

// List of soft pastel colors
    val colorList = listOf(
        Color(0xFFFFE0B2), // Light Orange
        Color(0xFFBBDEFB), // Light Blue
        Color(0xFFC8E6C9), // Light Green
        Color(0xFFFFCDD2), // Light Red
        Color(0xFFFFF9C4)  // Light Yellow
    )

// Associate each card with a specific color
    val cards = remember {
        mutableStateListOf(
            "Card 1" to colorList[0],
            "Card 2" to colorList[1],
            "Card 3" to colorList[2],
            "Card 4" to colorList[3],
            "Card 5" to colorList[4]
        )
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        cards.reversed().forEachIndexed { index, (text, color) ->
            val scale = if (index == cards.lastIndex) animatableScale.value
            else (stackScale.value - ((cards.size - index - 1) * 0.05f)) // Adjusted scale
            val yOffset = if (index == cards.lastIndex) animatableOffset.value
            else (cards.size - index - 1) * -20f

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .padding(16.dp)
                    .offset(y = yOffset.dp)
                    .scale(scale)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = {
                                scope.launch {
                                    stackScale.animateTo(1.1f, animationSpec = spring()) // Shrink stack on drag
                                }
                            },
                            onDrag = { _, _ -> },
                            onDragEnd = {
                                scope.launch {
                                    coroutineScope {
                                        val offsetJob = launch {
                                            animatableOffset.animateTo(
                                                targetValue = -260f,
                                                animationSpec = spring(
                                                    dampingRatio = Spring.DampingRatioLowBouncy,
                                                    stiffness = Spring.StiffnessLow
                                                )
                                            )
                                        }
                                        val scaleJob = launch {
                                            animatableScale.animateTo(
                                                targetValue = 0.9f,
                                                animationSpec = spring(
                                                    dampingRatio = Spring.DampingRatioLowBouncy,
                                                    stiffness = Spring.StiffnessLow
                                                )
                                            )
                                        }

                                        joinAll(offsetJob, scaleJob) // Wait for both animations to complete
                                    }

                                    // Bring stack back to original size
                                    stackScale.animateTo(1f, animationSpec = spring())

                                    // Move the top card (with its assigned color) to the back of the list
                                    val removedCard = cards.removeFirst()
                                    cards.add(removedCard)

                                    // Reset values instantly for the next card
                                    animatableOffset.snapTo(0f)
                                    animatableScale.snapTo(1f)
                                }
                            }
                        )
                    },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = color), // Use assigned color
                elevation = CardDefaults.cardElevation(7.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text, fontSize = 20.sp, color = Color.Black)
                }
            }
        }
    }
}





