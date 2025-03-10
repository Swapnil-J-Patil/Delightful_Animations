package com.example.jetpackcomposeanimations.presentation.custom_toast_animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.jetpackcomposeanimations.R
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.ui.theme.blue
import com.example.jetpackcomposeanimations.presentation.ui.theme.green
import com.example.jetpackcomposeanimations.presentation.ui.theme.red
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// ✅ Toast Property Interface
interface SweetToastProperty {
    fun getResourceId(): Int
    fun getBackgroundColor(): Color
    fun getBorderColor(): Color
    fun getTextColor(): Color
    fun getProgressBarColor(): Color
}

// ✅ Toast Types
class Success : SweetToastProperty {
    override fun getResourceId() = R.drawable.baseline_check_circle_24
    override fun getBackgroundColor() = Color.White
    override fun getBorderColor() = Color.Transparent
    override fun getTextColor() = Color.Black
    override fun getProgressBarColor() = green
}

class Error : SweetToastProperty {
    override fun getResourceId() = R.drawable.baseline_warning_24
    override fun getBackgroundColor() = Color.White
    override fun getBorderColor() = Color.Transparent
    override fun getTextColor() = Color.Black
    override fun getProgressBarColor() = red
}

class Info : SweetToastProperty {
    override fun getResourceId() = R.drawable.baseline_info_24
    override fun getBackgroundColor() = Color.White
    override fun getBorderColor() = Color.Transparent
    override fun getTextColor() = Color.Black
    override fun getProgressBarColor() = blue
}

// ✅ Custom Toast Component
@Composable
fun CustomSweetToast(
    message: String,
    type: SweetToastProperty,
    durationMillis: Long = 3000, // Auto-dismiss time
    onDismiss: () -> Unit,
    visibility: Boolean
) {
    val progress = remember { Animatable(1f) }

    // Reset progress and start animation when visibility becomes true
    LaunchedEffect(visibility) {
        if (visibility) {
            progress.snapTo(1f) // Reset progress instantly
            progress.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis.toInt(), easing = LinearEasing)
            )
            onDismiss() // Dismiss toast after progress completion
        }
    }

    // Slide-Up Animation
    val animatedOffset by animateFloatAsState(
        targetValue = 0f,
        animationSpec = tween(800, easing = LinearOutSlowInEasing),
        label = "toast-slide-up"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(Color.Transparent)
            .offset(y = animatedOffset.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        AnimatedVisibility(
            visible = visibility,
            enter = scaleIn(
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
            ),
            exit = scaleOut()
        ) {
            Column(
                modifier = Modifier
                    .shadow(6.dp, shape = RoundedCornerShape(12.dp))
                    .background(type.getBackgroundColor(), RoundedCornerShape(8.dp))
                    .border(2.dp, type.getBorderColor(), RoundedCornerShape(8.dp))
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Icon(
                            painter = painterResource(id = type.getResourceId()),
                            contentDescription = "Toast Icon",
                            tint = type.getProgressBarColor(),
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = message,
                            fontWeight = FontWeight.Bold,
                            color = type.getTextColor(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_close_24),
                        contentDescription = "Toast Icon",
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(25.dp)
                            .align(AbsoluteAlignment.CenterRight)
                            .clickable {
                                onDismiss()
                            }
                    )
                }

                // Smooth Progress Bar
                LinearProgressIndicator(
                    progress = progress.value, // Smooth animated progress
                    color = type.getProgressBarColor(),
                    trackColor = Color.LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                )
            }
        }
    }
}

// ✅ Example Usage
@Composable
fun CustomToast() {
    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("Success!") }
    var toastType by remember { mutableStateOf<SweetToastProperty>(Success()) }
    val coroutineScope= rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                showToast = false // Dismiss current toast
                coroutineScope.launch { // Ensure state updates properly
                    delay(100) // Small delay to allow recomposition
                    toastMessage = "Operation Completed!"
                    toastType = Success()
                    showToast = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = green),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(text = "Show Success Toast",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White)
        }

        Button(
            onClick = {
                showToast = false // Dismiss current toast
                coroutineScope.launch { // Ensure state updates properly
                    delay(100) // Small delay to allow recomposition
                    toastMessage = "An error occurred!"
                    toastType = Error()
                    showToast = true
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = red),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text("Show Error Toast",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White)
        }
        Button(
            onClick = {
                coroutineScope.launch { // Ensure state updates properly
                    showToast = false // Dismiss current toast
                    delay(100) // Small delay to allow recomposition
                    toastMessage = "Information Toast!"
                    toastType = Info()
                    showToast = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = blue),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text("Show Information Toast",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White)
        }

        // Show toast when triggered

        CustomSweetToast(
            message = toastMessage,
            type = toastType,
            onDismiss = { showToast = !showToast },
            visibility = showToast
        )
    }
}
