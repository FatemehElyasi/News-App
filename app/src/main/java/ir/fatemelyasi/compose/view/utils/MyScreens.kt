package ir.fatemelyasi.compose.view.utils

import kotlinx.serialization.Serializable

@Serializable
sealed class MyScreens() {
    @Serializable
    object DashboardScreen : MyScreens()

    @Serializable
    data class ArticleDetailScreen(
        val title: String = "",
        val date: String = "",
        val imageResId: String =" ",
        val description: String = "",
    ) : MyScreens()

    @Serializable
    object AllArticlesScreen : MyScreens()
}