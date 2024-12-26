package com.example.jetpackcomposeanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.toMutableStateList
import com.example.jetpackcomposeanimations.presentation.AnimationExamplesScreen
import com.example.jetpackcomposeanimations.presentation.other.PagerAnimation
import com.example.jetpackcomposeanimations.presentation.ui.theme.JetpackComposeAnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeAnimationsTheme {
                AnimationExamplesScreen()

               // PagerAnimation()
               /* DragDropList(
                    items = ReorderItem,
                    onMove = { fromIndex, toIndex -> ReorderItem.move(fromIndex, toIndex)}
                )*/

            }
        }
    }
}
val ReorderItem = listOf(
    "Item 1",
    "Item 2",
    "Item 3",
    "Item 4",
    "Item 5",
    "Item 6",
    "Item 7",
    "Item 8",
    "Item 9",
    "Item 10",
    "Item 11",
    "Item 12",
    "Item 13",
    "Item 14",
    "Item 15",
    "Item 16",
    "Item 17",
    "Item 18",
    "Item 19",
    "Item 20"
).toMutableStateList()
