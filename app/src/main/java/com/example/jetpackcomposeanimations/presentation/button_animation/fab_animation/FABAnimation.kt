package com.example.jetpackcomposeanimations.presentation.button_animation.fab_animation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ExpandableFAB() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        var expanded: Boolean by remember {
            mutableStateOf(false)
        }
        val offset by animateDpAsState(
            targetValue = if (expanded) 120.dp else 0.dp,
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = 100f,
            )
        )

        MetaContainer(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF00B0FF) // Light Blue Accent

        ) {

            FABButton(
                Modifier.offset(y = -offset),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = null,
                    tint = Color.White,
                )
            }

            FABButton(
                Modifier.offset(x = -offset),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                    tint = Color.White,
                )
            }

            FABButton(
                Modifier.offset(x = offset),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = Color.White,
                )
            }

            FABButton(
                Modifier,
                onClick = {
                    expanded = !expanded
                }
            ) {
                val rotation by animateFloatAsState(targetValue = if (expanded) 45f else 0f)
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.rotate(rotation),
                    tint = Color.White,
                )
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun BoxScope.FABButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    fabColor: Color = Color(0xFF2196F3), // 👈 add param
    content: @Composable BoxScope.() -> Unit
) {
    MetaEntity(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            )
            .align(Alignment.Center),
        blur = 50f,
        metaContent = {
            Box(
                Modifier
                    .background(fabColor, CircleShape) // 👈 BLUE
                    .fillMaxSize()
            )
        }
    ) {
        Box(
            Modifier.size(100.dp),
            content = content,
            contentAlignment = Alignment.Center,
        )
    }
}
