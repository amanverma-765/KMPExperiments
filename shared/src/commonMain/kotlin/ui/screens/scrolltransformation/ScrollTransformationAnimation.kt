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
