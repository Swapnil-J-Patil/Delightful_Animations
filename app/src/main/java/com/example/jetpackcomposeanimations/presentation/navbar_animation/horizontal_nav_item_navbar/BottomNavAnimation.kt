package com.example.jetpackcomposeanimations.presentation.navbar_animation.horizontal_nav_item_navbar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavAnimation(
    screens: List<Navbar>,
    isTab: Boolean,
    selectedTab: Int,  // Accept selectedTab as a parameter
    onClick: (Int) -> Unit
) {
    // Use selectedTab directly instead of maintaining local state
    if (!isTab) {
        Box(
            Modifier
                .shadow(5.dp)
                .background(color = MaterialTheme.colorScheme.surface)
                .height(64.dp)
                .fillMaxWidth()
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                for (screen in screens) {
                    val isSelected = screen == screens[selectedTab]
                    val animatedWeight by animateFloatAsState(
                        targetValue = if (isSelected) 1.5f else 1f
                    )
                    Box(
                        modifier = Modifier.weight(animatedWeight),
                        contentAlignment = Alignment.TopCenter,
                    ) {
                        val interactionSource = remember { MutableInteractionSource() }
                        BottomNavItem(
                            modifier = Modifier.clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                onClick(screens.indexOf(screen))
                            },
                            screen = screen,
                            isSelected = isSelected,
                            isTab = isTab
                        )
                    }
                }
            }
        }
    } else {
        Box(
            Modifier
                .shadow(5.dp)
                .background(color = MaterialTheme.colorScheme.surface)
                .fillMaxHeight()
                .width(140.dp)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.tertiary),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                for (screen in screens) {
                    val isSelected = screen == screens[selectedTab]
                    val animatedWeight by animateFloatAsState(
                        targetValue = if (isSelected) 1.5f else 1f
                    )

                    Box(
                        modifier = Modifier
                            .weight(animatedWeight, fill = false)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        val interactionSource = remember { MutableInteractionSource() }
                        BottomNavItem(
                            modifier = Modifier.clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) {
                                onClick(screens.indexOf(screen))
                            },
                            screen = screen,
                            isSelected = isSelected,
                            isTab = isTab
                        )
                    }
                }
            }
        }
    }
}