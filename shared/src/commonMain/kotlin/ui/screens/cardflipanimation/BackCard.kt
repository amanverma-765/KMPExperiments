package ui.screens.cardflipanimation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BackCard() {

    val cardGradient = remember {
        Brush.linearGradient(
            colors = listOf(
                Color(0xFF00014F),
                Color(0xFF3A6073)
            )
        )
    }

    Box(
        modifier = Modifier
            .width(354.dp)
            .height(225.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = cardGradient
            )
            .padding(5.dp)
            .graphicsLayer(
                rotationY = 180f
            )
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                fontSize = 6.sp,
                color = Color.White,
                lineHeight = 10.sp,
                modifier = Modifier.padding(start = 12.dp)
            )

            Spacer(modifier = Modifier.size(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp)
                    .background(Color.Black)
            )

            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(42.dp)
                    .background(Color.White)
            ) {
                Text(
                    text = (123).toString(),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            }

            Text(
                text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s",
                fontSize = 6.sp,
                color = Color.White,
                lineHeight = 10.sp,
                modifier = Modifier.padding(horizontal = 18.dp)
            )

            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.",
                fontSize = 6.sp,
                color = Color.White,
                lineHeight = 10.sp,
                modifier = Modifier.padding(horizontal = 18.dp)
            )
        }

    }
}