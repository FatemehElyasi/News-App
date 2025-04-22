package ir.fatemelyasi.compose.utils

sealed class MyScreens(val route: String) {

    object FirstScreen : MyScreens("FirstScreen")
    object SecondScreen : MyScreens("SecondScreen")
    object ArticleActivity : MyScreens("ArticleActivity")

}