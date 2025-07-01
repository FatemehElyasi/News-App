package ir.fatemelyasi.news.model.network.responseModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseModel(
    @SerialName("articles")
    val articles: List<ArticleResponseModel?>?,
    @SerialName("status")
    val status: String?, // ok
    @SerialName("totalResults")
    val totalResults: Int? // 878
)