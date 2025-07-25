package ir.fatemelyasi.news.view.screens.dashboardScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ir.fatemelyasi.news.R
import ir.fatemelyasi.news.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.news.view.components.OfflineErrorComponent
import ir.fatemelyasi.news.view.ui.theme.LocalCustomColors
import ir.fatemelyasi.news.view.ui.theme.LocalCustomTypography
import ir.fatemelyasi.news.view.utils.stateHandling.ErrorState
import org.koin.compose.viewmodel.koinViewModel
import java.util.Collections.emptyList

@Composable
internal fun DashboardScreen(
    viewModel: DashboardScreenViewModel = koinViewModel(),
    navigateToSecondScreen: (ArticleViewEntity) -> Unit,
    navigateToArticleScreen: () -> Unit,
    navigateToAuthenticationScreen: () -> Unit,
) {
    val newsListState by viewModel.newsList.subscribeAsState(initial = emptyList())
    val loadingState by viewModel.loading.subscribeAsState(initial = false)
    val errorState by viewModel.error.subscribeAsState(initial = ErrorState.None)
    val query by viewModel.query.subscribeAsState(initial = "")

    val colors = LocalCustomColors.current
    val typography = LocalCustomTypography.current

    LaunchedEffect(Unit) {
        viewModel.searchNews()
    }

    LaunchedEffect(Unit) {
        viewModel.fetchNewsItems()
    }

    LaunchedEffect(Unit) {
        viewModel.checkUserLoggedIn()
    }

    when {
        loadingState && newsListState.isEmpty() -> {
            LoadingIndicator()
        }

        errorState is ErrorState.Error -> {
            OfflineErrorComponent(
                isLoading = loadingState,
                onRetry = { viewModel.fetchNewsItems() }
            )
        }

        else -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.surface)
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    AnimatedVisibility(visible = query.isBlank()) {
                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = stringResource(R.string.hi_name),
                                    style = typography.titleMedium.copy(colors.onPrimary)
                                )
                                Text(
                                    modifier = Modifier.padding(top = 4.dp),
                                    text = stringResource(R.string.good_morning),
                                    style = typography.titleLarge.copy(colors.onPrimary)
                                )
                            }

                            Icon(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable {
                                        viewModel.loggedOut()
                                        navigateToAuthenticationScreen()
                                    },
                                imageVector = Icons.Default.Settings,
                                contentDescription = stringResource(R.string.news),
                                tint = colors.onPrimary
                            )
                        }
                    }

                    SearchRow(
                        query = query,
                        onQueryChange = { viewModel.updateQuery(it) },
                        onSearchClick = {}
                    )

                    if (query.isBlank()) {
                        Text(
                            text = stringResource(R.string.today_articles),
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .wrapContentSize(align = Alignment.TopStart),
                            style = typography.titleLarge.copy(colors.onPrimary)
                        )

                        if (newsListState.isNotEmpty()) {
                            CardBanner(
                                articleViewEntity = newsListState[0],
                                navigateToSecondScreen = { navigateToSecondScreen(newsListState[0]) }
                            )
                        }
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        thickness = 1.dp,
                        color = colors.outline
                    )
                    MoreArticle(
                        navigateToArticleScreen = navigateToArticleScreen,
                        navigateToSecondScreen = navigateToSecondScreen,
                        items = newsListState,
                        onLongClick = { article -> viewModel.deleteArticle(article) }
                    )
                }
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
    val colors = LocalCustomColors.current

    Row(
        modifier = Modifier
            .padding(
                vertical = 20.dp
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            singleLine = true,
            modifier = Modifier
                .weight(2f)
                .padding(end = 4.dp)
                .border(1.dp, colors.outline, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 16.dp, vertical = 18.dp),
            textStyle = TextStyle(
                color = colors.onBackground,
                fontSize = 16.sp
            ),
            decorationBox = { innerTextField ->
                if (query.isEmpty()) {
                    BasicText(
                        text = stringResource(R.string.search),
                        style = TextStyle(
                            colors.outline,
                            fontSize = 16.sp
                        )
                    )
                }
                innerTextField()
            }
        )
        Box(
            modifier = Modifier
                .size(55.dp)
                .background(color = colors.secondary, shape = RoundedCornerShape(8.dp))
                .clickable { onSearchClick() },
            contentAlignment = Alignment.Center
        )
        {
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = stringResource(R.string.search),
            )
        }
    }
}

@Composable
fun CardBanner(
    articleViewEntity: ArticleViewEntity,
    navigateToSecondScreen: () -> Unit
) {
    val colors = LocalCustomColors.current
    val typography = LocalCustomTypography.current

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
            model = articleViewEntity.urlToImage,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.CenterHorizontally),
            contentDescription = null,
            contentScale = ContentScale.Crop

        )
        Box(
            modifier = Modifier
                .size(width = 100.dp, height = 50.dp)
                .padding(vertical = 8.dp)
                .wrapContentSize(align = Alignment.TopStart)
                .clip(RoundedCornerShape(8.dp))
                .background(colors.secondary)
                .clickable { navigateToSecondScreen() },
            contentAlignment = Alignment.Center

        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp),
                text = stringResource(R.string.detail),
                style = typography.titleSmall.copy(
                    colors.onSecondary,
                    fontWeight = FontWeight.Bold,
                )
            )

        }

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = articleViewEntity.title.toString(),
                style = typography.displaySmall.copy(
                    colors.onPrimary,
                ),
            )
            Text(
                text = articleViewEntity.publishedAt.toString(),
                style = typography.titleSmall.copy(
                    colors.onSurfaceVariant,
                )
            )
        }
    }
}

@Composable
fun MoreArticle(
    navigateToArticleScreen: () -> Unit,
    navigateToSecondScreen: (ArticleViewEntity) -> Unit,
    onLongClick: (ArticleViewEntity) -> Unit,
    items: List<ArticleViewEntity>,
) {
    val colors = LocalCustomColors.current
    val typography = LocalCustomTypography.current

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
                text = stringResource(R.string.more_articles),
                style = typography.titleLarge.copy(
                    colors.onPrimary,
                )
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )

            Text(
                text = stringResource(R.string.see_all),
                modifier = Modifier
                    .wrapContentSize(align = Alignment.TopEnd)
                    .clickable { navigateToArticleScreen() },
                style = typography.titleSmall.copy(
                    colors.onSurfaceVariant,
                )
            )
        }

        items.take(4).forEach { item ->
            ArticleItems(
                navigateToSecondScreen = { navigateToSecondScreen(item) },
                messageItem = item,
                onLongClick = { onLongClick(item) }

            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArticleItems(
    navigateToSecondScreen: () -> Unit,
    messageItem: ArticleViewEntity,
    onLongClick: () -> Unit
) {
    val colors = LocalCustomColors.current
    val typography = LocalCustomTypography.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .combinedClickable(
                onClick = navigateToSecondScreen,
                onLongClick = onLongClick
            ),
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
                .border(
                    width = 0.5.dp,
                    color = colors.primary,
                    shape = RectangleShape
                ),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = messageItem.title,
                style = typography.titleMedium.copy(
                    colors.onPrimary,
                ),
                maxLines = 1
            )

            Text(
                modifier = Modifier.padding(
                    all = 4.dp
                ),
                text = messageItem.publishedAt ?: "",
                style = typography.titleSmall.copy(
                    colors.onSurfaceVariant,
                ),
                maxLines = 1
            )
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

