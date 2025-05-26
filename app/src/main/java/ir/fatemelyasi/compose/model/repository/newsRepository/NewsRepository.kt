package ir.fatemelyasi.compose.model.repository.newsRepository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity

interface NewsRepository {

    fun getNews(): Observable<List<ArticleViewEntity>>

    fun getNewsFromServer(): Single<List<ArticleViewEntity>>

    fun getNewsFromDb(): Observable<List<ArticleViewEntity>>

    fun saveNewsToDb(news: List<ArticleViewEntity>)

    fun searchNews(query: String): Observable<List<ArticleViewEntity>>

    fun deleteNews(news: List<ArticleViewEntity>)
}