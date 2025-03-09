package com.example.jetpackcomposeanimations.presentation.text_animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeanimations.presentation.ui.theme.Poppins
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorBlue
import com.example.jetpackcomposeanimations.presentation.ui.theme.colorGreen
import com.example.jetpackcomposeanimations.presentation.ui.theme.gold
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun TextListAnimation(modifier: Modifier = Modifier) {
    val characters = listOf(
        "Tanjiro" to "Tanjiro Kamado is a kind-hearted and determined Demon Slayer who embarks on a perilous journey after his family is slaughtered by demons, leaving only his sister, Nezuko, as the sole survivor. He possesses an extraordinary sense of smell, allowing him to track enemies and sense emotions. His relentless spirit and compassionate nature make him a formidable warrior, as he masters the Water Breathing technique and later unlocks the powerful Hinokami Kagura. Despite facing overwhelming odds, Tanjiro never wavers in his resolve to cure Nezuko and protect humanity from demons.",

        "Nezuko" to "Nezuko Kamado is Tanjiro's younger sister, who was transformed into a demon but miraculously retains her humanity. Unlike other demons, she does not consume human flesh and instead survives by sleeping for long periods. She possesses incredible strength, rapid regeneration, and the ability to change her size at will. Her Blood Demon Art, 'Exploding Blood,' allows her to ignite her own blood into powerful flames capable of harming demons. Nezuko's strong will and love for her brother make her a crucial ally in the fight against demons, proving that she is much more than just a demon.",

        "Zenitsu" to "Zenitsu Agatsuma is a cowardly yet incredibly talented Demon Slayer who specializes in Thunder Breathing. Despite his fearful nature, he possesses immense potential, only fully unlocking his power when he is unconscious or in a state of extreme focus. His signature move, 'Thunderclap and Flash,' allows him to strike down enemies at lightning-fast speeds. Though often seen as a comic relief due to his exaggerated fearfulness and love-struck tendencies, Zenitsu proves time and again that he is a force to be reckoned with when the situation calls for it.",

        "Inosuke" to "Inosuke Hashibira is a wild and aggressive Demon Slayer raised by boars in the mountains. He wields two serrated swords and developed his own unique fighting style, 'Beast Breathing,' which emphasizes animalistic instincts and brute force. Despite his initial hostility and reckless behavior, Inosuke gradually learns the value of teamwork and friendship. Beneath his rough exterior lies a strong-willed warrior who continually pushes himself to become stronger, proving that he is much more than just an untamed fighter.",

        "Kanao" to "Kanao Tsuyuri is a quiet and reserved Demon Slayer who was trained by the Flower Hashira, Kanae Kocho, and later by Shinobu Kocho. As a survivor of a tragic past, she initially struggled with making decisions, relying on a coin flip to guide her actions. However, through her experiences with Tanjiro and others, she learns to trust her instincts. Kanao is a skilled fighter with exceptional agility, powerful eyesight, and mastery of the Flower Breathing technique, making her a formidable warrior despite her gentle demeanor.",

        "Giyu" to "Giyu Tomioka is the stoic and disciplined Water Hashira of the Demon Slayer Corps. He was the first Hashira to encounter Tanjiro and Nezuko, choosing to spare Nezuko's life after recognizing her unique ability to resist her demonic urges. Giyu wields the Water Breathing technique with unparalleled skill, effortlessly dispatching demons with swift, fluid movements. Despite his cold exterior, he deeply cares for his comrades and upholds his duty as a protector of humanity, carrying the burden of his past with silent resilience.",

        "Shinobu" to "Shinobu Kocho is the Insect Hashira, known for her cheerful yet unsettlingly calm demeanor. Unlike most Demon Slayers, she lacks the raw strength to behead demons, so she relies on her intelligence, speed, and mastery of poison to eliminate them. She crafts her own unique fighting style, using a needle-like sword coated with deadly wisteria poison to swiftly dispatch her enemies. Though she often wears a smile, Shinobu harbors deep-seated hatred toward demons due to her tragic past, making her one of the most intriguing and complex members of the Demon Slayer Corps.",

        "Rengoku" to "Kyojuro Rengoku is the Flame Hashira, a warrior defined by his boundless enthusiasm, unwavering sense of duty, and incredible swordsmanship. He is a passionate protector of humanity, always fighting with a radiant smile and an indomitable spirit. His mastery of Flame Breathing makes him one of the strongest Hashira, capable of taking on even the most powerful demons. Despite his tragic fate, Rengoku leaves a lasting legacy, inspiring Tanjiro and his friends to push forward with courage and determination.",

        "Mitsuri" to "Mitsuri Kanroji is the Love Hashira, known for her incredible flexibility, inhuman strength, and warm-hearted nature. Despite her delicate and affectionate personality, she possesses immense combat abilities, utilizing the Love Breathing technique with her uniquely flexible Nichirin sword. Her kind and caring demeanor make her a beloved member of the Demon Slayer Corps, and her belief in the power of love is a driving force behind her unwavering dedication to protecting humanity.",

        "Muichiro" to "Muichiro Tokito is the young but exceptionally talented Mist Hashira, possessing an unparalleled natural talent for swordsmanship. Though initially appearing absent-minded and aloof, Muichiro is revealed to be a prodigy with incredible analytical skills and combat prowess. His mastery of Mist Breathing allows him to create illusions and move at breathtaking speeds, making him one of the most formidable Hashira. As he regains his lost memories, his true strength and resolve emerge, proving that he is far more than just a drifting cloud."
    )

    val yOffsetValues =
        listOf(-50f, -170f, -270f, -410f, -520f, -640f, -770f, -890f, -1000f, -1130f)

    val animatableList = remember { List(characters.size) { Animatable(300f) } }
    val colorAnimatableList = remember { List(characters.size) { Animatable(0f) } }
    val scaleAnimatableList = remember { List(characters.size) { Animatable(1f) } }
    val rotationAnimatableList = remember { List(characters.size) { Animatable(0f) } }
    val yOffsetAnimatableList = remember { List(characters.size) { Animatable(0f) } }

    val clickedIndex = remember { mutableStateOf<Int?>(0) }
    val showDescription = remember { mutableStateListOf(*Array(characters.size) { false }) }

    animatableList.forEachIndexed { index, animatable ->
        LaunchedEffect(Unit) {
            delay(index.toLong() * 500)
            launch { animatable.animateTo(0f, animationSpec = tween(1500)) }
            launch { colorAnimatableList[index].animateTo(1f, animationSpec = tween(1500)) }
        }

        LaunchedEffect(clickedIndex.value) {
            if (clickedIndex.value == index) {
                val targetY = yOffsetValues[index]
                launch { animatable.animateTo(600f, animationSpec = tween(1500)) }
                launch { colorAnimatableList[index].animateTo(0f, animationSpec = tween(1500)) }
                launch { scaleAnimatableList[index].animateTo(1.5f, animationSpec = tween(1500)) }
                launch {
                    rotationAnimatableList[index].animateTo(360f, animationSpec = tween(1500))
                    yOffsetAnimatableList[index].animateTo(targetY, animationSpec = tween(1000))
                    showDescription[index] = true // Show description after animation
                }
            } else {
                launch { animatable.animateTo(0f, animationSpec = tween(1500)) }
                launch { colorAnimatableList[index].animateTo(1f, animationSpec = tween(1500)) }
                launch { scaleAnimatableList[index].animateTo(1f, animationSpec = tween(1500)) }
                launch { rotationAnimatableList[index].animateTo(0f, animationSpec = tween(1500)) }
                launch {
                    yOffsetAnimatableList[index].animateTo(0f, animationSpec = tween(1000))
                    showDescription[index] = false // Hide description when collapsed
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        characters.forEachIndexed { index, (name, description) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(start = 100.dp, top = 80.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(
                    visible = showDescription[index],
                    enter = expandHorizontally(animationSpec = spring(stiffness = Spring.StiffnessLow)) + fadeIn(),
                    exit = shrinkHorizontally(animationSpec = spring(stiffness = Spring.StiffnessMedium)) + fadeOut()
                ) {
                    Text(
                        text = description,
                        color = gold,
                        style = MaterialTheme.typography.bodyLarge,
                        fontFamily = Poppins,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(top = 45.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            characters.forEachIndexed { index, (name, description) ->

                val color = Color.Lerp(colorBlue, colorGreen, colorAnimatableList[index].value)
                Text(
                    text = name,
                    color = color,
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = Poppins,
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                animatableList[index].value.roundToInt(),
                                yOffsetAnimatableList[index].value.roundToInt()
                            )
                        }
                        .fillMaxWidth()
                        .padding(8.dp)
                        .graphicsLayer(
                            scaleX = scaleAnimatableList[index].value,
                            scaleY = scaleAnimatableList[index].value,
                            rotationZ = rotationAnimatableList[index].value
                        )
                        .noRippleClickable {
                            showDescription[clickedIndex.value!!] =
                                false // Hide description when collapsed
                            clickedIndex.value = index
                        }
                )
            }
        }
    }
}


private fun Color.Companion.Lerp(startColor: Color, endColor: Color, fraction: Float): Color {
    return Color(
        red = lerp(startColor.red, endColor.red, fraction),
        green = lerp(startColor.green, endColor.green, fraction),
        blue = lerp(startColor.blue, endColor.blue, fraction),
        alpha = lerp(startColor.alpha, endColor.alpha, fraction)
    )
}

private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + fraction * (stop - start)
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}