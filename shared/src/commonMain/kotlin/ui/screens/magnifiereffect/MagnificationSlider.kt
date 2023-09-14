package ui.screens.magnifiereffect

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.isUnspecified
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BottomSlider(
    magSlider: MutableFloatState,
    magnifierCenter: MutableState<Offset>,
    showMagVal: MutableState<Boolean>,
) {

    val scope = rememberCoroutineScope()

    AnimatedVisibility(
        visible = magnifierCenter.value.isUnspecified,
        enter = slideInVertically { it } + fadeIn(),
        exit = slideOutVertically { it } + fadeOut(),
        modifier = Modifier.fillMaxWidth().padding(vertical = 42.dp)
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 24.dp,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.wrapContentSize()
        ) {
            Slider(
                value = magSlider.value,
                onValueChange = { magSlider.value = it },
                valueRange = 1f..5f,
                colors = SliderDefaults.colors(inactiveTrackColor = Color.LightGray),
                onValueChangeFinished = {
                    scope.launch {
                        showMagVal.value = true
                        delay(500)
                        showMagVal.value = false
                    }
                },
                modifier = Modifier
                    .width(300.dp)
                    .padding(16.dp)
            )
        }
    }
}