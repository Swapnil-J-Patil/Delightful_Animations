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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun ScaleButton(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val scaleAnim = remember { Animatable(1f) }

    Button(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp)
            .scale(scaleAnim.value)
        ,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8DDD66)),
        contentPadding = PaddingValues(vertical = 16.dp),
        onClick = {
            coroutineScope.launch {
                scaleAnim.animateTo(
                    targetValue = 1.2f,
                    animationSpec = tween(durationMillis = 100)
                )
                scaleAnim.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(durationMillis = 100)
                )
            }
        }
    ) {
        Text(
            text = "Scaling Button",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
    }
}