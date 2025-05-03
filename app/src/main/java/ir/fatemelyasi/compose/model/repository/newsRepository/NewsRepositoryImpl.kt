package ir.fatemelyasi.compose.model.repository.newsRepository

import io.reactivex.rxjava3.core.Single
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.compose.model.dataSources.remote.NewsRemoteDataSource

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsRepository {
    override fun getNews(): Single<List<ArticleViewEntity>> {
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
}