package com.example.jetpackcomposeanimations.presentation.shape_animation.translation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorBlue
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorBlue
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorGreen
import kotlin.math.roundToInt

@Preview
@Composable
fun AnimateOffset() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // [START android_compose_animation_offset_change]
        var moved by remember { mutableStateOf(false) }
        val pxToMove = with(LocalDensity.current) {
            100.dp.toPx().roundToInt()
        }
        val offset by animateIntOffsetAsState(
            targetValue = if (moved) {
                IntOffset(pxToMove, pxToMove)
            } else {
                IntOffset.Zero
            },
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow     //Very low for scroll like effect
            ),
            label = "offset"
        )

        Box(
            modifier = Modifier
                .offset {
                    offset
                }
                .background(colorBlue)
                .size(100.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    moved = !moved
                }
        )
        // [END android_compose_animation_offset_change]
    }
}