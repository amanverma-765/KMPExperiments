@file:OptIn(ExperimentalResourceApi::class)

package ui.screens.earthinspace

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@Composable
fun EarthInSpace() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        // The Space
        Space()
        // Glow effect around earth
        Glow()
        //Earth rotating model
        Earth()
    }
}

@Composable
private fun Glow() {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .blur(150.dp)
    ) {
        Box(
            modifier = Modifier
                .size(170.dp)
                .clip(CircleShape)
                .background( Color.Blue )
        )
    }
}

@Composable
private fun Earth() {

    val earth = painterResource("earthinspace_res/earthmap.webp")

    val width = remember { mutableFloatStateOf(1f) }
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val earthAnim = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = width.floatValue,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 7000,
                easing = LinearEasing
            ),
        ),
        label = "Animating Earth"
    )

    Box(
        modifier = Modifier
            .size(170.dp)
            .clip(CircleShape)
            .background(Color(0xFF005ba5))

    ) {

        Image(
            painter = earth,
            contentDescription = "Earth",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .requiredWidth(Dp.Infinity)
                .graphicsLayer {
                    translationX = earthAnim.value - width.floatValue
                }
                .onGloballyPositioned {
                    width.floatValue = it.size.width.toFloat()
                }
        )

        Image(
            painter = earth,
            contentDescription = "Earth",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .requiredWidth(Dp.Infinity)
                .graphicsLayer {
                    translationX = earthAnim.value
                }
        )

    }
}

@Composable
private fun Space() {

    val space = painterResource("earthinspace_res/spacebg.webp")

    val width = remember { mutableFloatStateOf(1f) }
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val spaceAnim = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = width.floatValue,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 20000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "Animating Space"
    )

    Image(
        painter = space,
        contentDescription = "Space",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .requiredWidth(Dp.Infinity)
            .graphicsLayer {
                translationX = spaceAnim.value - width.floatValue
            }
            .onGloballyPositioned {
                width.floatValue = it.size.width.toFloat()
            }
    )

    Image(
        painter = space,
        contentDescription = "Space",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .requiredWidth(Dp.Infinity)
            .graphicsLayer {
                translationX = spaceAnim.value
            }

    )
}