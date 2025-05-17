package ir.fatemelyasi.compose.model.dataSources.local

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ir.fatemelyasi.compose.model.local.NewsEntity
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity

interface NewsLocalDataSource {

    fun saveNewsToDb(newsEntityModel: List<NewsEntity>)

    fun deleteNews(newsEntityModel: List<NewsEntity>)

    fun getAllNews(): Observable<List<NewsEntity>>

    fun getTopNews(count: Int): Observable<List<NewsEntity>>

    fun searchNews(query: String): Observable<List<NewsEntity>>

}