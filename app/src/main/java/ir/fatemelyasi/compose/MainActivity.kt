package ir.fatemelyasi.compose

import Message
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.fatemelyasi.compose.ui.theme.ComposeTheme
import ir.fatemelyasi.compose.ui.theme.Gray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen()
                }

            }
        }
    }
}

@Composable
fun MainScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        var query by remember { mutableStateOf("") }


        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {

            Text(
                text = "Hi John ,",
                modifier = Modifier
                    .padding(top = 50.dp)
                    .padding(start = 20.dp)
                    .wrapContentSize(align = Alignment.TopStart),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            )
            Text(
                text = "Good Morning!",
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(top = 4.dp)
                    .wrapContentSize(align = Alignment.TopStart),
                style = TextStyle(
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            )


            SearchRow(
                query = query,
                onQueryChange = { query = it },
                onSearchClick = {
                    //whe clicked on search icon
                    println("Search: $query")
                }
            )

            Text(
                text = "Today's Articles",
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(top = 4.dp)
                    .wrapContentSize(align = Alignment.TopStart),
                style = TextStyle(
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
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
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                modifier = Modifier
                    .size(width = 200.dp, height = 35.dp)
                    .padding(start = 20.dp)
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
                Message(
                    "How To Get Started as a mobile app designer and get your first client",
                    "October,4,2024"
                )
            )


            Spacer(
                modifier = Modifier
                    .padding(all = 20.dp)
                    .width(400.dp) // or use .height(...) for vertical
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
                        .wrapContentSize(align = Alignment.TopEnd),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    )
                )
                Text(
                    " See All",
                    modifier = Modifier
                        .padding()
                        .wrapContentSize(align = Alignment.TopEnd),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
            ) {
                MessageCard(Message("Make a Successful Instagram ", "October,4,2024"))
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
                .border(0.5.dp, MaterialTheme.colorScheme.primary, RectangleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = text.title,
                color = MaterialTheme.colorScheme.secondary,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium) {
                Text(
                    text = text.date,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun MessageCardBanner(text: Message) {
    Column(modifier = Modifier.padding(all = 8.dp)) {

        Spacer(modifier = Modifier.width(8.dp))

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
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(shape = MaterialTheme.shapes.medium) {
                Text(
                    text = text.date,
                    modifier = Modifier,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizableSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    searchResults: List<String>,
    onResultClick: (String) -> Unit,
    // Customization options
    placeholder: @Composable () -> Unit = { Text("Search") },
    leadingIcon: @Composable (() -> Unit)? = {
        Icon(
            Icons.Default.Search,
            contentDescription = "Search"
        )
    },
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingContent: (@Composable (String) -> Unit)? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    // Track expanded state of search bar
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier
            .wrapContentSize()
            .padding(all = 20.dp)
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
            inputField = {
                // Customizable input field implementation
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = onQueryChange,
                    onSearch = {
                        onSearch(query)
                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = placeholder,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            // Show search results in a lazy column for better performance
            LazyColumn {
                items(count = searchResults.size) { index ->
                    val resultText = searchResults[index]
                    ListItem(
                        headlineContent = { Text(resultText) },
                        supportingContent = supportingContent?.let { { it(resultText) } },
                        leadingContent = leadingContent,
                        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer),
                        modifier = Modifier
                            .clickable {
                                onResultClick(resultText)
                                expanded = false
                            }
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MySearchScreen() {
    // وضعیت برای نگه‌داری متن سرچ
    var searchQuery by remember { mutableStateOf("") }

    // لیست فرضی نتایج
    val allItems = listOf("Kotlin", "Compose", "Android", "Jetpack", "Material3")
    val filteredResults = allItems.filter { it.contains(searchQuery, ignoreCase = true) }

    CustomizableSearchBar(
        query = searchQuery,
        onQueryChange = { searchQuery = it },
        onSearch = { /* اینجا می‌تونی مثلاً نتایج رو از سرور بیاری */ },
        searchResults = filteredResults,
        onResultClick = { selected ->
            println("You clicked on: $selected")
        },
        // می‌تونی placeholder و icon دلخواه هم بدی، ولی اینجا از پیش‌فرض استفاده کردیم
    )
}

@Composable
fun SecondScreen() {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Spacer(modifier = Modifier.height(10.dp))


        Icon(
            modifier = Modifier.padding(start = 20.dp).padding(top = 40.dp),
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "back"
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
        MessageCardBanner(
            Message(
                date = "October,4,2024",
                title = "How To Get Started as a mobile app designer and get your first client",
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
                .padding(all = 4.dp)
                .padding(start = 20.dp)
                .padding(end = 20.dp)

        )
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
            .padding(30.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                //.weight(0.5f)

                .padding(end = 4.dp),
            shape = RoundedCornerShape(10.dp),
            placeholder = { Text("Search") },
            singleLine = true
        )

        Button(
            onClick = onSearchClick,
            modifier = Modifier.size(50.dp), // اندازه دکمه مربع
            contentPadding = PaddingValues(0.dp), // حذف فاصله داخلی پیش‌فرض
            shape = RoundedCornerShape(8.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "جستجو"
            )
        }
    }
}
/*
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = "mainScreen"
    ) {
        composable("mainScreen") {
           MainScreen{
               navController.navigate("SeconScreen")
           }
        }
        composable("SecondScreen") {
            ir.fatemelyasi.compose.SecondScreen()
        }
    }

}
 */
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MainScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ComposeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            SecondScreen()
        }
    }
}
