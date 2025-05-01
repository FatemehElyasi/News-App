package ir.fatemelyasi.compose.view.screens.allArticleActivity

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import ir.fatemelyasi.compose.model.ViewEntity.ArticleViewEntity
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepository

class AllArticlesActivityViewModel(
    private val newsRepository: NewsRepository,
): ViewModel() {

    fun getNews(): Observable<List<ArticleViewEntity>> {
        return newsRepository.getNews()
    }
}