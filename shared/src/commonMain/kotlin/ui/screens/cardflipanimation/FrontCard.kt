@file:OptIn(ExperimentalResourceApi::class)

package ui.screens.cardflipanimation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun FrontCard() {

    val worldMap = painterResource("cardflipanim_res/world_map.png")
    val excludeLogo = painterResource("cardflipanim_res/exclude_logo.png")
    val nfcLogo = painterResource("cardflipanim_res/nfc_logo.png")
    val mastercardLogo = painterResource("cardflipanim_res/mastercard_logo.png")

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
    ) {

        Image(
            painter = worldMap,
            contentDescription = "World Map",
            colorFilter = ColorFilter.tint(Color.LightGray),
            modifier = Modifier
                .fillMaxSize()
                .alpha(.2f),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp)
        ) {


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Aman Verma R.",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500)
                    )
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Image(
                        painter = excludeLogo,
                        contentDescription = "logo",
                        modifier = Modifier
                            .width(28.dp)
                            .height(18.dp)
                    )

                    Text(
                        text = "Java\nBank",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            fontStyle = FontStyle.Italic,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center
                        )
                    )

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Image(
                    painter = nfcLogo,
                    contentDescription = "nfc logo",
                    modifier = Modifier
                        .width((51.5).dp)
                        .height(38.dp)
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 28.dp)
                    .fillMaxWidth()
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "35–070–0003-3256-2022",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(500),
                        )
                    )

                    Text(
                        text = "Exp. 12/26",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight(500)
                        ),
                        modifier = Modifier.alpha(.4f)
                    )
                }
                Column {
                    Image(
                        painter = mastercardLogo,
                        contentDescription = "mastercard logo",
                        modifier = Modifier
                            .width(41.dp)
                            .height(25.dp)
                    )

                    Text(
                        text = "Mastercard",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 8.sp,
                            fontWeight = FontWeight(500)
                        )
                    )
                }
            }
        }
    }

}


