package com.example.jetpackcomposeanimations.presentation.text_animation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.ui.theme.YellowAccent

@Composable
fun TextExpandAnimation(name: String, modifier: Modifier = Modifier) {
    var showMore by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .background(
                YellowAccent,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "Hello! $name!",
            modifier = modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .clickable {
                    showMore = !showMore
                },
            maxLines = if (!showMore) 1 else 5,

            )
    }
}