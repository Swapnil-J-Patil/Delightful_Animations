package com.example.jetpackcomposeanimations.presentation.navbar_animation.horizontal_nav_item

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.ui.theme.green


@Composable
fun BottomNavItem(
    modifier: Modifier = Modifier,
    screen: Navbar,
    isSelected: Boolean,
    isTab: Boolean
) {
    if(!isTab) {
        Box(
            modifier = modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.tertiary),
            contentAlignment = Alignment.Center,
        ) {
            val animatedHeight by animateDpAsState(targetValue = if (isSelected) 36.dp else 26.dp)
            val animatedElevation by animateDpAsState(targetValue = if (isSelected) 15.dp else 0.dp)
            val animatedAlpha by animateFloatAsState(targetValue = if (isSelected) 1f else .5f)
            val animatedIconSize by animateDpAsState(
                targetValue = if (isSelected) 26.dp else 20.dp,
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow,
                    dampingRatio = Spring.DampingRatioMediumBouncy
                )
            )
            val color = if (isSelected) green else MaterialTheme.colorScheme.onSurface
            Row(
                modifier = Modifier
                    .height(animatedHeight)
                    .shadow(
                        elevation = animatedElevation,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = RoundedCornerShape(20.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                FlipIcon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxHeight()
                        .padding(start = 11.dp)
                        .alpha(animatedAlpha)
                        .size(animatedIconSize),
                    isActive = isSelected,
                    activeIcon = screen.activeIcon,
                    inactiveIcon = screen.inactiveIcon,
                    contentDescription = "Bottom Navigation Icon",
                    color = color,
                    rotationMax = 180f,
                    rotationMin = 0f
                )

                if (isSelected) {
                    Text(
                        text = screen.title,
                        modifier = Modifier.padding(start = 8.dp, end = 10.dp),
                        color = green,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 1,
                    )
                }
            }
        }
    }
    else
    {
        Box(
            modifier = modifier.fillMaxWidth()
                .height(64.dp)
                .background(MaterialTheme.colorScheme.tertiary),
            contentAlignment = Alignment.Center,
        ) {
            val animatedHeight by animateDpAsState(targetValue = if (isSelected) 36.dp else 26.dp)
            val animatedElevation by animateDpAsState(targetValue = if (isSelected) 15.dp else 0.dp)
            val animatedAlpha by animateFloatAsState(targetValue = if (isSelected) 1f else .5f)
            val animatedIconSize by animateDpAsState(
                targetValue = if (isSelected) 26.dp else 20.dp,
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow,
                    dampingRatio = Spring.DampingRatioMediumBouncy
                )
            )
            val color = if (isSelected) green else MaterialTheme.colorScheme.onSurface
            Row(
                modifier = Modifier
                    .height(animatedHeight)
                    .shadow(
                        elevation = animatedElevation,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = RoundedCornerShape(20.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                FlipIcon(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxHeight()
                        .padding(start = 11.dp)
                        .alpha(animatedAlpha)
                        .size(animatedIconSize),
                    isActive = isSelected,
                    activeIcon = screen.activeIcon,
                    inactiveIcon = screen.inactiveIcon,
                    contentDescription = "Bottom Navigation Icon",
                    color = color,
                    rotationMax = 180f,
                    rotationMin = 0f
                )

                if (isSelected) {
                    Text(
                        text = screen.title,
                        modifier = Modifier.padding(start = 8.dp, end = 10.dp),
                        color = green,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        maxLines = 1,
                    )
                }
            }
        }
    }

}