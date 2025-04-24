package ir.fatemelyasi.compose.data

import kotlinx.serialization.Serializable

@Serializable
data class MessageViewEntity(
    val title: String,
    val date: String,
    val imageResId: Int,
)