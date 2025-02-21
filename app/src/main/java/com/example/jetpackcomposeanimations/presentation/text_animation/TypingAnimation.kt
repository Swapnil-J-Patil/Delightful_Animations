package com.example.jetpackcomposeanimations.presentation.text_animation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun TypingAnimation(text: String, modifier: Modifier = Modifier) {
    var visibleText by remember { mutableStateOf("") }
    val fullText = text

    // Launch the typing animation
    LaunchedEffect(fullText) {
        while (true) {
            // Typing animation forward
            for (i in fullText.indices) {
                visibleText = fullText.take(i + 1)
                delay(100) // Controls the typing speed
            }

            // Pause for a moment before starting to erase the text
            delay(1000)

            // Erase animation backward
            for (i in fullText.length downTo 1) {
                visibleText = fullText.take(i - 1)
                delay(50) // Controls the speed of erasing
            }

            // Pause again before starting the next iteration
            //delay(500)
        }
    }

    Text(
        text = visibleText,
        style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
        modifier = modifier
    )
}