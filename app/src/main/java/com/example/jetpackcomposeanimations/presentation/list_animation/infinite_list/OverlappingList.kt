package com.example.jetpackcomposeanimations.presentation.list_animation.infinite_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onFirstVisible
import androidx.compose.ui.layout.onVisibilityChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun ComposeOverlapping(
    modifier: Modifier = Modifier
) {

    val AccentColors = listOf(
        Color(0xFFE91E63), // Pink
        Color(0xFF9C27B0), // Purple
        Color(0xFF673AB7), // Deep Purple
        Color(0xFF3F51B5), // Indigo
        Color(0xFF2196F3), // Blue
        Color(0xFF03A9F4), // Light Blue
        Color(0xFF00BCD4), // Cyan
        Color(0xFF009688), // Teal
        Color(0xFF4CAF50), // Green
        Color(0xFF8BC34A), // Light Green
        Color(0xFFFFC107), // Amber
        Color(0xFFFF9800), // Orange
        Color(0xFFFF5722), // Deep Orange
        Color(0xFF795548), // Brown
        Color(0xFF607D8B)  // Blue Grey
    )

    LazyRow {
        item {
            Spacer(modifier = modifier.width(16.dp))
        }

        item {
            OverlappingRow(overlappingPercentage = 0.20f) {
                for (i in  0..10) {
                    Box(
                      modifier=Modifier.size(100.dp)
                          .background(AccentColors[i], shape = CircleShape)
                            ){  }
                }
            }
        }

        item {
            Spacer(modifier = modifier.width(16.dp))
        }
    }
}

@Composable
fun OverlappingRow(
    modifier: Modifier = Modifier,
    overlappingPercentage: Float,
    content: @Composable () -> Unit
) {

    val factor = (1 - overlappingPercentage)

    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map { it.measure(constraints) }
            val height = placeables.maxOf { it.height }
            val width = (placeables.subList(1, placeables.size).sumOf { it.width } * factor + (placeables.getOrNull(0)?.width ?: 0)).toInt()
            layout(width, height) {
                var x = 0
                for (placeable in placeables) {
                    placeable.placeRelative(x, 0, 0f)
                    x += (placeable.width * factor).toInt()
                }
            }
        }
    )

}

fun trapeziumShape(): Shape = GenericShape { size: Size, layoutDirection: LayoutDirection ->
    val width = size.width
    val height = size.height
    val topOffset = width * 0.2f

    moveTo(topOffset, 0f)
    lineTo(width - topOffset, 0f)
    lineTo(width, height)
    lineTo(0f, height)
    close()
}

@Composable
fun ComposeOverlappingColumn(modifier: Modifier = Modifier) {
    val AccentColors = listOf(
        Color(0xFFE91E63), Color(0xFF9C27B0), Color(0xFF673AB7),
        Color(0xFF3F51B5), Color(0xFF2196F3), Color(0xFF03A9F4),
        Color(0xFF00BCD4), Color(0xFF009688), Color(0xFF4CAF50),
        Color(0xFF8BC34A), Color(0xFFFFC107), Color(0xFFFF9800),
        Color(0xFFFF5722), Color(0xFF795548), Color(0xFF607D8B)
    )

    val listState = rememberLazyListState()
    val itemHeight = 300.dp
    val overlapFraction = 0.75f
    val density = LocalDensity.current

    val itemHeightPx = with(density) { itemHeight.toPx() }
    val spacingPx = itemHeightPx * (1 - overlapFraction)


    LazyColumn(
        modifier = Modifier.fillMaxSize()
           // .clip(trapeziumShape())
            ,
        state = listState,
        contentPadding = PaddingValues(vertical = 50.dp, horizontal = 20.dp)
    ) {
        itemsIndexed(AccentColors) { index, color ->
            val firstIndex = listState.firstVisibleItemIndex
            val scrollOffset = listState.firstVisibleItemScrollOffset.toFloat()

            // ✅ Correct: Shrink only earlier (above) items
            val rawDistance = (firstIndex - index) + (scrollOffset / spacingPx)
            val distance = rawDistance.coerceAtLeast(0f)

            val scaleY = (1f - distance * 0.1f).coerceIn(0.7f, 1f)
            val scaleX = (1f - distance * 0.3f).coerceIn(0.7f, 1f) // shrink width more aggressively

            Box(
                modifier = Modifier
                    .graphicsLayer {
                        translationY = -distance * spacingPx
                        this.scaleX = scaleX
                        this.scaleY = scaleY
                        alpha = scaleY
                    }

                    .fillMaxWidth()
                    .height(itemHeight)
                    .padding(vertical = 8.dp)
                    .background(color, shape = RoundedCornerShape(16.dp))
            )
        }
    }
}









@Composable
fun OverlappingColumn(
    modifier: Modifier = Modifier,
    overlappingPercentage: Float,
    content: @Composable () -> Unit
) {
    val factor = (1 - overlappingPercentage)

    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map { it.measure(constraints) }
            val width = placeables.maxOfOrNull { it.width } ?: 0
            val height = if (placeables.isNotEmpty()) {
                (placeables.first().height +
                        placeables.drop(1).sumOf { (it.height * factor).toInt() })
            } else 0

            layout(width, height) {
                var yOffset = 0
                for (placeable in placeables) {
                    placeable.placeRelative(0, yOffset)
                    yOffset += (placeable.height * factor).toInt()
                }
            }
        }
    )
}

