package ir.fatemelyasi.news.model.network.responseModel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceResponseModel(
    @SerialName("id")
    val id: String?, // wired
    @SerialName("name")
    val name: String? // VentureBeat
)