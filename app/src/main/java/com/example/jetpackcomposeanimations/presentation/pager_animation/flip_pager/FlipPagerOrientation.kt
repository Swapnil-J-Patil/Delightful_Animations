package com.example.jetpackcomposeanimations.presentation.pager_animation.flip_pager

sealed class FlipPagerOrientation {
    data object Vertical : FlipPagerOrientation()
    data object Horizontal : FlipPagerOrientation()
}