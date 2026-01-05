package com.example.jetpackcomposeanimations.presentation.navbar_animation.horizontal_nav_item_navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.InsertChart
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.InsertChart
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Navbar(
    val title: String,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector
) {
    object Home: Navbar("Home", Icons.Filled.Home, Icons.Outlined.Home)
    object Market: Navbar("Library", Icons.Filled.InsertChart, Icons.Outlined.InsertChart)
    object Saved: Navbar("Saved", Icons.Filled.Bookmark, Icons.Outlined.Bookmark)
    object Profile: Navbar("Profile", Icons.Filled.Person, Icons.Outlined.Person)
}