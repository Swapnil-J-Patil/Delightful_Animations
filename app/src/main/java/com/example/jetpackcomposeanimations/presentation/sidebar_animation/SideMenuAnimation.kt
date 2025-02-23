package com.example.jetpackcomposeanimations.presentation.sidebar_animation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun SideBarAnimation() {

    val colors = listOf(
        Color(0xFF0F4C75), // Deep Blue
        Color(0xFF3282B8), // Sky Blue
        Color(0xFFBBE1FA), // Light Aqua
        Color(0xFF1B262C), // Dark Teal
        Color(0xFFEF476F)  // Vivid Pink
    )


    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.Settings,
        Icons.Default.AccountCircle,
        Icons.Default.Notifications,
        Icons.Default.Favorite
    )

    var isMenuExpanded by remember { mutableStateOf(false) }
    var expandedIndex by remember { mutableIntStateOf(-1) }

    Box(modifier = Modifier.fillMaxSize()) {

        if (isMenuExpanded) {
            Box(modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable { isMenuExpanded = false })
        }

        Column(horizontalAlignment = Alignment.Start) {
            Icon(imageVector = Icons.Default.Menu,
                contentDescription = "Smooth Side Menu",
                modifier = Modifier
                    .clickable { isMenuExpanded = !isMenuExpanded }
                    .size(70.dp)
                    .padding(16.dp))

            colors.zip(icons).forEachIndexed { index, (color, icon) ->

                val rotation by animateFloatAsState(
                    targetValue = if (isMenuExpanded) 0f else -90f,
                    animationSpec = tween(durationMillis = 300, delayMillis = index * 100),
                    label = "rotation"
                )
                val width by animateDpAsState(
                    targetValue = if (expandedIndex == index) 100.dp else 70.dp,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                    label = "width"
                )
                val borderWidth by animateDpAsState(
                    targetValue = if (expandedIndex == index) 2.dp else (-1).dp,
                    animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy),
                    label = "borderWidth"
                )

                Box(modifier = Modifier
                    .width(width)
                    .height(70.dp)
                    .graphicsLayer { rotationY = rotation }
                    .background(color)
                    .border(
                        BorderStroke(
                            borderWidth,
                            Brush.linearGradient(listOf(Color(0xFF2196F3), Color(0xFFC7C4C2)))
                        )
                    )
                    .clickable {
                        expandedIndex = if (expandedIndex == index) -1 else index
                    }, contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Menu Sample Item",
                        tint = Color.White
                    )
                }
            }
        }
    }
}