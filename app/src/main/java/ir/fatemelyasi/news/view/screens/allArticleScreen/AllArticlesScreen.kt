package ir.fatemelyasi.news.view.screens.allArticleScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ir.fatemelyasi.news.R
import ir.fatemelyasi.news.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.news.view.screens.dashboardScreen.ArticleItems
import ir.fatemelyasi.news.view.ui.theme.LocalCustomColors
import ir.fatemelyasi.news.view.utils.SortOrder
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
        NewsTopBar(
            onSortAscClick = { viewModel.sortArticles(SortOrder.ASCENDING) },
            onSortDescClick = { viewModel.sortArticles(SortOrder.DESCENDING) },
            onBackClick = { popUpToFirstScreen() },
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

@Composable
fun NewsTopBar(
    onBackClick: () -> Unit,
    onSortAscClick: () -> Unit,
    onSortDescClick: () -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    val colors = LocalCustomColors.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 20.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(
            onClick = onBackClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                tint = (colors.onPrimary)

            )
        }

        // Menu (sort)
        Box(
            modifier = Modifier.zIndex(2f)
        ) {
            IconButton(
                onClick = { expanded = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = "More options",
                    tint = (colors.onPrimary)

                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                Modifier
                    .background(
                        colors.surface
                    )
                    .border(1.dp, colors.outline)
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            "Sort by Ascending",
                            color = colors.onPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    onClick = {
                        expanded = false
                        onSortAscClick()
                    }

                )
                DropdownMenuItem(
                    text = {
                        Text(
                            "Sort by Descending",
                            color = colors.onPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    },

                    onClick = {
                        expanded = false
                        onSortDescClick()
                    }
                )
            }
        }
    }
}



