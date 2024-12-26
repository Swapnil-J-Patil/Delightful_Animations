package com.example.jetpackcomposeanimations.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
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
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.jetpackcomposeanimations.R
import com.example.jetpackcomposeanimations.presentation.screens.DetailScreen
import com.example.jetpackcomposeanimations.presentation.screens.ListScreen

import com.example.jetpackcomposeanimations.presentation.ui.theme.YellowAccent
import com.example.jetpackcomposeanimations.presentation.util.randomSampleImageUrl
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

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

    //***** Color change animations  *******
        //AnimateBackgroundColor()
       //AnimateTextColor()
       // InfinitelyRepeatable()

    //***** Shape animations with size or padding  *******
        //HideAndShowDiagonally()
        //HideSwiftly()
        //AnimatePadding()
        //AnimateSizeChange()
       // AnimateSizeChange_Specs()
        //TransitionExampleConcurrent()


   //***** Button Animation  *******
        //AnimateElevation()


    //***** Shape animations with translation  *******
        //AnimateOffset()
        //AnimateOffset()
        //AnimationLayout()       //toggled boxes
        //AnimateAlignment()        //Change the alignment from left to right

        //SmoothAnimateText()               //For animating infinitely
        //ControlledSmoothAnimateText()     //For animating once

        //ConcurrentAnimatable()
        //SequentialAnimations()              //More than one animation
        //ConcurrentAnimations()


   //***** Navigating between screens with animation  *******
        //To open details screen on click of the list item with animation
        //AnimateBetweenComposableDestinations()
        //AnimatedContentExampleSwitch()        //Loading - Loaded - Error

   //***** Text animations  *******
        val text="Compose provides convenient APIs that allow you to solve for many common animation use cases. This section demonstrates how you can animate common properties of a composable."
        //TextExpandAnimation(text)   //For existing or given text
        //RevealingTextOnclick()     //For completely new text

        //AnimateCounterScreen()        //Counter top - down animation
        //AnimatedCounterDownUP()       //Counter bottom - up animation
        //AnimatedCounterSimple()       //Counter blink animation
        //AnimatedVisibilitySample()
        //TextVisibilityAnimation(AnnotatedString("Click me!"))

        //AnimatedVisibilityMutable()     //To track the visibility
        //AnimatedVisibilityAnimateEnterExitChildren()

    }
}

@Composable
fun TextExpandAnimation(name: String, modifier: Modifier = Modifier) {
    var showMore by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .background(
                YellowAccent,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "Hello! $name!",
            modifier = modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .clickable {
                    showMore = !showMore
                },
            maxLines = if (!showMore) 1 else 5,

            )
    }
}

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

@Preview
@Composable
fun HideSwiftly() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // [START android_compose_animation_cookbook_visibility_alpha]
        var visible by remember {
            mutableStateOf(true)
        }
        val animatedAlpha by animateFloatAsState(
            targetValue = if (visible) 1.0f else 0f,
            label = "alpha"
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    alpha = animatedAlpha
                }
                .clip(RoundedCornerShape(8.dp))
                .background(colorGreen)
                .align(Alignment.TopCenter)
        ) {
        }
        // [END android_compose_animation_cookbook_visibility_alpha]
        Button(modifier = Modifier.align(Alignment.BottomCenter), onClick = {
            visible = !visible
        }) {
            Text("Show/Hide")
        }
    }
}

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

@Preview
@Composable
fun AnimatePadding() {
    Box {
        // [START android_compose_animation_padding]
        var toggled by remember {
            mutableStateOf(false)
        }
        val animatedPadding by animateDpAsState(
            if (toggled) {
                0.dp
            } else {
                20.dp
            },
            label = "padding"
        )
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxSize()
                .padding(animatedPadding)
                .background(Color(0xff53D9A1))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    toggled = !toggled
                }
        )
        // [END android_compose_animation_padding]
    }
}

