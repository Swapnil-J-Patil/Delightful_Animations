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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jetpackcomposeanimations.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LuckyWheelScreen() {
    val items = listOf("$ 50", "$ 150", "$ 20", "$ 1000", "$ 0", "$ 500", "$ 5", "$ 10")
    var result by remember { mutableStateOf("") }
    var isChestAnimation by remember {
        mutableStateOf(false)
    }
    var isCoinAnimation by remember {
        mutableStateOf(false)
    }
    val chestComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.chest))
    val coinComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.coins))

    val chestProgress by animateLottieCompositionAsState(
        chestComposition,
        isPlaying = isChestAnimation // Infinite repeat mode
    )
    val coinProgress by animateLottieCompositionAsState(
        coinComposition,
        isPlaying = isCoinAnimation // Infinite repeat mode
    )
    val coroutineScope = rememberCoroutineScope()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

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
                composition = chestComposition,
                progress = { chestProgress },
                modifier = Modifier
                    .size(500.dp)
                    .align(Alignment.Center)
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
        }

        Text(
            result, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier
                .padding(top = 600.dp)
        )
    }
}

