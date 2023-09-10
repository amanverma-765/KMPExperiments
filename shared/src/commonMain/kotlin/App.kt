
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import ui.screens.magnifiereffect.ContentMagnificationEffect

@Composable
fun App() {
    MaterialTheme {
        ContentMagnificationEffect()
    }
}

expect fun getPlatformName(): String
