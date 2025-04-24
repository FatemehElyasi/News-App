package ir.fatemelyasi.compose.utils

import kotlinx.serialization.Serializable

@Serializable
sealed class MyScreens() {
    @Serializable
    object DashboardScreen : MyScreens()

    @Serializable
    data class ArticleDetailScreen(
        val title: String = "",
        val date: String = "",
        val imageResId: Int = 0,
    ) : MyScreens()

    @Serializable
    object AllArticlesScreen : MyScreens()
}