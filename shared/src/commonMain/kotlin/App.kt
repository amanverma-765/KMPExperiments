
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import ui.screens.cardflipanimation.FlipCardScreen

@Composable
fun App() {
    MaterialTheme {
        FlipCardScreen()
    }
}

expect fun getPlatformName(): String