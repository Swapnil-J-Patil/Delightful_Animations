package com.example.jetpackcomposeanimations.presentation.navbar_animation.vertical_nav_item_navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Navbar(
    val title: String,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector
) {
    object ForYou: Navbar("For you", Icons.Filled.Explore, Icons.Outlined.Explore)
    object Search: Navbar("Search", Icons.Filled.Search, Icons.Outlined.Search)
    object Chat: Navbar("Chat", Icons.Filled.ChatBubble, Icons.Outlined.Chat)
    object Match: Navbar("Match", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder)
    object Profile: Navbar("Profile", Icons.Filled.Person, Icons.Outlined.Person)

}