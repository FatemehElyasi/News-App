package ir.fatemelyasi.compose.view.screens.articleDetailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity
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
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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

            Text(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp),
                text = article.publishedAt.orEmpty(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
            )

            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = article.title.orEmpty(),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displaySmall
            )

            Text(
                text = article.description
                    ?: "There is No description.",
                modifier = Modifier.padding(top = 8.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}