@Preview
@Composable
fun AnimateSizeChange() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var expanded by remember { mutableStateOf(false) }

        // Custom animated height with a spring effect
        val animatedHeight by animateDpAsState(
            targetValue = if (expanded) 400.dp else 200.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy, //can set it to HighBouncy
                stiffness = Spring.StiffnessLow     //Very low for scroll like effect
            ),
            label = "Height Animation"
        )

        Box(
            modifier = Modifier
                .background(colorBlue)
                .height(animatedHeight)
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    expanded = !expanded
                }
        )
    }
}

@Preview
@Composable
fun AnimateSizeChange_Specs() {
    Row(modifier = Modifier.fillMaxSize()) {
        var expanded by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        ) {
            Text("No spec set")
            Box(
                modifier = Modifier
                    .background(colorBlue)
                    .animateContentSize()
                    .height(if (expanded) 300.dp else 200.dp)
                    .fillMaxSize()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        expanded = !expanded
                    }

            ) {
            }
        }
        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        ) {
            Text("Custom spec")
            // [START android_compose_animation_size_change_spec]
            Box(
                modifier = Modifier
                    .background(colorBlue)
                    .animateContentSize(
                        spring(
                            stiffness = Spring.StiffnessLow,
                            dampingRatio = Spring.DampingRatioHighBouncy
                        )
                    )
                    .height(if (expanded) 300.dp else 200.dp)
                    .fillMaxSize()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        expanded = !expanded
                    }

            ) {
            }
            // [END android_compose_animation_size_change_spec]
        }
    }
}

@Preview
@Composable
fun AnimateOffset() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // [START android_compose_animation_offset_change]
        var moved by remember { mutableStateOf(false) }
        val pxToMove = with(LocalDensity.current) {
            100.dp.toPx().roundToInt()
        }
        val offset by animateIntOffsetAsState(
            targetValue = if (moved) {
                IntOffset(pxToMove, pxToMove)
            } else {
                IntOffset.Zero
            },
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow     //Very low for scroll like effect
            ),
            label = "offset"
        )

        Box(
            modifier = Modifier
                .offset {
                    offset
                }
                .background(colorBlue)
                .size(100.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    moved = !moved
                }
        )
        // [END android_compose_animation_offset_change]
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
fun AnimateBetweenComposableDestinations() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val navController = rememberNavController()
        SharedTransitionLayout {
            NavHost(
                navController = navController,
                startDestination = "list"
            ) {
                composable("list") {
                    ListScreen(
                        onItemClick = { resId, text ->
                            navController.navigate("detail/$resId/$text")
                        },
                        animatedVisibilityScope = this
                    )
                }
                composable(
                    route = "detail/{resId}/{text}",
                    arguments = listOf(
                        navArgument("resId") {
                            type = NavType.IntType
                        },
                        navArgument("text") {
                            type = NavType.StringType
                        },
                    )
                ) {
                    val resId = it.arguments?.getInt("resId") ?: 0
                    val text = it.arguments?.getString("text") ?: ""
                    DetailScreen(
                        resId = resId,
                        text = text,
                        animatedVisibilityScope = this
                    )
                }
            }
        }
    }
    // [END android_compose_animate_destinations]
}

@Preview
@Composable
fun SmoothAnimateText() {
    // [START android_compose_animation_cookbook_text]
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "scale"
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Hello",
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin.Center
                }
                .align(Alignment.Center),
            // Text composable does not take TextMotion as a parameter.
            // Provide it via style argument but make sure that we are copying from current theme
            style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated)
        )
    }
    // [END android_compose_animation_cookbook_text]
}
@Composable
fun ControlledSmoothAnimateText() {
    var isAnimating by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isAnimating) 7f else 1f,
        animationSpec = tween(durationMillis = 1000),
        finishedListener = {
            if (!isAnimating) isAnimating = true // Trigger reverse animation manually
        }
    )
    LaunchedEffect(Unit) {
        isAnimating=!isAnimating
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Hello",
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin.Center
                }
                .align(Alignment.Center),
            style = LocalTextStyle.current.copy()
        )
    }
}

