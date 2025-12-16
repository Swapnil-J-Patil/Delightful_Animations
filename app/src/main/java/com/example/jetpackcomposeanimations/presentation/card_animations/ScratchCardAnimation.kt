package com.example.jetpackcomposeanimations.presentation.card_animations

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.R
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun ScratchCard(
    modifier: Modifier = Modifier,
    overlayImage: ImageBitmap,
    baseImage: Painter,
    scratchingThresholdPercentage : Float = 0.8f,
    scratchLineWidth : Dp = 32.dp,
    scratchLineCap : StrokeCap = StrokeCap.Round,
    isScratched: Boolean = false,
    onScratchComplete: () -> Unit = {},
    shape: Shape = RoundedCornerShape(12.dp)
) {
    val scratchLines = remember {
        mutableStateListOf<Line>()
    }
    val totalScratchedArea = remember {
        mutableFloatStateOf(0f)
    }

    Box(
        modifier = modifier
            .clip(shape)
    ){

        if (isScratched) {
            Canvas(
                modifier = Modifier
                    .size(250.dp)
                    .graphicsLayer {
                        alpha = 0.9f
                    }
            ) {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFFFD700), // Gold
                            Color(0xFFFFB300).copy(alpha = 0.6f),
                            Color.Transparent
                        ),
                        radius = size.minDimension * 0.8f
                    ),
                    radius = size.minDimension * 0.8f,
                    center = center
                )
            }
        }

        Image(
            painter = baseImage,
            contentDescription = "Base image",
            modifier = Modifier
                .fillMaxSize()
                .clip(shape),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen //Without it, all the pixels (in this example baseImage) drawn underneath scratchLines will be cleared with scratchLines
                    //now baseImage is considered as a separate layer and masking can be applied
                }
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(true) {
                        detectTapGestures(
                            onTap = { position ->
                                val line = Line(
                                    start = position,
                                    end = position,
                                    strokeWidth = scratchLineWidth.toPx()
                                )
                                //accumulate the total scratched area, including overlapping lines
                                totalScratchedArea.floatValue += line.calculateArea()
                                scratchLines.add(line)
                            }
                        )
                    }
                    .pointerInput(true) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()

                            val line = Line(
                                start = change.position - dragAmount,
                                end = change.position,
                                strokeWidth = scratchLineWidth.toPx()
                            )
                            //accumulate the total scratched area, including overlapping lines
                            totalScratchedArea.floatValue += line.calculateArea()

                            scratchLines.add(line)
                        }
                    }
            ) {
                val imageSize = IntSize(width = size.width.toInt(), height = size.height.toInt())
                val maxCanvasArea = this.size.width * this.size.height

                //if total scratched area is below the threshold, show the overlay image
                if(!isScratched && totalScratchedArea.floatValue/maxCanvasArea < scratchingThresholdPercentage) {
                    drawImage(image = overlayImage, dstSize = imageSize)
                    //draw the scratch lines with transparency to "erase" the overlay
                    scratchLines.forEach { line ->
                        drawLine(
                            color = Color.Transparent,
                            start = line.start,
                            end = line.end,
                            strokeWidth = line.strokeWidth,
                            cap = scratchLineCap,
                            blendMode = BlendMode.Clear //clears the pixels covered by the source in the destination
                        )
                    }
                }else{
                    if(totalScratchedArea.floatValue>0) {
                        if(!isScratched){
                            onScratchComplete()
                        }
                        if(isScratched){
                            scratchLines.clear()
                            totalScratchedArea.floatValue = 0f
                        }
                    }
                }
            }
        }
    }
}

private data class Line(
    val start: Offset,
    val end: Offset,
    val strokeWidth: Float
){
    fun calculateArea(): Float {
        val lineLength = calculateLength()
        return lineLength * strokeWidth
    }
    private fun calculateLength(): Float {
        return sqrt((end.x - start.x).pow(2) + (end.y - start.y).pow(2))
    }
}

@Composable
fun ScratchCardAnimation() {
    var isScratched by remember { mutableStateOf(false) }

    ScratchCard(
        overlayImage = ImageBitmap.imageResource(R.drawable.scratch_card),
        baseImage = painterResource(R.drawable.reward_img),
        modifier = Modifier.size(200.dp),
        isScratched = isScratched,
        onScratchComplete = {
            isScratched = true // ✅ THIS triggers glow
        }
    )
}
