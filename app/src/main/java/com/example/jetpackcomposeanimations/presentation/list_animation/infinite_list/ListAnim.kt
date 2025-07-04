package com.example.jetpackcomposeanimations.presentation.list_animation.infinite_list

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import kotlin.math.roundToInt
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.ui.util.lerp
import kotlin.math.abs

data class Contact(
    val name: String,
    val role: String,
    val address: String,
    val phone: String,
    val email: String,
    val website: String,
)

// Sample static list like in Flutter's Contact.contacts
val contacts = listOf(
    Contact("Alice", "Designer", "123 Lane", "123-456", "alice@example.com", "alice.dev"),
    Contact("Bob", "Developer", "456 Road", "987-654", "bob@example.com", "bob.dev"),
    Contact("Carol", "Manager", "789 Blvd", "456-789", "carol@example.com", "carol.dev"),
    Contact("Dave", "Engineer", "321 St", "741-852", "dave@example.com", "dave.dev"),
    Contact("Eva", "Tester", "159 Ave", "963-852", "eva@example.com", "eva.dev"),
    Contact("Alice", "Designer", "123 Lane", "123-456", "alice@example.com", "alice.dev"),
    Contact("Bob", "Developer", "456 Road", "987-654", "bob@example.com", "bob.dev"),
    Contact("Carol", "Manager", "789 Blvd", "456-789", "carol@example.com", "carol.dev"),
    Contact("Dave", "Engineer", "321 St", "741-852", "dave@example.com", "dave.dev"),
    Contact("Eva", "Tester", "159 Ave", "963-852", "eva@example.com", "eva.dev")

)
@Composable
fun ContactCard(contact: Contact) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = contact.name, style = MaterialTheme.typography.titleLarge)
            Text(text = contact.role, style = MaterialTheme.typography.bodyMedium)
            Text(text = contact.address, style = MaterialTheme.typography.bodySmall)
            Text(text = contact.phone, style = MaterialTheme.typography.bodySmall)
            Text(text = contact.email, style = MaterialTheme.typography.bodySmall)
            Text(text = contact.website, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun CardFlyAndShrink() {

   /* val pagerState = rememberPagerState(initialPage = 0, pageCount = {10})
    val visualizedItems = 4 // how many stacked cards you want
    val children: List<@Composable () -> Unit> = contacts.map { contact ->
        { ContactCard(contact = contact) }
    }



    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val pageHeightFraction = 1f / visualizedItems

    VerticalPager(
        state = pagerState,
        pageSize = PageSize.Fixed(screenHeight * pageHeightFraction),
        beyondViewportPageCount = visualizedItems,
        modifier = Modifier.fillMaxSize(),
    ) { page ->
        val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
        val scale = lerp(0.8f, 1f, 1f - abs(pageOffset).coerceIn(0f, 1f))
        val translateY = lerp(50f, 0f, 1f - abs(pageOffset).coerceIn(0f, 1f))

        Box(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationY = translateY
                }
                .padding(8.dp)
                .fillMaxWidth()
                .height(screenHeight * pageHeightFraction),
            contentAlignment = Alignment.Center
        ) {
            children[page]()
        }
    }*/


        val pagerState = rememberPagerState(pageCount = { 3 }, initialPage = 2)
        val overlapFactor = 2.4f // how much each page overlaps
        val density = LocalDensity.current
        val colors = listOf(Color.Blue, Color.White, Color.Cyan)

        val overlapSpacingPx = with(density) { (100.dp * overlapFactor).toPx() }

        Box(modifier = Modifier.fillMaxSize()) {
            VerticalPager(
                state = pagerState,
                pageSpacing = -overlapSpacingPx.dp, // negative for overlap
                modifier = Modifier.fillMaxSize()
            ) { page ->
                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            val scale = 0.9f + (1 - 0.9f) * (1 - pagerState.currentPageOffsetFraction)
                            scaleX = scale
                            scaleY = scale
                            translationY = pagerState.currentPageOffsetFraction * overlapSpacingPx
                        }
                        .size(300.dp)
                        .background(colors[page], shape = RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Page ${page + 1}")
                }
            }
        }


    //For horizontal
    /*val pagerState = rememberPagerState(pageCount = { 3 })
    val overlapFactor = 2.4f // overlap between pages
    val overlapSpacing = 100.dp * overlapFactor

    val colors= listOf<Color>(
        Color.Blue,
        Color.White,
        Color.Cyan
    )
    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            pageSpacing = -overlapSpacing, // Corrected usage
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        val scale = 0.9f + (1 - 0.9f) * (1 - pagerState.currentPageOffsetFraction)
                        scaleX = scale
                        scaleY = scale
                        translationX = pagerState.currentPageOffsetFraction * overlapSpacing.toPx()
                    }
                    .size(300.dp)
                    .background(colors[page], shape = RoundedCornerShape(20.dp))
                    //.shadow(20.dp)
                ,
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Page ${page + 1}")
            }
        }
    }
*/

    /* var offsetY by remember { mutableStateOf(0f) }
     var cardScale by remember { mutableStateOf(1f) }

     val animatedOffsetY by animateFloatAsState(
         targetValue = offsetY,
         animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing),
         label = "animatedOffsetY"
     )

     val animatedScale by animateFloatAsState(
         targetValue = cardScale,
         animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing),
         label = "animatedScale"
     )

     Box(
         modifier = Modifier
             .fillMaxSize()
             .background(Color(0xFFD23131))
             .pointerInput(Unit) {
                 detectVerticalDragGestures(
                     onVerticalDrag = { _, dragAmount ->
                         offsetY = (offsetY + dragAmount).coerceIn(-1200f, 0f)
                         cardScale = (1f + offsetY / 1500f).coerceIn(0.8f, 1f)
                     },
                     onDragEnd = {
                         if (offsetY < -300f) {
                             // Keep it shrunk
                             offsetY = -1200f
                             cardScale = 0.8f
                         } else {
                             // Reset back to normal
                             offsetY = 0f
                             cardScale = 1f
                         }
                     }
                 )
             },
         contentAlignment = Alignment.BottomCenter
     ) {
         Card(
             modifier = Modifier
                 .graphicsLayer {
                     translationY = animatedOffsetY
                     scaleX = animatedScale
                     scaleY = animatedScale
                 }
                 .fillMaxWidth(0.9f)
                 .height(200.dp)
                 .shadow(8.dp, RoundedCornerShape(16.dp))
                 .background(Color.White, RoundedCornerShape(16.dp)),
             elevation = CardDefaults.cardElevation(8.dp)
         ) {
             Box(
                 modifier = Modifier.fillMaxSize(),
                 contentAlignment = Alignment.Center
             ) {
                 Text(
                     "Swipe Up to Shrink\nSwipe Down to Reset",
                     textAlign = TextAlign.Center,
                     fontSize = 18.sp
                 )
             }
         }
     }*/
}






