package ir.fatemelyasi.compose.screens

import android.R.attr.top
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import ir.fatemelyasi.compose.data.MessageViewEntity

val messageViewEntities = listOf(
    MessageViewEntity(
        title = "How To Get Started as a mobile app designer and get your first client",
        date = "October,4,2024",
        imageResId = R.drawable.banner
    ),
    MessageViewEntity(
        title = "Make a Successful Instagram ",
        date = "October,4,2024",
        imageResId = R.drawable.bannerbody1
    ),
    MessageViewEntity(
        title = "Get Started in 3D Animation",
        date = "October,4,2024",
        imageResId = R.drawable.bannerbody2
    ),
    MessageViewEntity(
        title = "Get Started in 3D Animation",
        date = "October,4,2024",
        imageResId = R.drawable.banner
    )
)

@Composable
internal fun DashboardScreen(
    navigateToSecondScreen: (MessageViewEntity) -> Unit,
    navigateToArticleScreen: () -> Unit,
    messageViewEntity: ArrayList<MessageViewEntity>
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.surface,
    ) {
        var query by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 20.dp,
                        )
                        .wrapContentSize(align = Alignment.TopStart),
                    text = "Hi John ,",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleMedium

                )
                Text(
                    modifier = Modifier
                        .padding(
                            top = 4.dp
                        )
                        .wrapContentSize(align = Alignment.TopStart),
                    text = "Good Morning!",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.titleLarge,
                )
            }

            SearchRow(
                query = query,
                onQueryChange = { query = it },
                onSearchClick = {}
            )

            Text(
                text = "Today's Articles",
                modifier = Modifier
                    .padding(
                        bottom = 10.dp
                    )
                    .wrapContentSize(align = Alignment.TopStart),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge,
            )

            CardBanner(
                text = messageViewEntities[0],
                navigateToSecondScreen = { navigateToSecondScreen(messageViewEntities[0]) }
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .background(MaterialTheme.colorScheme.outline),
                thickness = 1.dp
            )

            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
            ) {
                MoreArticle(
                    navigateToArticleScreen = navigateToArticleScreen,
                    navigateToSecondScreen = navigateToSecondScreen,
                    items = messageViewEntity,
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
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor =  MaterialTheme.colorScheme.onSecondary
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
    text: MessageViewEntity,
    navigateToSecondScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                bottom = 8.dp,
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable { navigateToSecondScreen() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.CenterHorizontally),
            painter = painterResource(text.imageResId),
            contentDescription = null,
            contentScale = ContentScale.Crop

        )
        Button(
            modifier = Modifier
                .size(
                    width = 100.dp,
                    height = 50.dp
                )
                .padding(vertical = 8.dp)
                .wrapContentSize(align = Alignment.TopStart),
            onClick = { },
            shape = (RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSurface,
            ),
            contentPadding = PaddingValues(horizontal = 25.dp)
        ) {
            Text(
                text = "Design",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondary,
                fontWeight = FontWeight.Bold,
                maxLines = 150
            )

        }

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = text.title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = text.date,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

@Composable
fun MoreArticle(
    navigateToArticleScreen: () -> Unit,
    navigateToSecondScreen: (MessageViewEntity) -> Unit,
    items: List<MessageViewEntity>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 8.dp
                )
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize(align = Alignment.TopEnd),
                text = " More Articles",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,

            )

            Spacer(
                modifier = Modifier
                    .weight(1f)
            )

            Text(
                text = " See All",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .wrapContentSize(align = Alignment.TopEnd)
                    .clickable { navigateToArticleScreen() },
                style = MaterialTheme.typography.titleSmall
            )
        }

        items.forEach { item ->
            ArticleItems(
                navigateToSecondScreen = {
                    navigateToSecondScreen(item)
                },
                messageItem = item
            )
        }
    }
}

@Composable
fun ArticleItems(
    navigateToSecondScreen: () -> Unit,
    messageItem: MessageViewEntity,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                navigateToSecondScreen()
            },
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically

    )
    {
        Image(
            painter = painterResource(messageItem.imageResId),
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
                text = messageItem.title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1
            )

            Text(
                modifier = Modifier.padding(
                    all = 4.dp
                ),
                text = messageItem.date,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1
            )
        }
    }
}