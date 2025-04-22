package com.example.jetpackcomposeanimations.presentation.card_animations

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposeanimations.R
import com.example.jetpackcomposeanimations.presentation.ui.theme.blue
import com.example.jetpackcomposeanimations.presentation.ui.theme.green

@Composable
fun FlipCardAnimation(){

    var rotated by remember {
        mutableStateOf(false)
    }

    val rotate by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        // we specify the animation duration to 0.6 sec
        animationSpec = tween(600),
    )

    Card (
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xffEFEFEF)),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .padding(10.dp)
            .height(320.dp)
            .graphicsLayer {
                // we'll rotate the card on the horizontal axis using the float state we created
                rotationY = rotate
                // this line is to reduce the zoom-in effect
                cameraDistance = 10 * density
            }
            .fillMaxWidth()
    ){


        if (rotate < 90f)
            CardFace {
               rotated = !rotated
            }

        else
            CardTale {
                rotated = !rotated
            }


    }
}
// Card Tale ui design
@Composable
fun CardTale( onClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(green)
            .clickable { onClick.invoke() }
    ) {

    }
}

// Card face ui design
@Composable
fun CardFace(onClick: () -> Unit) {

    Box(
        Modifier
            .fillMaxSize()
            .background(blue)
            .clickable { onClick.invoke() }
    ) {

    }
}