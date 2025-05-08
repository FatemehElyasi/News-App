package ir.fatemelyasi.compose.view.utils.mappers

import ir.fatemelyasi.compose.model.local.NewsEntity
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity


fun NewsEntity.toView(): ArticleViewEntity = ArticleViewEntity(
    title = this.title,
    description = this.description,
    urlToImage = this.urlToImage,
    publishedAt = this.publishedAt
)

fun ArticleViewEntity.toEntity(): NewsEntity = NewsEntity(
    id = null,
    title = this.title ?: "",
    description = this.description ?: "",
    urlToImage = this.urlToImage ?: "",
    publishedAt = this.publishedAt ?: ""
)
