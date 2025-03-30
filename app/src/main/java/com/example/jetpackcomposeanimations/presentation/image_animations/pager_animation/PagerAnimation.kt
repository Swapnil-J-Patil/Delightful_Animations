package com.example.jetpackcomposeanimations.presentation.image_animations.pager_animation

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.jetpackcomposeanimations.R
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@SuppressLint("UnusedBoxWithConstraintsScope", "UnrememberedMutableInteractionSource")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerAnimation(modifier: Modifier = Modifier) {
    // List of images to display in the pager
    val imageResources = listOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img1,
        R.drawable.img2,
    )

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        val itemSpacing = 16.dp
        val pagerState = rememberPagerState(pageCount = { imageResources.size })

        val scope = rememberCoroutineScope()

        HorizontalPager(
            modifier = modifier,
            state = pagerState,
            flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState,
                pagerSnapDistance = PagerSnapDistance.atMost(0)
            ),
            contentPadding = PaddingValues(horizontal = 32.dp),
            pageSpacing = itemSpacing
        ) { page ->
            Image(
                painter = painterResource(id = imageResources[page]),
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        enabled = true,
                    ) {
                        scope.launch {
                            pagerState.animateScrollToPage(page)
                        }
                    }
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleY = lerp(
                            start = 0.75f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            )
        }
    }
}
