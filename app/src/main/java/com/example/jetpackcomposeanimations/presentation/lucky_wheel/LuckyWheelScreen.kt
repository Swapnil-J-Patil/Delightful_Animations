package com.example.jetpackcomposeanimations.presentation.lucky_wheel

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jetpackcomposeanimations.R
import com.example.jetpackcomposeanimations.presentation.ui.theme.Poppins
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LuckyWheelScreen() {
    val items = listOf("$ 50", "$ 150", "$ 25", "$ 1000", "$ 1", "$ 500", "$ 5", "$ 10")
    var result by remember { mutableStateOf("") }
    var isChestAnimation by remember {
        mutableStateOf(false)
    }
    var isCoinAnimation by remember {
        mutableStateOf(false)
    }
    val chestComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.chest))
    val coinComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.coins))
    val lightComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.light))

    val chestProgress by animateLottieCompositionAsState(
        chestComposition,
        isPlaying = isChestAnimation // Infinite repeat mode
    )
    val coinProgress by animateLottieCompositionAsState(
        coinComposition,
        isPlaying = isCoinAnimation // Infinite repeat mode
    )
    val lightProgress by animateLottieCompositionAsState(
        lightComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isChestAnimation // Infinite repeat mode
    )
    val coroutineScope = rememberCoroutineScope()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF23af92), Color(0xFF121212)),
                    center = Offset.Unspecified, // or specify a center like Offset(0f, 0f)
                    radius = 1500f // Adjust based on screen size
                )
            )
    )
    {

        WheelStand(
            modifier = Modifier
                .padding(top = 450.dp)
                .fillMaxWidth()
                .height(150.dp)
        )


        LuckyWheel(
            items = items,
            onSpinEnd = { index ->
                coroutineScope.launch {
                    result = "You got: ${items[index]}"
                    isChestAnimation = true
                    delay(1300)
                    isCoinAnimation = true
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        AnimatedVisibility(
            visible = isChestAnimation,
            enter = scaleIn(
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
            ),
            exit = scaleOut()
        ) {

            LottieAnimation(
                composition = lightComposition,
                progress = { lightProgress },
                modifier = Modifier
                    .size(600.dp)
                    .align(Alignment.Center)
                    .offset(y=50.dp)

            )
            LottieAnimation(
                composition = chestComposition,
                progress = { chestProgress },
                modifier = Modifier
                    .size(500.dp)
                    .align(Alignment.Center)
                    .offset(y=50.dp)
            )


        }
        AnimatedVisibility(
            visible = isCoinAnimation,
            enter = scaleIn(
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
            ),
            exit = scaleOut()
        ) {
            LottieAnimation(
                composition = coinComposition,
                progress = { coinProgress },
                modifier = Modifier
                    .size(500.dp)
                    .align(Alignment.Center)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp),
                contentAlignment = Alignment.TopCenter
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.green_button),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(16.dp)
                        .offset(y = -110.dp),
                    contentDescription = "price",
                    contentScale = ContentScale.FillBounds
                )

                Text(
                    text = result,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Poppins,
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
    LuckyWheelScreen()
}

