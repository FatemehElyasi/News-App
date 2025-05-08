package ir.fatemelyasi.compose.model.repository.newsRepository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ir.fatemelyasi.compose.model.dataSources.local.NewsLocalDataSource
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.compose.model.dataSources.remote.NewsRemoteDataSource
import ir.fatemelyasi.compose.view.utils.mappers.toEntity
import ir.fatemelyasi.compose.view.utils.mappers.toView

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource

) : NewsRepository {

    override fun getNews(): Observable<List<ArticleViewEntity>> {
        return Observable.concatArray(
            getNewsFromDb(),
            getNewsFromServer()
                .doOnSuccess { saveNewsToDb(it) }
                .toObservable()
        )
    }

    //--------------server
    override fun getNewsFromServer(): Single<List<ArticleViewEntity>> {
        return newsRemoteDataSource.getNewsInformation()
            .map { response ->
                response.articles.orEmpty()
                    .filterNotNull()
                    .map { article ->
                        ArticleViewEntity(
                            title = article.title,
                            publishedAt = article.publishedAt,
                            urlToImage = article.urlToImage,
                            description = article.description
                        )
                    }
            }
    }

    //--------------db
    override fun getNewsFromDb(): Observable<List<ArticleViewEntity>> {
        return newsLocalDataSource.getAllNews()
            .map { newsEntityList ->
                newsEntityList.map { it.toView() }
            }
    }

    override  fun saveNewsToDb(news: List<ArticleViewEntity>) {
        val entities = news.map { it.toEntity() }
        newsLocalDataSource.saveNewsToDb(entities)
    }

    override fun searchNews(query: String): Observable<List<ArticleViewEntity>> {
        return newsLocalDataSource.searchNews(query)
            .map { it.map { entity -> entity.toView() } }
    }

    override fun deleteNews(news: List<ArticleViewEntity>) {
        val entities = news.map { it.toEntity() }
        newsLocalDataSource.deleteNews(entities)
    }


}

