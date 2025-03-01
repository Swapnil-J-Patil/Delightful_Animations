package com.example.jetpackcomposeanimations.presentation.button_animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
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
fun ShakeButtonAnimation(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val offsetXAnim = remember { Animatable(0f) }

    Button(
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp)
            .offset(x = offsetXAnim.value.dp)
        ,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03DAC5)),
        contentPadding = PaddingValues(vertical = 16.dp),
        onClick = {
            coroutineScope.launch {
                val shakeValues = listOf(
                    0f, 25f, -25f, 20f, -20f, 15f, -15f, 10f, -10f, 5f, -5f, 0f
                )
                shakeValues.forEach { value ->
                    offsetXAnim.animateTo(
                        targetValue = value,
                        animationSpec = tween(durationMillis = 50)
                    )
                }
            }
        }
    ) {
        Text(
            text = "Shake Button",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
    }
}