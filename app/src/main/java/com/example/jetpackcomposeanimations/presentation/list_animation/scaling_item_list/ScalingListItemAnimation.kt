package com.example.jetpackcomposeanimations.presentation.list_animation.scaling_item_list

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.list_animation.list_item_swipe.ContactUi

@Composable
fun ScalingListItemAnimation() {
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
// Precompute visible indices
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

                ContactCardItem(
                    name = contact.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 15.dp)
                        .graphicsLayer(scaleX = scale.value, scaleY = scale.value),
                    onCardClicked = {

                    }
                )
            }
        }


        // Error message or loading animation


    }
}
