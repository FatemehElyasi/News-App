package ir.fatemelyasi.compose.screens

import android.R.attr.text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.fatemelyasi.compose.data.Message
import ir.fatemelyasi.compose.R
import ir.fatemelyasi.compose.ui.theme.Black
import ir.fatemelyasi.compose.ui.theme.ComposeTheme
import ir.fatemelyasi.compose.ui.theme.Gray

@Composable
fun SecondScreen(
    popUpToFirstScreen: () -> Unit,
    text: Message

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "back",
            modifier = Modifier
                .padding(
                    top = 40.dp,
                    bottom = 20.dp
                )
                .clickable {
                    popUpToFirstScreen()
                }
        )

        Image(
            modifier = Modifier
                .width(500.dp)
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.CenterHorizontally),
            painter = painterResource(text.imageResId),
            contentDescription = null,
            contentScale = ContentScale.FillWidth

        )

        Text(
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    bottom = 10.dp
                )
                .wrapContentSize(align = Alignment.TopStart),
            text = text.date,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier
                .padding(
                    bottom = 10.dp
                ),
            text = text.title,
            color = Black,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )



        Text(
            text = "Everyone wants to make the next great mobile app.\n" +
                    "It can be an extremely profitable way to make some money if you know what you're doing.\n" +
                    "\n" +
                    "If you've got a great mobile app idea and decided to consult with a developer or an app development company, you may have been surprised to hear how costly it is to outsource development.\n" +
                    "\n" +
                    "So that's when the thought hit you, I can just do learn to do this myself.",
            modifier = Modifier
                .padding(top = 8.dp)

        )
    }


}