@Preview
@Composable
fun AnimateTextColor() {
    Box(modifier = Modifier.fillMaxSize()) {
        // [START android_compose_animation_cookbook_text_color]
        val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
        val animatedColor by infiniteTransition.animateColor(
            initialValue = Color(0xFF60DDAD),
            targetValue = Color(0xFF4285F4),
            animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
            label = "color"
        )

        BasicText(
            text = "Hello Compose",
            color = {
                animatedColor
            },
            // [START_EXCLUDE]
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
            // [END_EXCLUDE]
        )
        // [END android_compose_animation_cookbook_text_color]
    }
}

@Preview
@Composable
fun InfinitelyRepeatable() {
    // [START android_compose_animation_infinitely_repeating]
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Green,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "color"
    )
    Column(
        modifier = Modifier
            .drawBehind {
                drawRect(color)
            }
            .fillMaxSize()
    ) {
        // your composable here
    }
    // [END android_compose_animation_infinitely_repeating]
}

@Preview
@Composable
fun ConcurrentAnimatable() {
    // [START android_compose_animation_on_launch]
    val alphaAnimation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        alphaAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000) // Add duration for a smoother effect
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                alpha = alphaAnimation.value
            }
            .background(Color.Blue) // Add a background color to make it visible
    )
    // [END android_compose_animation_on_launch]
}


@Preview
@Composable
fun SequentialAnimations() {
    // [START android_compose_animation_sequential]
    val alphaAnimation = remember { Animatable(0f) }
    val yAnimation = remember { Animatable(0f) }

    LaunchedEffect("animationKey") {
        alphaAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 500) // Smooth fade-in
        )
        yAnimation.animateTo(
            targetValue = 100f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy, // Bouncy motion
                stiffness = Spring.StiffnessLow                // Smooth and relaxed
            )
        )
        yAnimation.animateTo(
            targetValue = 500f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,  // Even bouncier motion
                stiffness = Spring.StiffnessLow            // Slightly stiffer
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                alpha = alphaAnimation.value
                translationY = yAnimation.value
            }
            .background(Color.Red) // Add a background color to make it visible
    )
    // [END android_compose_animation_sequential]
}


@Preview
@Composable
fun ConcurrentAnimations() {
    // [START android_compose_animation_concurrent]
    val alphaAnimation = remember { Animatable(0f) }
    val yAnimation = remember { Animatable(0f) }

    LaunchedEffect("animationKey") {
        // Launch concurrent animations
        launch {
            alphaAnimation.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 2000) // Smooth fade-in
            )
        }
        launch {
            yAnimation.animateTo(
                targetValue = 200f, // Visible translation range
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy, // Bouncy vertical movement
                    stiffness = Spring.StiffnessLow                // Smooth and relaxed
                )
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray) // Background to contrast animation
    ) {
        Box(
            modifier = Modifier
                .size(100.dp) // Constrained size for visibility
                .graphicsLayer {
                    alpha = alphaAnimation.value
                    translationY = yAnimation.value
                }
                .background(Color.Blue) // Add a visible color to the animated box
                .align(Alignment.TopCenter) // Align for better visual observation
        )
    }
    // [END android_compose_animation_concurrent]
}



enum class BoxState {
    Collapsed,
    Expanded
}

