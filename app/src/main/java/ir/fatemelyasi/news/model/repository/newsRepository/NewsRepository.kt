package ir.fatemelyasi.news.model.repository.newsRepository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ir.fatemelyasi.news.model.viewEntity.ArticleViewEntity

interface NewsRepository {

    fun getNews(): Observable<List<ArticleViewEntity>>
    fun getNewsFromServer(): Single<List<ArticleViewEntity>>
    fun getNewsFromDb(): Observable<List<ArticleViewEntity>>
    fun saveNewsToDb(news: List<ArticleViewEntity>)
    fun searchNews(query: String): Observable<List<ArticleViewEntity>>
    fun deleteNews(news: List<ArticleViewEntity>)

    fun signUp(email: String, password: String, isLoggedIn: Boolean)
    fun getEmail(): String?
    fun getPassword(): String?
    fun isLoggedIn(): Boolean
    fun clearInformation()
}