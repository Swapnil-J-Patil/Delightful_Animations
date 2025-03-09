package com.example.jetpackcomposeanimations.presentation.text_animation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeanimations.presentation.ui.theme.Poppins
import com.example.jetpackcomposeanimations.presentation.ui.theme.Purple80
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorBlue
import com.example.jetpackcomposeanimations.presentation.ui.theme.darkGreen
import com.example.jetpackcomposeanimations.presentation.ui.theme.gold
import kotlinx.coroutines.delay

@Composable
fun AnimatedText() {
    val messages = listOf("Friend", "Amigo", "Buddy", "Brother")
    val messageColors = listOf(gold, darkGreen, colorBlue, Purple80)
    var currentMessageIndex by remember { mutableIntStateOf(0) }
    val currentMessage = messages[currentMessageIndex]
    val currentColor = messageColors[currentMessageIndex]

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            currentMessageIndex = (currentMessageIndex + 1) % messages.size
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .padding(top=45.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        Row {
            Text(
                text = "Hello ",
                color = Color(0xFF61afef),
                fontFamily = Poppins,
                style = MaterialTheme.typography.displayMedium,
            )
            AnimatedContent(
                targetState = currentMessage,
                transitionSpec = {
                    slideInVertically { height -> height } + fadeIn() togetherWith
                            slideOutVertically { height -> -height } + fadeOut()
                }, label = "currentMessageLabel"
            ) { targetMessage ->
                Text(
                    text = targetMessage,
                    fontFamily = Poppins,
                    style = MaterialTheme.typography.displayMedium,
                    color = currentColor
                )
            }
        }
    }
}