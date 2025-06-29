package ir.fatemelyasi.news.model.dataSources.local

import io.reactivex.rxjava3.core.Observable
import ir.fatemelyasi.news.model.local.NewsEntity

interface NewsLocalDataSource {

    fun saveNewsToDb(newsEntityModel: List<NewsEntity>)

    fun deleteNews(newsEntityModel: List<NewsEntity>)

    fun getAllNews(): Observable<List<NewsEntity>>

    fun getTopNews(count: Int): Observable<List<NewsEntity>>

    fun searchNews(query: String): Observable<List<NewsEntity>>

}