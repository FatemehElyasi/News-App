package ir.fatemelyasi.compose.screens

import Message
import android.R.id.message
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import ir.fatemelyasi.compose.R
import ir.fatemelyasi.compose.ui.theme.ComposeTheme
import ir.fatemelyasi.compose.utils.MyScreens

@Composable
fun SecondScreen(
    navController: NavHostController,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "back",
            modifier = Modifier
                .padding(start = 20.dp, top = 40.dp)
                .clickable {
                    navController.popBackStack(MyScreens.FirstScreen.route, false)
                }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = null,
            modifier = Modifier
                .padding(20.dp)
                .height(200.dp)
                .width(500.dp)
                .clip(RoundedCornerShape(16.dp)) // <- rounded corners here
                .clip(RectangleShape)
                .border(0.5.dp, MaterialTheme.colorScheme.primary, RectangleShape),
            contentScale = ContentScale.Crop
        )


        MessageCardBanner(messages[0], navController)

        Text(
            text = "Everyone wants to make the next great mobile app.\n" +
                    "It can be an extremely profitable way to make some money if you know what you're doing.\n" +
                    "\n" +
                    "If you've got a great mobile app idea and decided to consult with a developer or an app development company, you may have been surprised to hear how costly it is to outsource development.\n" +
                    "\n" +
                    "So that's when the thought hit you, I can just do learn to do this myself.",
            modifier = Modifier
                .padding(all = 4.dp)
                .padding(start = 20.dp)
                .padding(end = 20.dp)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ComposeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            //SecondScreen()
        }
    }
}


