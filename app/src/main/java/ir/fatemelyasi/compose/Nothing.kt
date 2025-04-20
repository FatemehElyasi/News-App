package ir.fatemelyasi.compose


/*
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


        Column {
            var query by remember { mutableStateOf("") }

            SearchRow(
                query = query,
                onQueryChange = { query = it },
                onSearchClick = {
                    // کاری که با کلیک روی آیکون جستجو می‌خوای انجام بدی
                    println("Search: $query")
                }
            )

        }
    }
//---------------------
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


//-------------
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
//------------------------
@Composable
fun MySearchScreen() {
    // وضعیت برای نگه‌داری متن سرچ
    var searchQuery by remember { mutableStateOf("") }

    // لیست فرضی نتایج
    val allItems = listOf("Kotlin", "Compose", "Android", "Jetpack", "Material3")
    val filteredResults = allItems.filter { it.contains(searchQuery, ignoreCase = true) }


 */
