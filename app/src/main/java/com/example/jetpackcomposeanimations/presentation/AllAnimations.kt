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
import com.example.jetpackcomposeanimations.presentation.button_animation.FadeButtonAnimation
import com.example.jetpackcomposeanimations.presentation.button_animation.RotateButtonAnimation
import com.example.jetpackcomposeanimations.presentation.button_animation.ScaleButton
import com.example.jetpackcomposeanimations.presentation.button_animation.ShakeButtonAnimation
import com.example.jetpackcomposeanimations.presentation.card_animations.CardStackScreen
import com.example.jetpackcomposeanimations.presentation.card_animations.CardFlipAnimation
import com.example.jetpackcomposeanimations.presentation.card_animations.DynamicCardListComponent
import com.example.jetpackcomposeanimations.presentation.custom_toast_animation.CustomToast
import com.example.jetpackcomposeanimations.presentation.flow_layout_animation.FlowLayoutAnimation
import com.example.jetpackcomposeanimations.presentation.text_animation.AnimatedText
import com.example.jetpackcomposeanimations.presentation.text_animation.TextListAnimation

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

        //************************** Card Animations **************************
        //DynamicCardListComponent()
        //CardStackScreen()
        //CardFlipAnimation()

        //************************** Shape Animations (Size or Padding) **************************
        // HideAndShowDiagonally()
        // HideSwiftly()
        // AnimatePadding()
        // AnimateSizeChange()
        // AnimateSizeChange_Specs()
        // TransitionExampleConcurrent()

        //************************** Button Animations **************************
        // ScaleButton()
        // RotateButtonAnimation()
        // ShakeButtonAnimation()
        // FadeButtonAnimation()

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
        val text =
            "Compose provides convenient APIs that allow you to solve for many common animation use cases. This section demonstrates how you can animate common properties of a composable!!"
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
        // TextListAnimation()
        // AnimatedText()
        // CustomToast()

        //************************** Sidebar Animations **************************
        //SideBarAnimation()

        //************************** List Animations **************************
        val list = listOf(
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5",
            "Item 6",
            "Item 7",
            "Item 8",
            "Item 9",
            "Item 10",
            "Item 11",
            "Item 12",
            "Item 13",
            "Item 14",
            "Item 15",
        )
        // PagerAnimation()               // Horizontal scrolling with animation
        // ResponsiveGrid()               // Adapts based on screen size
        // ImageResizeOnScrollExample()   // Image resizes dynamically on scroll
        // DragDropList(items = ReorderItem, onMove = { fromIndex, toIndex -> ReorderItem.move(fromIndex, toIndex) })
        // ListAnimationWithFloatingButton(menuItems = list, onMenuItemClick = { item -> } )
        // FlowLayoutAnimation()

        //************************** List Animations **************************
        //DynamicCardListComponent(modifier = Modifier)


        //************************** Image Animations **************************
        //RowPhotos()
        //StaggeredPhotos()
        //DemonSlayerPhotoGallery()
    }
}
