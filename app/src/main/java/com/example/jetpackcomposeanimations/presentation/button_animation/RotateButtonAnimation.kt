package com.example.jetpackcomposeanimations.presentation.button_animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun RotateButtonAnimation(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val rotateAnim = remember { Animatable(0f) }


    Button(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp)
            .rotate(rotateAnim.value)
        ,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFAD206)),
        contentPadding = PaddingValues(vertical = 16.dp),
        onClick = {
            coroutineScope.launch {
                rotateAnim.animateTo(
                    targetValue = 15f,
                    animationSpec = tween(durationMillis = 100)
                )
                rotateAnim.animateTo(
                    targetValue = -15f,
                    animationSpec = tween(durationMillis = 100)
                )
                rotateAnim.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 100)
                )
            }
        }
    ) {
        Text(
            text = "Rotating Button",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
    }
}