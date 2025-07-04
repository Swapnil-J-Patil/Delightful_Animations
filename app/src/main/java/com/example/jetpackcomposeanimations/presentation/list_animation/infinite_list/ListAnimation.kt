package com.example.jetpackcomposeanimations.presentation.list_animation.infinite_list

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex

@Composable
fun PerspectiveListView(
    modifier: Modifier = Modifier,
    items: List<@Composable () -> Unit>,
    visualizedItems: Int = 5,
    itemExtent: Dp = 270.dp,
    initialIndex: Int = 0,
    onFrontItemClick: ((Int) -> Unit)? = null,
    onFrontItemChanged: ((Int) -> Unit)? = null
) {
    val state = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)
    val density = LocalDensity.current
    val itemExtentPx = with(density) { itemExtent.toPx() }

    val frontIndex = state.firstVisibleItemIndex + visualizedItems - 1

    LaunchedEffect(frontIndex) {
        onFrontItemChanged?.invoke(frontIndex)
    }

    Box(modifier = modifier) {
        LazyColumn(
            state = state,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 40.dp),
            verticalArrangement = Arrangement.Top // <-- remove spacing
        ) {
            items(items.size) { index ->
                val offset = (frontIndex - index)
                val absOffset = offset.coerceAtLeast(0).coerceAtMost(visualizedItems)

                val translateY = itemExtentPx * 0.1f * absOffset
                val scale = 1f - (0.03f * absOffset)

                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            translationY = translateY
                            scaleX = scale
                            scaleY = scale
                        }
                        .height(itemExtent)
                        .fillMaxWidth()
                        .zIndex(-absOffset.toFloat())
                        .padding(horizontal = 16.dp)
                        .then(
                            if (index == frontIndex) Modifier.clickable {
                                onFrontItemClick?.invoke(index)
                            } else Modifier
                        ),
                    contentAlignment = Alignment.TopCenter
                ) {
                    items[index]()
                }
            }
        }
    }
}



@Composable
fun SamplePerspectiveListViewScreen() {
    val items: List<@Composable () -> Unit> = List(20) { index ->
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .shadow(20.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Item $index",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
            }
        }
    }

    PerspectiveListView(
        items = items,
        visualizedItems = 6,
        itemExtent = 250.dp,
        initialIndex = 5,
        onFrontItemClick = { index ->
            println("Tapped on Item $index")
        },
        onFrontItemChanged = { index ->
            println("Front item changed to $index")
        }
    )
}



