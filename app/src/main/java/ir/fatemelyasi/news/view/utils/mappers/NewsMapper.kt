package ir.fatemelyasi.news.view.utils.mappers

import ir.fatemelyasi.news.model.local.NewsEntity
import ir.fatemelyasi.news.model.viewEntity.ArticleViewEntity


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