@Preview
@Composable
fun TransitionExampleConcurrent() {
    // [START android_compose_concurrent_transition]
    var currentState by remember { mutableStateOf(BoxState.Collapsed) }
    val transition = updateTransition(targetState = currentState, label = "transition")

    val rect by transition.animateRect(label = "rect") { state ->
        when (state) {
            BoxState.Collapsed -> Rect(0f, 0f, 200f, 200f) // Smaller rect
            BoxState.Expanded -> Rect(300f, 300f, 600f, 600f) // Larger rect
        }
    }
    val borderWidth by transition.animateDp(label = "borderWidth") { state ->
        when (state) {
            BoxState.Collapsed -> 4.dp // Thick border for visibility
            BoxState.Expanded -> 0.dp // No border
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    currentState = if (currentState == BoxState.Collapsed) {
                        BoxState.Expanded
                    } else {
                        BoxState.Collapsed
                    }
                }
        ) {
            // Draw a rectangle using the animated rect values
            drawRect(
                color = Color.Blue,
                topLeft = Offset(rect.left, rect.top),
                size = Size(rect.width - rect.left, rect.height - rect.top), // Ensure proper size
                style = if (borderWidth > 0.dp) {
                    Stroke(width = borderWidth.toPx()) // Animated border
                } else {
                    Fill // Filled rectangle when borderWidth = 0.dp
                }
            )
        }
    }
    // [END android_compose_concurrent_transition]
}


@Preview
@Composable
fun AnimateElevation() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // [START android_compose_animation_cookbook_elevation]
        val mutableInteractionSource = remember {
            MutableInteractionSource()
        }
        val pressed = mutableInteractionSource.collectIsPressedAsState()
        val elevation = animateDpAsState(
            targetValue = if (pressed.value) {
                32.dp
            } else {
                8.dp
            },
            label = "elevation"
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.Center)
                .graphicsLayer {
                    this.shadowElevation = elevation.value.toPx()
                }
                .clickable(interactionSource = mutableInteractionSource, indication = null) {
                }
                .background(colorGreen)
        ) {
        }
        // [END android_compose_animation_cookbook_elevation]
    }
}

@Preview
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

@Composable
private fun ErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // [START_EXCLUDE]
        Text("Error", fontSize = 18.sp)
        // [END_EXCLUDE]
    }
}

@Composable
private fun LoadedScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // [START_EXCLUDE]
        Text("Loaded", fontSize = 18.sp)
        Image(
            painterResource(id = R.drawable.ic_launcher_foreground),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                ),
            contentDescription = "dog",
            contentScale = ContentScale.Crop
        )
        // [END_EXCLUDE]
    }
}

@Composable
private fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        CircularProgressIndicator(modifier = Modifier.size(48.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Text("Loading", fontSize = 18.sp)
    }
}

@Preview
@Composable
fun AnimationLayout() {
    // [START android_compose_animation_layout_offset]
    var toggled by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .clickable(indication = null, interactionSource = interactionSource) {
                toggled = !toggled
            }
    ) {
        val offsetTarget = if (toggled) {
            IntOffset(150, 150)
        } else {
            IntOffset.Zero
        }
        val offset = animateIntOffsetAsState(
            targetValue = offsetTarget, label = "offset"
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(colorBlue)
        )
        Box(
            modifier = Modifier
                .layout { measurable, constraints ->
                    val offsetValue = if (isLookingAhead) offsetTarget else offset.value
                    val placeable = measurable.measure(constraints)
                    layout(placeable.width + offsetValue.x, placeable.height + offsetValue.y) {
                        placeable.placeRelative(offsetValue)
                    }
                }
                .size(100.dp)
                .background(colorGreen)
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(colorBlue)
        )
    }
    // [END android_compose_animation_layout_offset]
}

@Preview
@Composable
fun AnimateAlignment() {
    // [START android_compose_animate_item_placement]
    var toggled by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .clickable(indication = null, interactionSource = interactionSource) {
                toggled = !toggled
            },
        horizontalAlignment = if (toggled) Alignment.End else Alignment.Start, // Change alignment on toggle
        verticalArrangement = Arrangement.SpaceBetween // Spacing between items
    ) {
        // Animating offset or alignment
        val offsetX by animateDpAsState(
            targetValue = if (toggled) 150.dp else 0.dp,
            animationSpec = tween(durationMillis = 2000, easing = LinearOutSlowInEasing)
        )

        // Boxes with animated offset
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Blue)
                .offset(x = offsetX)
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
                .offset(x = offsetX)
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Blue)
                .offset(x = offsetX)
        )
    }
    // [END android_compose_animate_item_placement]
}


