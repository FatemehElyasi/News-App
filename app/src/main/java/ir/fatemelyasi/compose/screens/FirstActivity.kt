package ir.fatemelyasi.compose.screens

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.fatemelyasi.compose.R
import ir.fatemelyasi.compose.data.Message
import ir.fatemelyasi.compose.ui.theme.Black
import ir.fatemelyasi.compose.ui.theme.Gray
import ir.fatemelyasi.compose.ui.theme.White

val messages = listOf(
    Message(
        title = "How To Get Started as a mobile app designer and get your first client",
        date = "October,4,2024",
        imageResId = R.drawable.banner
    ),
    Message(
        title = "Make a Successful Instagram ",
        date = "October,4,2024",
        imageResId = R.drawable.bannerbody1
    ),
    Message(
        title = "Get Started in 3D Animation",
        date = "October,4,2024",
        imageResId = R.drawable.bannerbody2
    ),
    Message(
        title = "Get Started in 3D Animation",
        date = "October,4,2024",
        imageResId = R.drawable.banner
    )
)

@Composable
internal fun FirstScreen(
    navigateToSecondScreen: () -> Unit,
    navigateToArticleScreen: () -> Unit,
    message: ArrayList<Message>
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        var query by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Column {
                Text(
                    text = "Hi John ,",
                    color = Black,
                    modifier = Modifier
                        .padding(
                            top = 20.dp,
                        )
                        .wrapContentSize(align = Alignment.TopStart),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Good Morning!",
                    color = Black,
                    modifier = Modifier
                        .padding(
                            top = 4.dp
                        )
                        .wrapContentSize(align = Alignment.TopStart),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold

                    )

                )
            }

            SearchRow(
                query = query,
                onQueryChange = { query = it },
                onSearchClick = {
                    println("Search: $query")
                }
            )

            CardBanner(
                text = messages[0],
                navigateToSecondScreen = navigateToSecondScreen
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )

            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
            ) {
                MoreArticle(
                    navigateToArticleScreen = navigateToArticleScreen,
                    navigateToSecondScreen = navigateToSecondScreen,
                    text = message[0]
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
            .padding(
                vertical = 20.dp
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .weight(2f)
                .padding(
                    end = 4.dp
                ),
            shape = RoundedCornerShape(10.dp),

            placeholder = { Text("Search") },
            singleLine = true
        )



        Button(
            modifier = Modifier.size(55.dp),
            onClick = onSearchClick,
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

@Composable
fun CardBanner(
    text: Message,
    navigateToSecondScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = 8.dp,
                bottom = 8.dp,
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Today's Articles",
            modifier = Modifier
                .padding(
                    bottom = 16.dp
                )

                .wrapContentSize(align = Alignment.TopStart)
                .clickable {
                    navigateToSecondScreen()
                },
            style = TextStyle(
                fontSize = 20.sp,
                color = Black,
                fontWeight = FontWeight.Bold
            )
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
        Button(
            modifier = Modifier
                .size(
                    width = 100.dp,
                    height = 50.dp
                )
                .padding(vertical = 8.dp)
                .wrapContentSize(align = Alignment.TopStart),
            onClick = {
                navigateToSecondScreen()
            },
            shape = (RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Gray,
                contentColor = Black
            ),
            contentPadding = PaddingValues(horizontal = 25.dp)
        ) {
            Text(
                text = "Design",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 150
            )

        }

        Column(

            modifier = Modifier
                .clickable {
                    navigateToSecondScreen()
                },
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start,

            )
        {
            Text(
                text = text.title,
                color = Black,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = text.date,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun MoreArticle(
    navigateToArticleScreen: () -> Unit,
    navigateToSecondScreen: () -> Unit,
    text: Message,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            modifier = Modifier
                .padding(
                    bottom = 8.dp
                )
        ) {
            Text(
                text = " More Articles",
                modifier = Modifier
                    .wrapContentSize(align = Alignment.TopEnd),
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Black,
                    fontWeight = FontWeight.Bold
                ),
            )

            Spacer(
                modifier = Modifier
                    .weight(1f)
            )

            Text(
                text = " See All",
                modifier = Modifier
                    .wrapContentSize(align = Alignment.TopEnd)
                    .clickable {
                        navigateToArticleScreen()
                    },
                style = MaterialTheme.typography.bodySmall
            )
        }

        messages.forEach { message ->
            ArticleItems(
                navigateToSecondScreen = {
                    navigateToSecondScreen()
                }, text = message
            )
        }

    }
}


@Composable
fun ArticleItems(
    navigateToSecondScreen: () -> Unit,
    text: Message,

    ) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clickable {
                navigateToSecondScreen()
            },

        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically

    )
    {
        Image(
            painter = painterResource(text.imageResId),
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(0.5.dp, MaterialTheme.colorScheme.primary, RectangleShape),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = text.title,
                color = Black,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1
            )

            Text(
                text = text.date,
                modifier = Modifier.padding(
                    all = 4.dp
                ),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1
            )
        }
    }
}