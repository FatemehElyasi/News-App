package ir.fatemelyasi.compose.screens

import Message
import android.R.attr.fontWeight
import android.R.attr.singleLine
import android.R.attr.text
import android.graphics.Color
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ir.fatemelyasi.compose.R
import ir.fatemelyasi.compose.ui.theme.Black
import ir.fatemelyasi.compose.ui.theme.Gray
import ir.fatemelyasi.compose.ui.theme.PurpleGrey40
import ir.fatemelyasi.compose.ui.theme.White
import ir.fatemelyasi.compose.utils.MyScreens

val messages = listOf(
    Message(
        "How To Get Started as a mobile app designer and get your first client",
        "October,4,2024"
    ),
    Message("Make a Successful Instagram ", "October,4,2024"),
    Message("Get Started in 3D Animation", "October,4,2024"),
    Message("Get Started in 3D Animation", "October,4,2024")
)

@Composable
fun FirstScreen(
    navController: NavHostController,
    message: Message
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        var query by remember { mutableStateOf("") }


        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "Hi John ,",
                color = Black,
                modifier = Modifier
                    .padding(top = 50.dp)
                    .padding(start = 20.dp)
                    .wrapContentSize(align = Alignment.TopStart)
                    .clickable {
                        navController.navigate(MyScreens.SecondScreen.route, null)
                    },
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "Good Morning!",
                color = Black,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(top = 4.dp)
                    .wrapContentSize(align = Alignment.TopStart)
                    .clickable {
                        navController.navigate(MyScreens.SecondScreen.route, null)
                    },
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold

                )

            )


            SearchRow(
                query = query,
                onQueryChange = { query = it },
                onSearchClick = {
                    println("Search: $query")
                    navController.navigate(MyScreens.SecondScreen.route, null)
                }
            )

            Text(
                text = "Today's Articles",
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(top = 4.dp)
                    .wrapContentSize(align = Alignment.TopStart)
                    .clickable {
                        navController.navigate(MyScreens.SecondScreen.route, null)
                    },
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Black,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(R.drawable.banner),
                contentDescription = null,
                modifier = Modifier
                    .padding(20.dp)
                    .height(200.dp)
                    .width(500.dp)
                    .clip(RoundedCornerShape(16.dp)) // <- rounded corners here
                    .clip(RectangleShape)
                    .align(Alignment.CenterHorizontally)
                    .border(0.5.dp, MaterialTheme.colorScheme.primary, RectangleShape),
                contentScale = ContentScale.Crop
            )

            Button(
                onClick = {
                    navController.navigate(MyScreens.SecondScreen.route, null)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Gray,
                    contentColor = Black
                ),
                modifier = Modifier
                    .size(width = 200.dp, height = 40.dp)
                    .padding(start = 20.dp)
                    .padding(bottom = 8.dp)
                    .wrapContentSize(align = Alignment.TopStart),
                shape = (RoundedCornerShape(8.dp)),
                contentPadding = PaddingValues(start = 30.dp, end = 30.dp)
            ) {
                Text(
                    text = "Design",
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 100
                )
            }

            MessageCardBanner(
                messages[0],
                navController
            )

            Spacer(
                modifier = Modifier
                    .padding(all = 20.dp)
                    .width(400.dp)
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp)
                    .padding(start = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(200.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    " More Articles",
                    modifier = Modifier
                        .padding()
                        .wrapContentSize(align = Alignment.TopEnd)
                        .clickable {
                            navController.navigate(MyScreens.SecondScreen.route, null)
                        },
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold
                    ),


                    )
                Text(
                    " See All",
                    modifier = Modifier
                        .padding()
                        .wrapContentSize(align = Alignment.TopEnd)
                        .clickable {
                            val titleBannerText = Uri.encode(message.title)
                            val dateBannerText = Uri.encode(message.date)
                            navController.navigate("${MyScreens.ArticleActivity.route}/$titleBannerText/$dateBannerText")
                        },
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
            ) {
                MessageCard(Message("Make a Successful Instagram ", "October,4,2024"))
                MessageCard(Message("Get Started in 3D Animation", "October,4,2024"))
                MessageCard(Message("Get Started in 3D Animation", "October,4,2024"))

            }
        }

    }

}

@Composable
fun MessageCard(text: Message) {

    Row(
        modifier = Modifier
            .padding(all = 8.dp)
            .padding(start = 20.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.bannerbody2),
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(0.5.dp, MaterialTheme.colorScheme.primary, RectangleShape)
                .clickable {
                    //navController.navigate(MyScreens.SecondScreen.route, null)
                },
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = text.title,
                color = Black,
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.clickable {
                },

                )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium) {
                Text(
                    text = text.date,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
fun MessageCardBanner(text: Message, navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(all = 8.dp)
            .clickable {
                // val titleBannerText = Uri.encode(text.title)
                // val dateBannerText = Uri.encode(text.date)
                navController.navigate(
                    MyScreens.SecondScreen.route
                    //+  "/$titleBannerText/$dateBannerText"
                )
            }
    ) {
        Column(
            modifier = Modifier
                .padding(start = 18.dp)
                .padding(end = 20.dp)
        ) {
            Text(
                text = text.title,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium) {
                Text(
                    text = text.date,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun SearchRow(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .weight(2.3f)
                .padding(end = 4.dp)
            //.shadow(4.dp, shape = RoundedCornerShape(0.5.dp)),
            , shape = RoundedCornerShape(10.dp),
            /*
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )  */
            placeholder = { Text("Search") },
            singleLine = true
        )



        Button(
            onClick = onSearchClick,
            modifier = Modifier.size(50.dp),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(8.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Black, contentColor = White
            )
        )
        {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search",
            )
        }
    }
}

