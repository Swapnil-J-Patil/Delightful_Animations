package com.example.jetpackcomposeanimations.presentation.navbar_animation.vertical_nav_item_navbar

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.ui.theme.green


@Composable
fun BottomNavItem(
    modifier: Modifier = Modifier,
    screen: Navbar,
    isSelected: Boolean,
    isChatClicked: Boolean
) {
    var showNotification by remember { mutableStateOf(isChatClicked) }

    LaunchedEffect(isChatClicked) {
        if(isChatClicked)
        {
            showNotification = true
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary),
        contentAlignment = Alignment.Center,
    ) {
        val animatedHeight by animateDpAsState(targetValue = if (isSelected) 50.dp else 26.dp)
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
        Column(
            modifier = Modifier
                .height(100.dp),
            /* .shadow(
                 elevation = animatedElevation,
                 shape = RoundedCornerShape(20.dp)
             )*/
            /*.background(
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(20.dp)
            )*/
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            FlipIcon(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    //.fillMaxHeight()
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

            Text(
                text = screen.title,
                color = color,
                modifier = Modifier.padding(top = 5.dp),
                style = MaterialTheme.typography.titleSmall,
                /*.copy(
                fontWeight = FontWeight.Bold
            )*/
                maxLines = 1,
            )

        }
        if(screen == Navbar.Chat && !showNotification) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(top = 5.dp, start = 10.dp)
                        .size(15.dp)
                        .background(
                            shape = CircleShape,
                            color = Color(0xFFFEBE24)
                        )
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.tertiary,
                            shape = CircleShape
                        )
                    //.padding(2.dp)
                ) {
                    Text(
                        "4",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Black
                    )
                }

            }
        }
    }


}