package com.example.jetpackcomposeanimations.presentation.image_animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private val rotationDegrees = listOf(0f, -10f, 10f)

@Preview()
@Composable
fun StaggeredPhotos() {
    var run by remember { mutableStateOf(true) }
    Box(
        Modifier
            .size(400.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                run = !run
            },
        contentAlignment = Alignment.Center
    ) {
        if (run) {
            PhotoSquare(
                imageUrl = "https://i.pinimg.com/736x/8c/7b/66/8c7b669419f0511465b96c92eac51301.jpg",
                index = 0,
                modifier = Modifier
            )
            PhotoSquare(
                imageUrl = "https://wallpapers.com/images/featured/zenitsu-elxcv6kxzxsrpp69.jpg",
                index = 1,
                modifier = Modifier
                    .size(200.dp)
                    .offset(x = (-50).dp, y = 50.dp),
            )
            PhotoSquare(
                imageUrl = "https://motionbgs.com/media/6562/swordsman-inosuke-hashibira.jpg",
                index = 2,
                modifier = Modifier
                    .offset(x = 50.dp, y = 50.dp),
            )
        }
    }

}

@Composable
private fun PhotoSquare(
    imageUrl: String,
    index: Int,
    modifier: Modifier,
) {
    val scale = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }

    val animationSpec = remember {
        spring<Float>(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = 100f
        )
    }
    LaunchedEffect(key1 = index) {
        delay(100L * index)
        launch {
            scale.animateTo(
                targetValue = 1f, animationSpec =
                animationSpec
            )
        }
        launch {
            rotation.animateTo(
                targetValue = rotationDegrees[index],
                animationSpec = animationSpec
            )
        }
    }
    val shape = remember { RoundedCornerShape(24.dp) }
    Image(
        painter = rememberAsyncImagePainter(imageUrl, placeholder = ColorPainter(Color.Gray)),
        contentDescription = "Photo",
        modifier = modifier
            .size(200.dp)
            .graphicsLayer {
                rotationZ = rotation.value
                scaleX = scale.value
                scaleY = scale.value
                transformOrigin = TransformOrigin.Center
            }
            .shadow(elevation = 2.dp * (index + 1), shape = shape)
            .border(width = 6.dp, color = Color.White, shape = shape),
        contentScale = ContentScale.Crop
    )
}