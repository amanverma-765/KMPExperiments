
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import ui.screens.earthinspace.EarthInSpace

@Composable
fun App() {
    MaterialTheme {
        EarthInSpace()
    }
}

expect fun getPlatformName(): String