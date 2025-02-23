package com.example.jetpackcomposeanimations.presentation.card_animations

import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.DeferredTargetAnimation
import androidx.compose.animation.core.ExperimentalAnimatableApi
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.unit.*

@OptIn(ExperimentalAnimatableApi::class)
class AnimatedPlacementModifierNode(
    var lookaheadScope: LookaheadScope
) : ApproachLayoutModifierNode, Modifier.Node() {
    val offsetAnimation: DeferredTargetAnimation<IntOffset, AnimationVector2D> =
        DeferredTargetAnimation(IntOffset.VectorConverter)

    override fun isMeasurementApproachInProgress(lookaheadSize: IntSize): Boolean {
        return false
    }

    override fun Placeable.PlacementScope.isPlacementApproachInProgress(
        lookaheadCoordinates: LayoutCoordinates
    ): Boolean {
        val target = with(lookaheadScope) {
            lookaheadScopeCoordinates.localLookaheadPositionOf(lookaheadCoordinates).round()
        }
        offsetAnimation.updateTarget(target, coroutineScope)
        return !offsetAnimation.isIdle
    }

    @OptIn(ExperimentalComposeUiApi::class)
    override fun ApproachMeasureScope.approachMeasure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val placeable = measurable.measure(constraints)
        return layout(placeable.width, placeable.height) {
            val coordinates = coordinates
            if (coordinates != null) {
                val target = with(lookaheadScope) {
                    lookaheadScopeCoordinates
                        .localLookaheadPositionOf(coordinates)
                        .round()
                }
                val animatedOffset = offsetAnimation.updateTarget(target, coroutineScope)
                val placementOffset = with(lookaheadScope) {
                    lookaheadScopeCoordinates.localPositionOf(
                        coordinates,
                        Offset.Zero
                    ).round()
                }
                val (x, y) = animatedOffset - placementOffset
                placeable.place(x, y)
            } else {
                placeable.place(0, 0)
            }
        }
    }
}

data class AnimatePlacementNodeElement(val lookaheadScope: LookaheadScope) :
    ModifierNodeElement<AnimatedPlacementModifierNode>() {

    override fun update(node: AnimatedPlacementModifierNode) {
        node.lookaheadScope = lookaheadScope
    }

    override fun create(): AnimatedPlacementModifierNode {
        return AnimatedPlacementModifierNode(lookaheadScope)
    }
}

@Composable
fun DynamicCardListComponent(modifier: Modifier = Modifier) {
    val items = listOf(
        Triple("Title 1", "Description 1", Color(0xffff6f69)),
        Triple("Title 2", "Description 2", Color(0xffffcc5c)),
        Triple("Title 3", "Description 3", Color(0xff264653)),
        Triple("Title 4", "Description 4", Color(0xff2a9d84))
    )
    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    LookaheadScope {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            items.forEachIndexed { index, item ->
                val isSelected = selectedIndex == index
                val animatedHeight by animateFloatAsState(targetValue = if (isSelected) 200f else 100f)
                val animatedColor by animateFloatAsState(targetValue = if (isSelected) 0.8f else 1f)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(animatedHeight.dp)
                        .background(
                            color = item.third.copy(alpha = animatedColor),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable { selectedIndex = if (isSelected) null else index }
                        .padding(16.dp)
                        .then(AnimatePlacementNodeElement(this@LookaheadScope)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
                        Text(text = item.first, style = MaterialTheme.typography.labelLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = item.second, style = MaterialTheme.typography.bodySmall)
                        if (isSelected) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = { /* Handle button click */ },
                                colors = ButtonDefaults.buttonColors(containerColor = item.third)
                            ) {
                                Text(text = "Action", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}