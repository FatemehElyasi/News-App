package ir.fatemelyasi.news.view.screens.articleDetailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ir.fatemelyasi.news.R
import ir.fatemelyasi.news.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.news.view.ui.theme.LocalCustomColors
import ir.fatemelyasi.news.view.ui.theme.LocalCustomTypography
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ArticleDetailScreen(
    viewModel: ArticleDetailScreenViewModel = koinViewModel(),
    popBackStack: () -> Unit,
    text: ArticleViewEntity
) {
    //send data to viewModel
    LaunchedEffect(Unit) {
        viewModel.setArticle(text)
    }
    val colors = LocalCustomColors.current
    val typography = LocalCustomTypography.current

    val articleState = remember { mutableStateOf<ArticleViewEntity?>(null) }

    LaunchedEffect(Unit) {
        viewModel.articleObservable
            .subscribe { article ->
                articleState.value = article
            }.also { viewModel.addDisposable(it) }
    }


    articleState.value?.let { article ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(20.dp)
                .background(colors.surface)
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "back",
                modifier = Modifier
                    .padding(top = 40.dp, bottom = 20.dp)
                    .clickable { popBackStack() }
            )

            AsyncImage(
                model = article.urlToImage,
                modifier = Modifier
                    .width(500.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterHorizontally),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            BasicText(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp),
                text = article.publishedAt.orEmpty(),
                style = typography.titleSmall.copy(colors.onSurfaceVariant)
            )

            BasicText(
                modifier = Modifier.padding(bottom = 10.dp),
                text = article.title,
                style = typography.displaySmall.copy(colors.onPrimary)
            )

            BasicText(
                text = article.description
                    ?: "There is No description.",
                modifier = Modifier.padding(top = 8.dp),
                style = typography.titleSmall.copy(colors.onSurfaceVariant)
            )
        }
    }
}



