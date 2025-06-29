package ir.fatemelyasi.news.view.screens.allArticleScreen

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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ir.fatemelyasi.news.R
import ir.fatemelyasi.news.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.news.view.screens.dashboardScreen.ArticleItems
import ir.fatemelyasi.news.view.ui.theme.LocalCustomColors
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun AllArticlesScreen(
    viewModel: AllArticleScreenViewModel = koinViewModel(),
    navigateToSecondScreen: (ArticleViewEntity) -> Unit,
    deleteArticle: (ArticleViewEntity) -> Unit,
    popUpToFirstScreen: () -> Unit,
) {
    val colors = LocalCustomColors.current

    val articles = remember { mutableStateListOf<ArticleViewEntity>() }

    LaunchedEffect(Unit) {
        viewModel.articles.subscribe {
            articles.clear()
            articles.addAll(it)
        }.also(viewModel::addDisposable)
    }

    LaunchedEffect(Unit) {
        viewModel.fetchArticles()
    }

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
            items(items = articles.toList()) { article ->
                ArticleItems(
                    navigateToSecondScreen = { navigateToSecondScreen(article) },
                    onLongClick = { deleteArticle(article) },
                    messageItem = article
                )
            }
        }
    }
}