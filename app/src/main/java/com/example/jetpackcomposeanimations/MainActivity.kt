package com.example.jetpackcomposeanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.toMutableStateList
import com.example.jetpackcomposeanimations.presentation.AnimationExamplesScreen
import com.example.jetpackcomposeanimations.presentation.ui.theme.JetpackComposeAnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeAnimationsTheme {
                AnimationExamplesScreen()
            }
        }
    }
}

