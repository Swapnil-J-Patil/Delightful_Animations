package com.example.jetpackcomposeanimations.presentation.text_animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AnimatedVisibilitySample() {
    // [START android_compose_animations_animated_visibility]
    var editable by remember { mutableStateOf(true) }
    AnimatedVisibility(visible = editable) {
        Text(text = "Edit")
    }
    // [END android_compose_animations_animated_visibility]
}