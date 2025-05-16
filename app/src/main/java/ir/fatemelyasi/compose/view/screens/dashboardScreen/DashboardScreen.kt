package ir.fatemelyasi.compose.view.screens.dashboardScreen

import android.R.attr.contentDescription
import android.R.attr.maxLines
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.internal.util.NotificationLite.disposable
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.compose.view.screens.articleDetailScreen.ArticleDetailScreenViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
internal fun DashboardScreen(
    viewModel: DashboardScreenViewModel = koinViewModel(),
    navigateToSecondScreen: (ArticleViewEntity) -> Unit,
    navigateToArticleScreen: () -> Unit,
) {
    val newsListState = remember { mutableStateListOf<ArticleViewEntity>() }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        viewModel.newsList.subscribe { list ->
            newsListState.clear()
            newsListState.addAll(list)
        }.also(viewModel::addDisposable)
    }
    LaunchedEffect(Unit) {
        viewModel.loading.subscribe {
            isLoading = it
        }.also(viewModel::addDisposable)
    }
    LaunchedEffect(Unit) {
        viewModel.error.subscribe {
            errorMessage = it.message
        }.also(viewModel::addDisposable)
    }
    LaunchedEffect(Unit) {
        viewModel.fetchNews()
    }

Surface(
modifier = Modifier.fillMaxSize(),
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

        SearchRow(query = query, onQueryChange = { query = it }, onSearchClick = {
            viewModel.searchNews(query)
        })

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

        if (newsListState.isNotEmpty()) {
            CardBanner(
                articleViewEntity = newsListState[0],
                navigateToSecondScreen = { navigateToSecondScreen(newsListState[0]) })
        }

        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .background(MaterialTheme.colorScheme.outline), thickness = 1.dp
        )

        Column(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            MoreArticle(
                navigateToArticleScreen = navigateToArticleScreen,
                navigateToSecondScreen = navigateToSecondScreen,
                items = newsListState,
            )
        }
    }
}
}

@Composable
fun SearchRow(
    query: String, onQueryChange: (String) -> Unit, onSearchClick: () -> Unit
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
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search",
            )
        }
    }
}

@Composable
fun CardBanner(
    articleViewEntity: ArticleViewEntity,
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
        AsyncImage(
            model = articleViewEntity.urlToImage ?: "https://media.wired.com/photos/680bc6b2b3938efbc5752612/191:100/w_1280,c_limit/chatgpt-shopping-gear-1355048636%20.jpg",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.CenterHorizontally),
            contentDescription = null,
            contentScale = ContentScale.Crop

        )
        Button(
            modifier = Modifier
                .size(
                    width = 100.dp, height = 50.dp
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
                text = articleViewEntity.title.toString(),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = articleViewEntity.publishedAt.toString(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

@Composable
fun MoreArticle(
    navigateToArticleScreen: () -> Unit,
    navigateToSecondScreen: (ArticleViewEntity) -> Unit,
    items: List<ArticleViewEntity>,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
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
                modifier = Modifier.wrapContentSize(align = Alignment.TopEnd),
                text = " More Articles",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,

                )

            Spacer(
                modifier = Modifier.weight(1f)
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
                }, messageItem = item
            )
        }
    }
}

@Composable
fun ArticleItems(
    navigateToSecondScreen: () -> Unit,
    messageItem: ArticleViewEntity,
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

    ) {
        AsyncImage(
            model = (messageItem.urlToImage),
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(0.5.dp, MaterialTheme.colorScheme.primary, RectangleShape),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = messageItem.title ?: "",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1
            )

            Text(
                modifier = Modifier.padding(
                    all = 4.dp
                ),
                text = messageItem.publishedAt ?: "",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1
            )
        }
    }
}