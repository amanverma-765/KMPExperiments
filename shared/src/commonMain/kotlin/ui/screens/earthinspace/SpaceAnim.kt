package ui.screens.earthinspace

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TheSpace() {

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