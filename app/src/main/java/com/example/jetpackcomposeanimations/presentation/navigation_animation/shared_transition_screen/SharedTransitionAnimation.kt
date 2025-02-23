package com.example.jetpackcomposeanimations.presentation.navigation_animation.shared_transition_screen

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
fun AnimateBetweenComposableDestinations() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val navController = rememberNavController()
        SharedTransitionLayout {
            NavHost(
                navController = navController,
                startDestination = "list"
            ) {
                composable("list") {
                    ListScreen(
                        onItemClick = { resId, text ->
                            navController.navigate("detail/$resId/$text")
                        },
                        animatedVisibilityScope = this
                    )
                }
                composable(
                    route = "detail/{resId}/{text}",
                    arguments = listOf(
                        navArgument("resId") {
                            type = NavType.IntType
                        },
                        navArgument("text") {
                            type = NavType.StringType
                        },
                    )
                ) {
                    val resId = it.arguments?.getInt("resId") ?: 0
                    val text = it.arguments?.getString("text") ?: ""
                    DetailScreen(
                        resId = resId,
                        text = text,
                        animatedVisibilityScope = this
                    )
                }
            }
        }
    }
    // [END android_compose_animate_destinations]
}
