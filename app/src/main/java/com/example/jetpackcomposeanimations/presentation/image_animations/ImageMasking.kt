package com.example.jetpackcomposeanimations.presentation.image_animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.R
import com.example.jetpackcomposeanimations.presentation.ui.theme.pink
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PlaneHealth(healthPercentage: Float) {
    val width = 200
    val height=400
    val imageBitmap = ImageBitmap.imageResource(R.drawable.can_image)
    val metalShineMask = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            Color.White.copy(alpha = 0.1f),
            Color.Transparent,
            Color.White.copy(alpha = 0.08f),
            Color.Transparent
        )
    )


    Box(
        modifier = Modifier
            .size(width = width.dp, height = height.dp)
            .drawWithContent {
                with(drawContext.canvas.nativeCanvas) {
                    val checkPoint = saveLayer(null, null)

                    drawContent()
                    drawImage(
                        image = imageBitmap,
                        dstSize = IntSize(
                            width = width.dp.toPx().toInt(),
                            height = height.dp.toPx().toInt()
                        ),
                        blendMode = BlendMode.DstIn
                    )

                    restoreToCount(checkPoint)
                }
            }
    ) {
        Box(
            modifier = Modifier
                .size(width = width.dp, height = height.dp)
                .background(pink)

        ) {
            Image(painter = painterResource(id = R.drawable.st_bg),
                contentDescription = "soda image bg",
                modifier = Modifier
                    /*.fillMaxWidth()
                    .height((planeSize * healthPercentage).dp)*/
                    .fillMaxSize()
                    .background(pink)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
            Image(painter = painterResource(id = R.drawable.strawberry),
                contentDescription = "soda image",
                modifier = Modifier
                    /*.fillMaxWidth()
                    .height((planeSize * healthPercentage).dp)*/
                    .padding(top=20.dp)
                    .clip(CircleShape)
                    .size(150.dp)
                    .align(Alignment.Center)
                    ,
                contentScale = ContentScale.Crop
            )
            /*Box(
                modifier = Modifier
                    .size(120.dp, 300.dp)
                    .drawWithContent {
                        drawContent()
                        drawRect(brush = metalShineMask)
                    }
            )*/

            Image(painter = painterResource(id = R.drawable.droplets),
                contentDescription = "droplets bg",
                modifier = Modifier
                    /*.fillMaxWidth()
                    .height((planeSize * healthPercentage).dp)*/
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
            /*Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((planeSize * healthPercentage).dp)
                    .background(Color.Green)
                    .align(Alignment.BottomCenter)
            )*/
        }
    }
}

@Composable
fun MaskedImage(
    imageResId: Int,
    maskResId: Int,
    modifier: Modifier = Modifier.size(210.dp, 350.dp)
) {
    val context = LocalContext.current

    val imageBitmap = remember(imageResId) {
        ImageBitmap.imageResource(context.resources, imageResId)
    }

    val maskBitmap = remember(maskResId) {
        ImageBitmap.imageResource(context.resources, maskResId)
    }


    // Trigger animation sequence
    /*LaunchedEffect(Unit) {
        val steps = listOf(0f, 0.33f, 0.66f)
        for (i in 0 until steps.lastIndex) {
            delay(3000)
            animatedStartPercent.animateTo(
                targetValue = steps[i + 1],
                animationSpec = tween(durationMillis = 800, easing = LinearEasing)
            )
        }
    }*/
    val steps = listOf(0f, 0.33f, 0.66f)
    var index by remember { mutableStateOf(0) }
    val animatedStartPercent = remember { Animatable(steps[index]) }

    // Accumulate drag distance across the gesture
    var totalDrag by remember { mutableStateOf(0f) }


    val scope= rememberCoroutineScope()
    Box(
        Modifier.fillMaxSize()
         //   .background(Color.Red.copy(alpha = 0.8f))
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        val threshold = 80f // minimum drag distance to trigger switch

                        val nextIndex = when {
                            totalDrag < -threshold && index < steps.lastIndex -> index + 1
                            totalDrag > threshold && index > 0 -> index - 1
                            else -> index
                        }

                        if (nextIndex != index) {
                            index = nextIndex
                            scope.launch {
                                animatedStartPercent.animateTo(
                                    targetValue = steps[index],
                                    animationSpec = tween(800, easing = LinearEasing)
                                )
                            }
                        }

                        // Reset drag
                        totalDrag = 0f
                    },
                    onHorizontalDrag = { change, dragAmount ->
                        totalDrag += dragAmount
                        change.consume()
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = modifier) {
            val paint = Paint()
            val canvasWidth = size.width
            val canvasHeight = size.height
            val maskWidth = maskBitmap.width
            val maskHeight = maskBitmap.height

            // Animate visible 33% window of mask
            val srcStartX = (maskWidth * animatedStartPercent.value).toInt()
            val srcEndX =
                (srcStartX + (maskWidth * 0.33f)).coerceAtMost(maskWidth.toFloat()).toInt()
            val srcWidth = srcEndX - srcStartX

            // Slightly shrink and center mask image
            val shrinkFactor = 0.98f
            val shrinkedWidth = (canvasWidth * shrinkFactor).toInt()
            val shrinkedHeight = (canvasHeight * shrinkFactor).toInt()
            val offsetX = ((canvasWidth - shrinkedWidth) / 2f).toInt()
            val offsetY = ((canvasHeight - shrinkedHeight) / 2f).toInt()

            drawIntoCanvas { canvas ->
                canvas.saveLayer(Rect(0f, 0f, canvasWidth, canvasHeight), paint)

                // Draw base image stretched to full canvas
                canvas.drawImageRect(
                    image = imageBitmap,
                    srcOffset = IntOffset.Zero,
                    srcSize = IntSize(imageBitmap.width, imageBitmap.height),
                    dstOffset = IntOffset.Zero,
                    dstSize = IntSize(canvasWidth.toInt(), canvasHeight.toInt()),
                    paint = paint
                )
                // Apply the mask using BlendMode.DstIn
                paint.blendMode = BlendMode.Modulate

                canvas.drawImageRect(
                    image = maskBitmap,
                    srcOffset = IntOffset(srcStartX, 0),
                    srcSize = IntSize(srcWidth, maskHeight),
                    dstOffset = IntOffset(offsetX, offsetY),
                    dstSize = IntSize(shrinkedWidth, shrinkedHeight),
                    paint = paint
                )


                canvas.restore()
            }
        }
    }
}