@Composable
fun AnimateCounterScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var count by remember {
            mutableStateOf(0)
        }
        AnimatedCounter(
            count = count,
            style = MaterialTheme.typography.headlineLarge
        )
        Button(onClick = { count++ }) {
            Text(text = "Increment")
        }
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCounter(
    count: Int,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium
) {
    var oldCount by remember {
        mutableStateOf(count)
    }
    SideEffect {
        oldCount = count
    }
    Row(modifier = modifier) {
        val countString = count.toString()
        val oldCountString = oldCount.toString()
        for(i in countString.indices) {
            val oldChar = oldCountString.getOrNull(i)
            val newChar = countString[i]
            val char = if(oldChar == newChar) {
                oldCountString[i]
            } else {
                countString[i]
            }
            AnimatedContent(
                targetState = char,
                transitionSpec = {
                    slideInVertically { it } with slideOutVertically { -it }
                }
            ) { char ->
                Text(
                    text = char.toString(),
                    style = style,
                    softWrap = false
                )
            }
        }
    }
}

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

@Composable
fun TextVisibilityAnimation(
    message: AnnotatedString
) {

    var showDetails by remember { mutableStateOf(false) }

    Column {
        ClickableText(
            text = message,
            onClick = {
                showDetails = !showDetails
            }
        )
        AnimatedVisibility(showDetails) {
            Text("showed hidden message")
        }
    }
}

@Preview
@Composable
fun AnimatedVisibilityMutable() {
    // [START android_compose_animations_animated_visibility_mutable]
    // Create a MutableTransitionState<Boolean> for the AnimatedVisibility.
    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }
    Column {
        AnimatedVisibility(visibleState = state) {
            Text(text = "Hello, world!")
        }

        // Use the MutableTransitionState to know the current animation state
        // of the AnimatedVisibility.
        Text(
            text = when {
                state.isIdle && state.currentState -> "Visible"
                !state.isIdle && state.currentState -> "Disappearing"
                state.isIdle && !state.currentState -> "Invisible"
                else -> "Appearing"
            }
        )
    }
    // [END android_compose_animations_animated_visibility_mutable]
}


@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun AnimatedCounterSimple() {
    // [START android_compose_animations_animated_content_simple]
    Row {
        var count by remember { mutableStateOf(0) }
        Button(onClick = { count++ }) {
            Text("Add")
        }
        AnimatedContent(targetState = count) { targetCount ->
            // Make sure to use `targetCount`, not `count`.
            Text(text = "Count: $targetCount")
        }
    }
    // [END android_compose_animations_animated_content_simple]
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCounterDownUP() {
    // Define a state for count and initialize it to 0
    var count by remember { mutableStateOf(0) }

    // Button to increment the count value and trigger the animation
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Button(onClick = { count++ }) {
            Text("Increment Count")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // AnimatedContent with a transition spec
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                // Check if the target number is greater than the initial number
                if (targetState > initialState) {
                    // Slide up and fade in
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                } else {
                    // Slide down and fade in
                    slideInVertically { height -> -height } + fadeIn() with
                            slideOutVertically { height -> height } + fadeOut()
                }.using(
                    // Disable clipping to allow the transition to happen outside bounds
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Text(text = "$targetCount", style = MaterialTheme.typography.headlineLarge)
        }
    }
}


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
enum class UiState {
    Loading,
    Loaded,
    Error
}

val colorGreen = Color(0xFF53D9A1)
val colorBlue = Color(0xFF4FC3F7)





private val randomSizedPhotos = listOf(
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 900, height = 1600),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 300, height = 400),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 900, height = 1600),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 300, height = 400),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 900, height = 1600),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 300, height = 400),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 300, height = 400),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 900, height = 1600),
    randomSampleImageUrl(width = 500, height = 500),
    randomSampleImageUrl(width = 300, height = 400),
    randomSampleImageUrl(width = 1600, height = 900),
    randomSampleImageUrl(width = 500, height = 500),
)