package ir.fatemelyasi.compose.view.utils.mappers

import ir.fatemelyasi.compose.model.local.NewsEntity
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity


fun NewsEntity.toViewEntity(): ArticleViewEntity = ArticleViewEntity(
    title = this.title,
    description = this.description,
    urlToImage = this.urlToImage ?: "",
    publishedAt = this.publishedAt
)

fun ArticleViewEntity.toViewEntity(): NewsEntity = NewsEntity(
    id = null,
    title = this.title,
    description = this.description ?: "",
    urlToImage = this.urlToImage ,
    publishedAt = this.publishedAt ?: ""
)
