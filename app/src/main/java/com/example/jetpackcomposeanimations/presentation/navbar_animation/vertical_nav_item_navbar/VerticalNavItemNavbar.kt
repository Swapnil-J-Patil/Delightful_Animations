package com.example.jetpackcomposeanimations.presentation.navbar_animation.vertical_nav_item_navbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun VerticalNavItemNavbar() {
    val screen = listOf(
        Navbar.ForYou,
        Navbar.Search,
        Navbar.Chat,
        Navbar.Match,
        Navbar.Profile
    )

    val configuration = LocalConfiguration.current
    val isTab = configuration.screenWidthDp.dp > 600.dp

    var selectedTab by rememberSaveable(
        stateSaver = Saver(
            save = { it },
            restore = { it }
        )) { mutableStateOf(0) }
    var bottomBarVisibility by remember { mutableStateOf(true) }
    var isChatClicked by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier.fillMaxSize()
            .navigationBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {

        if (isTab) {

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                AnimatedVisibility(
                    visible = bottomBarVisibility,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 600,
                            easing = FastOutSlowInEasing
                        )
                    ),
                    exit = fadeOut(
                        animationSpec = tween(
                            durationMillis = 600,
                            easing = FastOutSlowInEasing
                        )
                    )
                ) {
                    BottomNavAnimation(
                        screens = screen,
                        selectedTab = selectedTab,  // Pass selectedTab
                        onClick = { tab ->
                            selectedTab = tab
                            when (tab) {
                                0 -> {}
                                1 -> {}
                                2 -> {

                                    isChatClicked = true
                                }

                                3 -> {}
                                4 -> {}

                            }
                        },
                        isChatClicked = isChatClicked
                    )
                }
            }

        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                AnimatedVisibility(
                    visible = bottomBarVisibility,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 600,
                            easing = FastOutSlowInEasing
                        )
                    ),
                    exit = fadeOut(
                        animationSpec = tween(
                            durationMillis = 600,
                            easing = FastOutSlowInEasing
                        )
                    )
                ) {
                    BottomNavAnimation(
                        screens = screen,
                        selectedTab = selectedTab,  // Pass selectedTab
                        onClick = { tab ->
                            selectedTab = tab
                            when (tab) {
                                0 -> {}
                                1 -> {}
                                2 -> {

                                    isChatClicked = true
                                }

                                3 -> {}
                                4 -> {}

                            }
                        },
                        isChatClicked = isChatClicked
                    )
                }

            }
        }
    }
}