package ir.fatemelyasi.news.model.repository.newsRepository

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ir.fatemelyasi.news.model.dataSources.local.newsLocalDataSource.NewsLocalDataSource
import ir.fatemelyasi.news.model.dataSources.remote.NewsRemoteDataSource
import ir.fatemelyasi.news.model.local.NewsEntity
import ir.fatemelyasi.news.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.news.view.utils.mappers.toViewEntity


@org.koin.core.annotation.Single
class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource,
) : NewsRepository {

    override fun getNews(): Observable<List<ArticleViewEntity>> {
        return Observable.merge(
            getNewsFromDb()
                .subscribeOn(Schedulers.io()),
            getNewsFromServer()
                .subscribeOn(Schedulers.io())
                .doOnSuccess { saveNewsToDb(it) }
                .toObservable()
        )
    }
    //--------------server
    override fun getNewsFromServer(): Single<List<ArticleViewEntity>> {
        return newsRemoteDataSource.getNewsInformation()
            .subscribeOn(Schedulers.io())
            .map { response ->
                Log.d("API_RESPONSE", "Raw response: $response")
                Log.d("API_RESPONSE", "Articles count: ${response.articles?.size}")

                response.articles.orEmpty()
                    .filterNotNull()
                    .filter { !it.urlToImage.isNullOrBlank() && !it.title.isNullOrBlank() }
                    .map { article ->
                        Log.d("ARTICLE_ITEM", "Mapped Article: ${article.title}")
                        article.toViewEntity()
                    }
            }
    }
    //--------------db
    override fun getNewsFromDb(): Observable<List<ArticleViewEntity>> {
        return newsLocalDataSource.getAllNews()
            .subscribeOn(Schedulers.io())
            .filter { it.isNotEmpty() }
            .map { newsEntityList: List<NewsEntity> ->
                Log.d("NewsRepository", "DB News Count: ${newsEntityList.size}")
                newsEntityList.map {
                    Log.d("NewsRepository", "DB Article: ${it.title}")
                    it.toViewEntity()
                }
                    .filter { it.urlToImage.isNotBlank() }
            }
    }
    override fun saveNewsToDb(news: List<ArticleViewEntity>) {
        val entities = news.map { it.toViewEntity() }
        newsLocalDataSource.saveNewsToDb(entities)
    }
    override fun searchNews(query: String): Observable<List<ArticleViewEntity>> {
        return newsLocalDataSource.searchNews(query)
            .subscribeOn(Schedulers.io())
            .map { it.map { entity -> entity.toViewEntity() } }
            .observeOn(AndroidSchedulers.mainThread())
    }
    override fun deleteNews(news: List<ArticleViewEntity>) {
        val entities = news.map { it.toViewEntity() }
        newsLocalDataSource.deleteNews(entities)
    }


}

