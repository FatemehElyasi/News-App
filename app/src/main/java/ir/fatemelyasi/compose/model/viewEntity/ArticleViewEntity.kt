package ir.fatemelyasi.compose.model.viewEntity

import kotlinx.serialization.Serializable

@Serializable
data class ArticleViewEntity(
    val title: String?,
    val publishedAt: String?,
    val urlToImage: String?,
    val description: String?,
)