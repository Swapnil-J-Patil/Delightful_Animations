package com.example.jetpackcomposeanimations.presentation.color_animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimateTextColor() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        // [START android_compose_animation_cookbook_text_color]
        val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
        val animatedColor by infiniteTransition.animateColor(
            initialValue = Color(0xFF60DDAD),
            targetValue = Color(0xFF4285F4),
            animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
            label = "color"
        )

        BasicText(
            text = "Hello Guys",
            color = {
                animatedColor
            },
            // [START_EXCLUDE]
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
            // [END_EXCLUDE]
        )
        // [END android_compose_animation_cookbook_text_color]
    }
}