package ui.screens.magnifiereffect

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import getPlatformName
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import kotlin.math.round

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ContentMagnificationEffect() {

    /**
     * We are getting magnification effect by using magnification modifier
     * this modifier is not available for other platforms other than android, it might be available for other platforms in future,
     * So we have to create a separate implementation for an android platform
     **/

    val isSupported = getPlatformName() == "Android" // checking if a platform is android
    val image = painterResource("magnifiereffect_res/city_aerial_view.webp")

    val magSlider = remember { mutableFloatStateOf(1f) }
    val magnifierCenter = remember { mutableStateOf(Offset.Unspecified) } // storing center for magnifier

    // for displaying the magnification value
    val showMagVal = remember { mutableStateOf(false) }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text("Magnification Effect") }) },
        bottomBar = { BottomSlider(magSlider, magnifierCenter, showMagVal) }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                // adding magnification to the screen
                .magEffect(magnifierCenter.value, magSlider.value)
                // detecting drag gesture on the whole screen
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { magnifierCenter.value = it },
                        onDrag = { _, delta ->
                            magnifierCenter.value = magnifierCenter.value.plus(delta)
                        },
                        onDragEnd = {
                            magnifierCenter.value = Offset.Unspecified
                        },
                        onDragCancel = {
                            magnifierCenter.value = Offset.Unspecified
                        },
                    )
                }
        ) {

            Image(
                painter = image,
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.requiredSize(Dp.Infinity)
            )

            // displayed if the platform is other than android
            Text(
                text = if (isSupported) "" else "${getPlatformName()} is Not Supported!",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )

            // Displaying magnification value on the screen
            AnimatedVisibility(
                visible = showMagVal.value,
                enter = scaleIn() + fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    text = "${round(magSlider.value * 10) / 10}",
                    fontSize = 100.sp,
                    color = Color.White,
                )
            }
        }
    }
}



expect fun Modifier.magEffect(magnifierCenter: Offset, magnification: Float): Modifier