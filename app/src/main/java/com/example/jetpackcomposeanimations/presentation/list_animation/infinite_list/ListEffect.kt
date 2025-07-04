package com.example.jetpackcomposeanimations.presentation.list_animation.infinite_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LazyColumnWithFirstItemSmaller() {
    val list = List(20) { "Item $it" }
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(list) { index, item ->
            // Dynamically determine if this item is the top one (fully or partially visible)
            val isTopItem = index == listState.firstVisibleItemIndex

            val height = if (isTopItem) 220.dp else 350.dp

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .padding(8.dp)
                    .background(
                        color = if (isTopItem) Color.Red else Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = item, color = Color.White, fontSize = 24.sp)
            }
        }
    }
}

