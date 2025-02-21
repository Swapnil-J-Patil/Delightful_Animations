package com.example.jetpackcomposeanimations.presentation.shape_animation.size_padding_animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorBlue
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorGreen
@Preview
@Composable
fun HideAndShowDiagonally() {
    Box(modifier = Modifier.fillMaxSize()) {
        // [START android_compose_animation_cookbook_visibility]
        var visible by remember {
            mutableStateOf(true)
        }
        // Animated visibility will eventually remove the item from the composition once the animation has finished.
        AnimatedVisibility(visible) {
            // your composable here
            // [START_EXCLUDE]
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(colorGreen)
            ) {
            }
            // [END_EXCLUDE]
        }
        // [END android_compose_animation_cookbook_visibility]
        Button(modifier = Modifier.align(Alignment.BottomCenter), onClick = {
            visible = !visible
        }) {
            Text("Show/Hide")
        }
    }
}