package ir.fatemelyasi.news.view.screens.allArticleScreen

import OfflineErrorComponent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ir.fatemelyasi.news.R
import ir.fatemelyasi.news.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.news.view.screens.dashboardScreen.ArticleItems
import ir.fatemelyasi.news.view.screens.dashboardScreen.LoadingIndicator
import ir.fatemelyasi.news.view.ui.theme.LocalCustomColors
import ir.fatemelyasi.news.view.utils.stateHandling.ErrorState
import org.koin.compose.viewmodel.koinViewModel
import java.util.Collections


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AllArticlesScreen(
    viewModel: AllArticleScreenViewModel = koinViewModel(),
    navigateToSecondScreen: (ArticleViewEntity) -> Unit,
    deleteArticle: (ArticleViewEntity) -> Unit,
    popUpToFirstScreen: () -> Unit,
) {
    val colors = LocalCustomColors.current

    val articles by viewModel.articles.subscribeAsState(initial = Collections.emptyList())
    val loadingState by viewModel.loading.subscribeAsState(initial = false)
    val errorState by viewModel.error.subscribeAsState(initial = ErrorState.None)

    LaunchedEffect(Unit) {
        viewModel.fetchArticles()
    }
    if (loadingState && articles.isEmpty()) {
        LoadingIndicator()
    } else if (errorState != ErrorState.None && articles.isEmpty()) {
        OfflineErrorComponent(
            isLoading = loadingState,
            onRetry = { viewModel.fetchArticles() })
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.surface)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "back",
                colorFilter = ColorFilter.tint(Color.Black),
                modifier = Modifier
                    .padding(top = 60.dp, start = 20.dp, bottom = 10.dp)
                    .clickable { popUpToFirstScreen() }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                items(items = articles.toMutableList()) { article ->
                    ArticleItems(
                        navigateToSecondScreen = { navigateToSecondScreen(article) },
                        onLongClick = { deleteArticle(article) },
                        messageItem = article
                    )
                }
            }
        }
    }
}