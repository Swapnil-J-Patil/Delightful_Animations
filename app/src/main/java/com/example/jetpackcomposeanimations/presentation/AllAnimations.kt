package com.example.jetpackcomposeanimations.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.R
import com.example.jetpackcomposeanimations.presentation.card_animations.NeonShimmerPipeOnPath
import com.example.jetpackcomposeanimations.presentation.card_animations.PipeMovingOnRoundedRectBorder
import com.example.jetpackcomposeanimations.presentation.image_animations.MaskedImage
import com.example.jetpackcomposeanimations.presentation.image_animations.PlaneHealth
import com.example.jetpackcomposeanimations.presentation.image_animations.pager_animation.CircleRevealPager
import com.example.jetpackcomposeanimations.presentation.image_animations.pager_animation.ColorPager


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
        //PipeMovingOnRoundedRectBorder()


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
        CircleRevealPager()
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
        //ColorPager()
        //PlaneHealth(20f)
        /*MaskedImage(
            imageResId = R.drawable.can_image, // background label image
            maskResId = R.drawable.img_1     // can-shaped alpha mask
        )*/

        //************************** Other Animations **************************
        //LuckyWheelScreen()
    }
}
