package ir.fatemelyasi.compose.model.viewEntity

import ir.fatemelyasi.compose.model.network.responseModel.ArticleResponseModel
import kotlinx.serialization.Serializable

@Serializable
data class NewsViewEntity(
    val articles: List<ArticleResponseModel?>?,
    val status: String?, // ok
    val totalResults: Int? // 878
)

