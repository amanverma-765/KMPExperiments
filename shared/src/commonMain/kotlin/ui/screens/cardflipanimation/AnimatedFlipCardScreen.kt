package ui.screens.cardflipanimation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun FlipCardScreen() {

    val isFlipped = rememberSaveable { mutableStateOf(true) }
    val flipAnim = animateFloatAsState(
        targetValue = if (isFlipped.value) 0f else 180f,
        label = "Card Animation Provider",
        animationSpec = tween(
            durationMillis = 1500,
            easing = FastOutSlowInEasing
        )
    )

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .graphicsLayer {
                    rotationY = flipAnim.value
                    cameraDistance = 20f
                }
                .clickable { isFlipped.value = !isFlipped.value }
        ) {

            if (flipAnim.value <= 90) FrontCard() else BackCard()
        }

        Spacer(modifier = Modifier.size(16.dp))
        Button(
            onClick = { isFlipped.value = !isFlipped.value },
        ) {
            Text(text = "Flip")
        }
    }
}
