package ui.screens.magnifiereffect

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MagnifierStyle
import androidx.compose.foundation.magnifier
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
actual fun Modifier.magEffect(magnifierCenter: Offset, magnification: Float): Modifier {
    return magnifier(
        sourceCenter = { magnifierCenter },
        magnifierCenter = { magnifierCenter },
        style = MagnifierStyle(
            size = DpSize(150.dp, 150.dp),
            cornerRadius = 75.dp,
            clippingEnabled = false
        ),
        zoom = magnification
    )
}