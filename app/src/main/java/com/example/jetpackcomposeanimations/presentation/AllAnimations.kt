package com.example.jetpackcomposeanimations.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.sidebar_animation.SideBarAnimation

@Preview
@Composable
fun AnimationExamplesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center, // Align content vertically in the center
        horizontalAlignment = Alignment.CenterHorizontally // Align content horizontally in the center
    ) {
            //************************** Color Change Animations **************************
            // AnimateBackgroundColor()
             //AnimateTextColor()
            // InfinitelyRepeatable()
            // InfinitelyRepeatableGradientColors()

            //************************** Shape Animations (Size or Padding) **************************
            // HideAndShowDiagonally()
            // HideSwiftly()
            // AnimatePadding()
            // AnimateSizeChange()
            // AnimateSizeChange_Specs()
            // TransitionExampleConcurrent()

            //************************** Button Animations **************************
            // AnimateElevation()

            //************************** Shape Animations (Translation) **************************
            // AnimateOffset()
            // AnimationLayout()              // Toggled boxes
            // AnimateAlignment()             // Change the alignment from left to right
            // SmoothAnimateText()               // For animating infinitely
            // ControlledSmoothAnimateText()     // For animating once
            // ConcurrentAnimatable()
            // SequentialAnimations()              // More than one animation
            // ConcurrentAnimations()

            //************************** Screen Navigation Animations **************************
            // To open details screen on click of the list item with animation
            // AnimateBetweenComposableDestinations()
            // AnimatedContentExampleSwitch()      // Loading - Loaded - Error

            //************************** Text Animations **************************
            val text = "Compose provides convenient APIs that allow you to solve for many common animation use cases. This section demonstrates how you can animate common properties of a composable."
            // TextExpandAnimation(text)      // Expanding text animation
            // RevealingTextOnclick()         // Reveals completely new text on click
            // SwipeableTextAnimation()       // Swiping text animation
            // AnimatedCounterScreen()        // Counter top-down animation
            // AnimatedCounterDownUP()        // Counter bottom-up animation
            // AnimatedCounterSimple()        // Counter blink animation
            // AnimatedVisibilitySample()
            // TextVisibilityAnimation(AnnotatedString("Click me!"))
            // AnimatedVisibilityMutable()     // To track visibility
            // TextWithPhotoBackground(Modifier.padding(top = 45.dp, start = 15.dp, end = 15.dp))
            // TypingAnimation(" Let's Dive Into the Market!", Modifier.padding(top = 45.dp, start = 15.dp, end = 15.dp))
            // TextWithMotion()
            SideBarAnimation()
            //************************** List Animations **************************
            // PagerAnimation()               // Horizontal scrolling with animation
            // ResponsiveGrid()               // Adapts based on screen size
            // ImageResizeOnScrollExample()   // Image resizes dynamically on scroll
            // DragDropList(items = ReorderItem, onMove = { fromIndex, toIndex -> ReorderItem.move(fromIndex, toIndex) })

            //************************** Image Animations **************************
            //RowPhotos()
            //StaggeredPhotos()
            //DemonSlayerPhotoGallery()
    }
}
