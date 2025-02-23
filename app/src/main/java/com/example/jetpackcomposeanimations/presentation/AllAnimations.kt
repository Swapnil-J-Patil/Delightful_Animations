package com.example.jetpackcomposeanimations.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.animateRect
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeanimations.R

import com.example.jetpackcomposeanimations.presentation.ui.theme.YellowAccent
import com.example.jetpackcomposeanimations.presentation.util.randomSampleImageUrl
import kotlin.math.roundToInt
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.IntOffset  // Import for IntOffset
import com.example.jetpackcomposeanimations.presentation.color_animation.AnimateTextColor
import com.example.jetpackcomposeanimations.presentation.image_animations.RowPhotos
import com.example.jetpackcomposeanimations.presentation.image_animations.StaggeredPhotos
import com.example.jetpackcomposeanimations.presentation.list_animation.ImageResizeOnScrollExample
import com.example.jetpackcomposeanimations.presentation.list_animation.list_item_swipe.SwipeableTextAnimation
import kotlinx.coroutines.delay


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

            //************************** List Animations **************************
            // PagerAnimation()               // Horizontal scrolling with animation
            // ResponsiveGrid()               // Adapts based on screen size
            // ImageResizeOnScrollExample()   // Image resizes dynamically on scroll
            // DragDropList(items = ReorderItem, onMove = { fromIndex, toIndex -> ReorderItem.move(fromIndex, toIndex) })

            //************************** Image Animations **************************
            RowPhotos()
            //StaggeredPhotos()
    }
}
