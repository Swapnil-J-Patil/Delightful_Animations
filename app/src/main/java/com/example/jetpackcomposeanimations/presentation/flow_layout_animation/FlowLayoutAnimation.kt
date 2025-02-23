package com.example.jetpackcomposeanimations.presentation.flow_layout_animation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ContextualFlowRowOverflow
import androidx.compose.foundation.layout.ContextualFlowRowOverflowScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowLayoutAnimation() {
    val animeList = listOf(
        "Attack on Titan", "Death Note", "Naruto", "One Piece", "Dragon Ball Z",
         "Demon Slayer", "Jujutsu Kaisen", "My Hero Academia", "Tokyo Ghoul",
        "Sword Art Online", "Hunter x Hunter", "Steins;Gate", "Neon Genesis Evangelion", "Cowboy Bebop",
        "Re:Zero", "Black Clover", "Bleach", "Fairy Tail", "The Rising of the Shield Hero",
        "Your Lie in April", "Clannad", "Code Geass", "No Game No Life", "Erased",
        "Vinland Saga", "Made in Abyss", "Parasyte", "Psycho-Pass", "Fate/Zero",
        "Gurren Lagann", "Overlord", "Hellsing Ultimate", "The Promised Neverland", "Blue Exorcist",
        "Dr. Stone", "Noragami", "The Seven Deadly Sins", "Mushoku Tensei", "Charlotte"
    )

    var maxLines by remember {
        mutableStateOf(2)
    }

    val moreOrCollapseIndicator = @Composable { scope: ContextualFlowRowOverflowScope ->
        val remainingItems = animeList.size - scope.shownItemCount
        ChipItem(if (remainingItems == 0) "Less" else "+$remainingItems", onClick = {
            if (remainingItems == 0) {
                maxLines = 2
            } else {
                maxLines += 5
            }
        })
    }

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF2196F3).copy(0.2f), Color.White.copy(alpha = 0.2f))
    )

    Column(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "My Favorite Anime: ",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        ContextualFlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.Top)
                .background(brush = gradientBrush)
                .padding(16.dp)
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            maxLines = maxLines,
            overflow = ContextualFlowRowOverflow.expandOrCollapseIndicator(
                minRowsToShowCollapse = 4,
                expandIndicator = moreOrCollapseIndicator,
                collapseIndicator = moreOrCollapseIndicator
            ),
            itemCount = animeList.size
        ) { index ->
            ChipItem(animeList[index])
        }
    }
}

@Composable
fun ChipItem(text: String, onClick: (() -> Unit)? = null) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Color(0xFF2196F3).copy(0.7f))
            .clickable { onClick?.invoke() }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.White)
    }
}