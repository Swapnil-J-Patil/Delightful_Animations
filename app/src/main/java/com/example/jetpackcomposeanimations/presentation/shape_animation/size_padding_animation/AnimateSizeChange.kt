package com.example.jetpackcomposeanimations.presentation.shape_animation.size_padding_animation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorBlue
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorGreen
@Composable
fun AnimateSizeChange() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var expanded by remember { mutableStateOf(false) }

        // Custom animated height with a spring effect
        val animatedHeight by animateDpAsState(
            targetValue = if (expanded) 400.dp else 200.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy, //can set it to HighBouncy
                stiffness = Spring.StiffnessLow     //Very low for scroll like effect
            ),
            label = "Height Animation"
        )

        Box(
            modifier = Modifier
                .background(colorBlue)
                .height(animatedHeight)
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    expanded = !expanded
                }
        )
    }
}