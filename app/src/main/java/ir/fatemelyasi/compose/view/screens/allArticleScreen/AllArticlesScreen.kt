package ir.fatemelyasi.compose.view.screens.allArticleScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.compose.view.screens.dashboardScreen.ArticleItems
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AllArticlesScreen(
    viewModel: AllArticleScreenViewModel = koinViewModel(),
    navigateToSecondScreen: (ArticleViewEntity) -> Unit,
    popUpToFirstScreen: () -> Unit,
) {
    val articles = remember { mutableStateListOf<ArticleViewEntity>() }

    LaunchedEffect(Unit) {
        viewModel.articles.subscribe {
            articles.clear()
            articles.addAll(it)
        }.also { viewModel::addDisposable }
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(20.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "back",
            modifier = Modifier
                .padding(
                    top = 40.dp
                )
                .clickable {
                    popUpToFirstScreen()
                })
        Spacer(modifier = Modifier.height(10.dp))

        articles.forEach { article ->
            ArticleItems(
                navigateToSecondScreen = { navigateToSecondScreen(article) },
                messageItem = article
            )
        }

    }

}