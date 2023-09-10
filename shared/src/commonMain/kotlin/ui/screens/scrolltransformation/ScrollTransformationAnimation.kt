package ui.screens.scrolltransformation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrollTransformation() {

    // LazyColumn Related Variables
    val lazyState = rememberLazyGridState()
    val isScrolling = lazyState.isScrollInProgress
    val isScrollingUp = lazyState.isScrollingUp()

    // Card Animation
    val animateScroll = animateFloatAsState(
        targetValue = if (isScrollingUp && isScrolling) {
            -70f
        } else if (!isScrollingUp && isScrolling) {
            70f
        } else 0f,
        animationSpec = tween(300),
        label = ""
    )

    Scaffold(
        // TopAppBar
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Transformation Scroll Effect") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Menu, "")
                    }
                },
                modifier = Modifier.alpha(.5f)
            )
        }

    ) { paddingValues ->

        // LazyColumn
        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            contentPadding = paddingValues,
            state = lazyState,
        ) {
            items(itemColors) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(top = 16.dp, start = 20.dp, end = 20.dp)

                        // animation applied here
                        .graphicsLayer {
                            rotationX = animateScroll.value
                            cameraDistance = 20f
                        },
                    colors = CardDefaults.cardColors(containerColor = it)
                ) {}
            }
        }
    }
}

val itemColors = listOf(

    Color(0xFFFF1744),
    Color(0xFFD500F9),
    Color(0xFF00E5FF),
    Color(0xFF76FF03),
    Color(0xFF3D5AFE),
    Color(0xFF1DE9B6),
    Color(0xFF43A047),
    Color(0xFF039BE5),
    Color(0xFF00897B),
    Color(0xFFFDD835),
    Color(0xFFF8BBD0),
    Color(0xFFD32F2F),
    Color(0xFF69F0AE),
    Color(0xFFFF6F00),
    Color(0xFF01579B),
    Color(0xFFBF360C),
    Color(0xFFFBC02D),
    Color(0xFF2962FF),
    Color(0xFFAA00FF),
    Color(0xFF00B8D4),
    Color(0xFFD50000),
    Color(0xFFD500F9),
    Color(0xFFFF1744),
    Color(0xFFD500F9),
    Color(0xFF00E5FF),
    Color(0xFF76FF03),
    Color(0xFF3D5AFE),
    Color(0xFF1DE9B6),
    Color(0xFF43A047),
    Color(0xFF039BE5),
    Color(0xFF00897B),
    Color(0xFFFDD835),
    Color(0xFFF8BBD0),
    Color(0xFFD32F2F),
    Color(0xFF69F0AE),
    Color(0xFFFF6F00),
    Color(0xFF01579B),
    Color(0xFFBF360C),
    Color(0xFFFBC02D),
    Color(0xFF2962FF),
    Color(0xFFAA00FF),
    Color(0xFF00B8D4),
    Color(0xFFD50000),
    Color(0xFFD500F9)
)


// Returns the lazyColumn scroll direction
@Composable
private fun LazyGridState.isScrollingUp(): Boolean {
    val previousIndex = remember(this) { mutableIntStateOf(firstVisibleItemIndex) }
    val previousScrollOffset = remember(this) { mutableIntStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex.value != firstVisibleItemIndex) {
                previousIndex.value > firstVisibleItemIndex
            } else {
                previousScrollOffset.value >= firstVisibleItemScrollOffset
            }.also {
                previousIndex.value = firstVisibleItemIndex
                previousScrollOffset.value = firstVisibleItemScrollOffset
            }
        }
    }.value
}
