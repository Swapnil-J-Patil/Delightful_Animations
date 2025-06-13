package com.example.jetpackcomposeanimations.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.button_animation.PipeMovingOnRoundedRectBorder
import com.example.jetpackcomposeanimations.presentation.card_animations.AnimatedBorderCard
import com.example.jetpackcomposeanimations.presentation.card_animations.DynamicCardListComponent
import com.example.jetpackcomposeanimations.presentation.card_animations.FlipCardAnimation
import com.example.jetpackcomposeanimations.presentation.card_animations.FlippingCardAnimation
import com.example.jetpackcomposeanimations.presentation.card_animations.GlowingCard
import com.example.jetpackcomposeanimations.presentation.card_animations.HorizontalCardFlip
import com.example.jetpackcomposeanimations.presentation.flow_layout_animation.FlowLayoutAnimation
import com.example.jetpackcomposeanimations.presentation.image_animations.RowPhotos
import com.example.jetpackcomposeanimations.presentation.image_animations.StaggeredPhotos
import com.example.jetpackcomposeanimations.presentation.image_animations.shared_transition_image.DemonSlayerPhotoGallery
import com.example.jetpackcomposeanimations.presentation.list_animation.ImageResizeOnScrollExample
import com.example.jetpackcomposeanimations.presentation.list_animation.ListAnimationWithFloatingButton
import com.example.jetpackcomposeanimations.presentation.list_animation.ResponsiveGrid
import com.example.jetpackcomposeanimations.presentation.list_animation.drag_drop_list.DragDropList
import com.example.jetpackcomposeanimations.presentation.list_animation.list_item_swipe.SwipeableTextAnimation
import com.example.jetpackcomposeanimations.presentation.list_animation.scaling_item_list.ScalingListItemAnimation
import com.example.jetpackcomposeanimations.presentation.lucky_wheel.LuckyWheelScreen
import com.example.jetpackcomposeanimations.presentation.navigation_animation.conditional_navigation.AnimatedContentExampleSwitch
import com.example.jetpackcomposeanimations.presentation.navigation_animation.shared_transition_screen.AnimateBetweenComposableDestinations
import com.example.jetpackcomposeanimations.presentation.sidebar_animation.SideBarAnimation
import com.example.jetpackcomposeanimations.presentation.text_animation.AnimatedText
import com.example.jetpackcomposeanimations.presentation.text_animation.ControlledSmoothAnimateText
import com.example.jetpackcomposeanimations.presentation.text_animation.RevealingTextOnclick
import com.example.jetpackcomposeanimations.presentation.text_animation.SmoothAnimateText
import com.example.jetpackcomposeanimations.presentation.text_animation.SplitTextReveal
import com.example.jetpackcomposeanimations.presentation.text_animation.TextExpandAnimation
import com.example.jetpackcomposeanimations.presentation.text_animation.TextListAnimation
import com.example.jetpackcomposeanimations.presentation.text_animation.TextVisibilityAnimation
import com.example.jetpackcomposeanimations.presentation.text_animation.TextWithMotion
import com.example.jetpackcomposeanimations.presentation.text_animation.TextWithPhotoBackground
import com.example.jetpackcomposeanimations.presentation.text_animation.TypingAnimation
import com.example.jetpackcomposeanimations.presentation.text_animation.counter_animation.AnimateCounterScreen
import com.example.jetpackcomposeanimations.presentation.text_animation.counter_animation.AnimatedCounterDownUP
import com.example.jetpackcomposeanimations.presentation.text_animation.counter_animation.AnimatedCounterSimple
import com.example.jetpackcomposeanimations.presentation.ui.theme.blue
import java.util.Collections

@Preview
@Composable
fun AnimationExamplesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f)),
        verticalArrangement = Arrangement.Center, // Align content vertically in the center
        horizontalAlignment = Alignment.CenterHorizontally // Align content horizontally in the center
    ) {
        //************************** Color Change Animations **************************
        // AnimateBackgroundColor()
        //AnimateTextColor()
        //InfinitelyRepeatable()
        // InfinitelyRepeatableGradientColors()

        //************************** Card Animations **************************
        //DynamicCardListComponent()
        //StackOfCardAnimation()
        //CardFlipAnimation()
        //FlippingCardAnimation()
        //FlipCardAnimation()
        // HorizontalCardFlip
        /*GlowingCard(
            glowingColor = blue,
            modifier = Modifier.size(300.dp),
            cornersRadius = 20.dp,
            content = {        AnimatedBorderCard(content = {}) }
        )*/

        //************************** Shape Animations (Size or Padding) **************************
        //HideAndShowDiagonally()
        // HideSwiftly()
        // AnimatePadding()
        // AnimateSizeChange()
        // AnimateSizeChange_Specs()

        //************************** Button Animations **************************
        // ScaleButton()
        // RotateButtonAnimation()
        // ShakeButtonAnimation()
        // FadeButtonAnimation()
        PipeMovingOnRoundedRectBorder()
        //NeonShimmerPipeOnPath()

        //************************** Shape Animations (Translation) **************************
        // AnimateOffset()
        // AnimationLayout()              // Toggled boxes
        // ConcurrentAnimatable()
        // SequentialAnimations()              // More than one animation
        // ConcurrentAnimations()

        //SquishyToggleScreen()

        //************************** Screen Navigation Animations **************************
        // To open details screen on click of the list item with animation
        // AnimateBetweenComposableDestinations()
        // AnimatedContentExampleSwitch()      // Loading - Loaded - Error

        //************************** Text Animations **************************

        val text =
            "Compose provides convenient APIs that allow you to solve for many common animation use cases. This section demonstrates how you can animate common properties of a composable!!"
        //ControlledSmoothAnimateText()     // For animating once
        //SmoothAnimateText()               // For animating infinitely
        // TextExpandAnimation(text)      // Expanding text animation
        // RevealingTextOnclick()         // Reveals completely new text on click
        // AnimateCounterScreen()        // Counter top-down animation
        // AnimatedCounterDownUP()        // Counter bottom-up animation
        // AnimatedCounterSimple()        // Counter blink animation
        // TextVisibilityAnimation(AnnotatedString("Click me!"))
        //TextWithPhotoBackground(Modifier.padding(top = 45.dp, start = 15.dp, end = 15.dp))
        // TypingAnimation(" Let's Dive Into Development!", Modifier.padding(top = 45.dp, start = 8.dp, end = 8.dp))
        // TextWithMotion()
        // TextListAnimation()
        // AnimatedText()
        // CustomToast()
        //SplitTextReveal()

        //************************** Sidebar Animations **************************
        //SideBarAnimation()

        //************************** List Animations **************************
        var items by remember {
            mutableStateOf(
                listOf(
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
            )
        }
        var draggedItem by remember { mutableStateOf<String?>(null) }

        //ScalingListItemAnimation()
        //DemonSlayerPhotoGallery()
        //SwipeableTextAnimation()       // Swiping text animation
        // PagerAnimation()               // Horizontal scrolling with animation
        // ResponsiveGrid()               // Adapts based on screen size
        // ImageResizeOnScrollExample()   // Image resizes dynamically on scroll
       /* DragDropList(items = items, onMove = { from, to ->
            items = items.toMutableList().apply {
                draggedItem = this[from] // Store the dragged item
                val movedItem = removeAt(from)
                add(to, movedItem)
            }
        }
        )*/
        //ListAnimationWithFloatingButton(menuItems = items, onMenuItemClick = { item -> } )
        // FlowLayoutAnimation()


        //************************** Image Animations **************************
        //RowPhotos()
        //StaggeredPhotos()

        //LuckyWheelScreen()
    }
}
