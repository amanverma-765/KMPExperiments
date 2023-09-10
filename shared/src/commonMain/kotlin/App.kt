
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import ui.screens.scrolltransformation.ScrollTransformation

@Composable
fun App() {
    MaterialTheme {
        ScrollTransformation()
    }
}

expect fun getPlatformName(): String
