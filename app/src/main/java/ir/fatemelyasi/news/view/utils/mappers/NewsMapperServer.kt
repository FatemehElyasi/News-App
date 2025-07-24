package ir.fatemelyasi.news.view.utils.mappers

import ir.fatemelyasi.news.model.network.responseModel.ArticleResponseModel
import ir.fatemelyasi.news.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.news.view.utils.toFormattedDate

fun ArticleResponseModel.toViewEntity(): ArticleViewEntity {
    return ArticleViewEntity(
        title = this.title ?: "",
        publishedAt = this.publishedAt.toFormattedDate(),
        urlToImage = this.urlToImage ?: "",
        description = this.description
    )
}