package com.example.jetpackcomposeanimations.presentation.image_animations

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.R
import com.example.jetpackcomposeanimations.presentation.ui.theme.pink

@Composable
fun PlaneHealth(healthPercentage: Float) {
    val planeSize = 240
    val imageBitmap = ImageBitmap.imageResource(R.drawable.img)
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
            .size(planeSize.dp)
            .drawWithContent {
                with(drawContext.canvas.nativeCanvas) {
                    val checkPoint = saveLayer(null, null)

                    drawContent()
                    drawImage(
                        image = imageBitmap,
                        dstSize = IntSize(
                            width = planeSize.dp.toPx().toInt(),
                            height = planeSize.dp.toPx().toInt()
                        ),
                        blendMode = BlendMode.DstIn
                    )

                    restoreToCount(checkPoint)
                }
            }
    ) {
        Box(
            modifier = Modifier
                .size(planeSize.dp+150.dp)
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
                    .fillMaxSize(0.4f)
                    .align(Alignment.Center)
                    ,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .size(120.dp, 300.dp)
                    .drawWithContent {
                        drawContent()
                        drawRect(brush = metalShineMask)
                    }
            )

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