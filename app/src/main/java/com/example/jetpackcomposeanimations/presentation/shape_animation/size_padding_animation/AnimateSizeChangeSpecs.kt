package com.example.jetpackcomposeanimations.presentation.shape_animation.size_padding_animation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
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
fun AnimateSizeChange_Specs() {
    Row(modifier = Modifier.fillMaxSize()) {
        var expanded by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        ) {
            Text("No spec set")
            Box(
                modifier = Modifier
                    .background(colorBlue)
                    .animateContentSize()
                    .height(if (expanded) 300.dp else 200.dp)
                    .fillMaxSize()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        expanded = !expanded
                    }

            ) {
            }
        }
        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        ) {
            Text("Custom spec")
            // [START android_compose_animation_size_change_spec]
            Box(
                modifier = Modifier
                    .background(colorBlue)
                    .animateContentSize(
                        spring(
                            stiffness = Spring.StiffnessLow,
                            dampingRatio = Spring.DampingRatioHighBouncy
                        )
                    )
                    .height(if (expanded) 300.dp else 200.dp)
                    .fillMaxSize()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        expanded = !expanded
                    }

            ) {
            }
            // [END android_compose_animation_size_change_spec]
        }
    }
}