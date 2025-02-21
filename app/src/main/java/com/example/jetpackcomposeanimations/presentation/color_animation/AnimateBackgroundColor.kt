package com.example.jetpackcomposeanimations.presentation.color_animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorBlue
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorGreen


@Preview
@Composable
fun AnimateBackgroundColor() {
    var animateBackgroundColor by remember {
        mutableStateOf(true)
    }

    val animatedColor by animateColorAsState(
        targetValue = if (animateBackgroundColor) colorGreen else colorBlue,
        label = "color"
    )

    Box(
        modifier = Modifier
            .drawBehind {
                drawRect(animatedColor)
            }
            .fillMaxSize()
    ) {
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {
                // Toggle the color
                animateBackgroundColor = !animateBackgroundColor
            }
        ) {
            Text("Change color")
        }
    }
}