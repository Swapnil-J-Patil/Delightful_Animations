package com.example.jetpackcomposeanimations.presentation.navigation_animation.conditional_navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeanimations.presentation.util.UiState

@Composable
fun AnimatedContentExampleSwitch() {
    // [START android_compose_animation_cookbook_animated_content]
    var state by remember {
        mutableStateOf(UiState.Loading)
    }
    AnimatedContent(
        state,
        transitionSpec = {
            fadeIn(
                animationSpec = tween(3000)
            ) togetherWith fadeOut(animationSpec = tween(3000))
        },
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            state = when (state) {
                UiState.Loading -> UiState.Loaded
                UiState.Loaded -> UiState.Error
                UiState.Error -> UiState.Loading
            }
        },
        label = "Animated Content"
    ) { targetState ->
        when (targetState) {
            UiState.Loading -> {
                LoadingScreen()
            }

            UiState.Loaded -> {
                LoadedScreen()
            }

            UiState.Error -> {
                ErrorScreen()
            }
        }
    }
    // [END android_compose_animation_cookbook_animated_content]
}