package com.example.jetpackcomposeanimations.presentation.card_animations

import android.graphics.BitmapShader
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RawRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.R
import kotlinx.coroutines.launch



@Composable
fun Modifier.liquidGlass(
    @RawRes shaderRes: Int,
    bitmap: ImageBitmap?,
    sizeM: Float = 0.6f,
    pointerEnabled: Boolean = true
): Modifier = composed {
    val context = LocalContext.current
    val src by remember(shaderRes) {
        mutableStateOf(
            context.resources.openRawResource(shaderRes)
                .bufferedReader().use { it.readText() }
        )
    }

    val shader = remember(src) {
        if (Build.VERSION.SDK_INT >= 33) android.graphics.RuntimeShader(src) else null
    }

    var gesture by remember { mutableStateOf(Offset(-1f, -1f)) }
    var modifier = this

    if (pointerEnabled) {
        modifier = modifier.pointerInput(Unit) {
            awaitPointerEventScope {
                while (true) {
                    val e = awaitPointerEvent()
                    val p = e.changes.firstOrNull()
                    if (p != null) {
                        gesture = p.position
                    }
                }
            }
        }
    }
    modifier.then(
        Modifier.drawWithCache {

            if (Build.VERSION.SDK_INT < 33 || shader == null) {
                onDrawWithContent { drawContent() }
            } else {
                val frameworkPaint = android.graphics.Paint()
                val androidBmp = bitmap?.asAndroidBitmap()
                val bmpShader = if (androidBmp != null) {
                    BitmapShader(androidBmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
                } else null

                onDrawWithContent {
                    drawContent()
                    shader.setFloatUniform(
                        "iResolution",
                        size.width, size.height, 0f
                    )
                    val mx: Float
                    val my: Float
                    if (gesture.x < 0f || gesture.y < 0f || !pointerEnabled) {
                        mx = size.width / 2f
                        my = size.height / 2f
                    } else {
                        mx = gesture.x
                        my = gesture.y
                    }
                    shader.setFloatUniform("iGesture", mx, my, mx, my)
                    val zoom = 0.2f + sizeM.coerceIn(0f, 1f) * 1.8f
                    shader.setFloatUniform("uSize", zoom, 0f, 0f, 0f)
                    if (bmpShader != null && androidBmp != null) {
                        shader.setInputShader("iImage1", bmpShader)
                        shader.setFloatUniform(
                            "iImageResolution",
                            androidBmp.width.toFloat(),
                            androidBmp.height.toFloat(),
                            0f
                        )
                    } else {
                        shader.setFloatUniform("iImageResolution", 0f, 0f, 0f)
                    }

                    frameworkPaint.shader = shader

                    drawIntoCanvas { c ->
                        c.nativeCanvas.drawRect(0f, 0f, size.width, size.height, frameworkPaint)
                    }
                    drawContent()
                }
            }
        }
    )
}

@Composable
private fun BottomControls(
    value: Float,
    onChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.25f))
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Slider(
            value = value,
            onValueChange = onChange,
            valueRange = 0f..1f,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFF00BCD4),
                activeTrackColor = Color(0xFF131313),
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun DemoScreenWithModifier() {
    val bmp = ImageBitmap.imageResource(id = R.drawable.img1)
    var sizeSlider by remember { mutableStateOf(0.1f) }

    Box(
        Modifier
            .fillMaxSize()
            .liquidGlass(
                shaderRes = R.raw.liquid_glass,
                bitmap = bmp,
                sizeM = sizeSlider,
                pointerEnabled = true
            )
    ) {

       /* BottomControls(
            value = sizeSlider,
            onChange = { sizeSlider = it },
            modifier = Modifier.align(Alignment.BottomCenter)
        )*/
    }
}

@Composable
fun DemoScreenWithModifierV2()
{
    val bmp = ImageBitmap.imageResource(id = R.drawable.img1)
    var baseSize by remember { mutableStateOf(0.6f) }
    val pulse = remember { Animatable(1f) }
    val scope = rememberCoroutineScope()

    Box(
        Modifier
            .fillMaxSize()
            .liquidGlass(
                shaderRes = R.raw.liquid_glass,
                bitmap = bmp,
                sizeM = (baseSize * pulse.value).coerceIn(0f, 1f),
                pointerEnabled = true
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        scope.launch {
                            pulse.animateTo(
                                0.40f,
                                animationSpec = tween(70)
                            )
                            pulse.animateTo(
                                1f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioHighBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            )
                        }
                    }
                )
            }
    ) {
        BottomControls(
            value = baseSize,
            onChange = { baseSize = it },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}