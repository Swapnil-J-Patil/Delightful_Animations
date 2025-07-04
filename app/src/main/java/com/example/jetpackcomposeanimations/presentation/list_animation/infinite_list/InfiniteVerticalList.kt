package com.example.jetpackcomposeanimations.presentation.list_animation.infinite_list

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeanimations.presentation.list_animation.list_item_swipe.ContactUi
import com.example.jetpackcomposeanimations.presentation.list_animation.scaling_item_list.ContactCardItem
import com.example.jetpackcomposeanimations.presentation.ui.theme.green
import kotlinx.coroutines.delay

@Composable
fun InfiniteScroll(modifier: Modifier = Modifier) {
    val screenWidth =
        LocalDensity.current.run { androidx.compose.ui.platform.LocalContext.current.resources.displayMetrics.widthPixels / density }

    val listState = rememberLazyGridState()
    val halfScreenWidth = if (screenWidth > 600) screenWidth / 3 else screenWidth
    val contacts = remember {
        mutableStateListOf(
            *(1..100).map {
                ContactUi(
                    id = it,
                    name = "Contact $it",
                    isOptionsRevealed = false
                )
            }.toTypedArray()
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {

        LazyVerticalGrid(
            columns = GridCells.Adaptive(halfScreenWidth.dp),
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 5.dp)

        ) {
            itemsIndexed(
                contacts,
                key = { _, contact -> contact.id }) { index, contact ->

                val baseHeight = 220.dp
                val baseWidth = 320.dp

                val reductionPerLevel = 50.dp
                val maxStackCount = 5

                val limitedIndex = index.coerceAtMost(maxStackCount - 1)
                val cardHeight = baseHeight - (reductionPerLevel * (maxStackCount - 1 - limitedIndex))
                val cardWidth = baseWidth - (reductionPerLevel * (maxStackCount - 1 - limitedIndex))

                // Check visibility
                val isVisible = remember {
                    derivedStateOf {
                        val visibleItems = listState.layoutInfo.visibleItemsInfo
                        visibleItems.any { it.index == index }
                    }
                }
                val scale = remember { Animatable(0f) }

                // val hasAnimated = remember { mutableStateOf(false) }

                LaunchedEffect(isVisible.value) {
                    if (isVisible.value) {
                        scale.animateTo(
                            targetValue = 1f,
                            animationSpec = tween(
                                durationMillis = 300, // Adjust as needed for smoothness
                                easing = FastOutSlowInEasing
                            )
                        )
                    } else {
                        scale.snapTo(0f) // Reset scale when not visible
                    }
                }

                SwipeUpShrinkCard(
                    label = contact.name,
                    modifier = Modifier
                        .width(cardWidth)
                        .height(cardHeight)
                        .padding(vertical = 8.dp, horizontal = 15.dp)
                        .graphicsLayer(scaleX = scale.value, scaleY = scale.value,)
                        .offset(y = (-50 * index).dp) // <- Add this line for vertical overlap
                    ,
                    /*onCardClicked = {

                    }*/
                )
            }
        }


        // Error message or loading animation


    }
}

@Composable
fun SwipeUpShrinkCard(
    modifier: Modifier,
    label: String = "Swipe Me Up"
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(label, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}


