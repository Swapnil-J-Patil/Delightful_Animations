package com.example.jetpackcomposeanimations.presentation.lucky_wheel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LuckyWheelScreen() {
    val items = listOf("Apple", "Banana", "Cherry", "Date", "Fig", "Grape", "Kiwi", "Lemon")
    var result by remember { mutableStateOf("") }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        WheelStand(
            modifier = Modifier
                .padding(top=450.dp)
                .fillMaxWidth()
                .height(150.dp)
        )
        LuckyWheel(
            items = items,
            onSpinEnd = { index -> result = "You got: ${items[index]}" },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Text(result, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier
            .padding(top=600.dp)
        )
    }
}

