package ir.fatemelyasi.compose.model.repository.newsRepository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ir.fatemelyasi.compose.model.dataSources.local.NewsLocalDataSource
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.compose.model.dataSources.remote.NewsRemoteDataSource
import ir.fatemelyasi.compose.model.local.NewsEntity

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource

) : NewsRepository {

    //---------------
    override fun getNewsFromServer(): Single<List<ArticleViewEntity>> {
        return newsRemoteDataSource.getNewsInformation()
            .map { response ->
                response.articles.orEmpty()
                    .filterNotNull()
                    .map { article ->
                        ArticleViewEntity(
                            title = article.title.orEmpty(),
                            publishedAt = article.publishedAt.orEmpty(),
                            urlToImage = article.urlToImage.orEmpty(),
                            description = article.description.orEmpty()
                        )
                    }
            }
    }

    //--------------db
    fun getNewsFromDb(): Observable<List<NewsEntity>> {
        return newsLocalDataSource.getAllNews()
    }

    fun insertNews(newsEntityModel: NewsEntity) {
        newsLocalDataSource.insertNews(newsEntityModel)
    }

    fun deleteNews(newsEntityModel: NewsEntity) {
        newsLocalDataSource.deleteNews(newsEntityModel)
    }

    fun searchNews(query: String): Observable<List<NewsEntity>> {
        return newsLocalDataSource.searchNews(query)
    }
}

