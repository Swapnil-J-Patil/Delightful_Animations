package com.example.jetpackcomposeanimations.presentation.text_animation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeanimations.R

@Composable
fun TextWithPhotoBackground(modifier: Modifier = Modifier) {
    val imageBrush= ShaderBrush(ImageShader(ImageBitmap.imageResource(id = R.drawable.img1)))
    Text(text = "Jetpack Compose",
        style = TextStyle(
            brush = imageBrush,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp
        )
    )
}
