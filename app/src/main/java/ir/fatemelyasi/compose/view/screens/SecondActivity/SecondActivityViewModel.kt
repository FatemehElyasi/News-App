package ir.fatemelyasi.compose.view.screens.SecondActivity

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepository

class SecondActivityViewModel(
    private val newsRepository: NewsRepository,
): ViewModel() {

    fun getItemDetails(itemId: String): Observable<ArticleViewEntity> {
        return newsRepository.getNews(itemId)
    }
}