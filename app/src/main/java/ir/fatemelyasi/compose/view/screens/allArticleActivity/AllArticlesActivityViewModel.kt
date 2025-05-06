package ir.fatemelyasi.compose.view.screens.allArticleActivity

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepository

class AllArticlesActivityViewModel(
    private val newsRepository: NewsRepository,
): ViewModel() {

    fun getNews(): Single<List<ArticleViewEntity>> {
        return newsRepository.getNewsFromServer()
    }
}