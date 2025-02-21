package com.example.jetpackcomposeanimations.presentation.text_animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.ui.theme.YellowAccent

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RevealingTextOnclick() {
    // State to manage the selected state
    var selected by remember { mutableStateOf(false) }

    // Create the transition to animate properties when `selected` changes
    val transition = updateTransition(selected, label = "selected state")

    // Animate color and elevation based on `selected` state
    val borderColor by transition.animateColor(label = "border color") { isSelected ->
        if (isSelected) YellowAccent else Color.White
    }

    val elevation by transition.animateDp(label = "elevation") { isSelected ->
        if (isSelected) 10.dp else 2.dp
    }

    // Surface with animated properties
    Surface(
        modifier = Modifier
            .clickable { selected = !selected }  // Toggle selected state on click
            .padding(16.dp),  // Padding around the surface
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, borderColor),
        tonalElevation = elevation
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Hello, world!")

            /* // AnimatedVisibility: Shows or hides the second text based on `selected` state
             AnimatedVisibility(
                 visible = selected,  // Make it visible when `selected` is true
                 enter = expandVertically(),  // Use vertical expansion when it enters
                 exit = shrinkVertically()   // Use vertical shrink when it exits
             ) {
                 Text(text = "It is fine today.")
             }*/

            // AnimatedContent: Switch between text and icon based on `selected` state
            AnimatedContent(
                targetState = selected,  // Target state is `selected`
                transitionSpec = {
                    // You can specify custom transitions here if needed
                    fadeIn() with fadeOut()  // Example of fade transition
                }
            ) { targetState ->
                if (targetState) {
                    Text(text = "Compose provides convenient APIs that allow you to solve for many common animation use cases. This section demonstrates how you can animate common properties of a composable.")
                }
                else {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone")
                }
            }
        }
    }
}