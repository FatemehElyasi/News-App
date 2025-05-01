package ir.fatemelyasi.compose.model.repository.newsRepository

import io.reactivex.rxjava3.core.Observable
import ir.fatemelyasi.compose.model.ViewEntity.ArticleViewEntity

interface NewsRepository {
    fun getNews(): Observable<List<ArticleViewEntity>>
}