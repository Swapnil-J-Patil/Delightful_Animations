package com.example.jetpackcomposeanimations.presentation.navbar_animation.horizontal_nav_item

import androidx.compose.animation.AnimatedVisibility
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

@Composable
fun HorizontalNavItem(modifier: Modifier = Modifier) {
    val screen = listOf(
        Navbar.Home,
        Navbar.Market,
        Navbar.Saved,
        Navbar.Profile
    )

    val configuration = LocalConfiguration.current
    val isTab = configuration.screenWidthDp.dp > 600.dp

    var selectedTab by rememberSaveable(stateSaver = Saver(
        save = { it },
        restore = { it }
    )) { mutableStateOf(0) }
    var bottomBarVisibility by remember { mutableStateOf(true) }

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
                        isTab = isTab,
                        selectedTab = selectedTab,  // Pass selectedTab
                        onClick = { tab ->
                            selectedTab = tab
                            when (tab) {
                              /*  0 -> mainScreenViewModel.toggleTab("home")
                                1 -> mainScreenViewModel.toggleTab("market")
                                2 -> mainScreenViewModel.toggleTab("saved")
                                3 -> mainScreenViewModel.toggleTab("profile")*/
                            }
                        }
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
                        isTab = isTab,
                        selectedTab = selectedTab,  // Pass selectedTab
                        onClick = { tab ->
                            selectedTab = tab
                            when (tab) {
                               /* 0 -> mainScreenViewModel.toggleTab("home")
                                1 -> mainScreenViewModel.toggleTab("market")
                                2 -> mainScreenViewModel.toggleTab("saved")
                                3 -> mainScreenViewModel.toggleTab("profile")*/
                            }
                        }
                    )
                }
            }
        }
    }
}