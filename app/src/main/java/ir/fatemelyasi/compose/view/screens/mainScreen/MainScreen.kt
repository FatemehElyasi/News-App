package ir.fatemelyasi.compose.view.screens.mainScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.compose.view.screens.articleDetailScreen.ArticleDetailScreen
import ir.fatemelyasi.compose.view.screens.allArticleScreen.AllArticlesScreen
import ir.fatemelyasi.compose.view.screens.dashboardScreen.DashboardScreen
import ir.fatemelyasi.compose.view.ui.theme.ComposeTheme
import ir.fatemelyasi.compose.view.utils.MyScreens


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MyScreens.DashboardScreen
    ) {
        composable<MyScreens.DashboardScreen> {
            DashboardScreen(
                navigateToSecondScreen = { data ->
                    navController.navigate(
                        MyScreens.ArticleDetailScreen(
                            title = data.title.orEmpty(),
                            date = data.publishedAt.orEmpty(),
                            imageResId = data.urlToImage.orEmpty(),
                            description = data.description.orEmpty()
                        )
                    )
                },
                //messageViewEntity = ArrayList(messageViewEntities),
                navigateToArticleScreen = {
                    navController.navigate(MyScreens.AllArticlesScreen)
                }
            )
        }

        composable<MyScreens.ArticleDetailScreen> { backStackEntry ->
            val dataModel: MyScreens.ArticleDetailScreen = backStackEntry.toRoute()

            val messageViewEntity = ArticleViewEntity(
                title = dataModel.title,
                publishedAt = dataModel.date,
                urlToImage = dataModel.imageResId,
                description = dataModel.description
            )
            ArticleDetailScreen(
                popBackStack = {
                    navController.popBackStack()
                },
                text = messageViewEntity
            )
        }
        composable<MyScreens.AllArticlesScreen> {
            AllArticlesScreen(
                navigateToSecondScreen = { article ->
                    navController.navigate(
                        MyScreens.ArticleDetailScreen(
                            title = article.title.orEmpty(),
                            date = article.publishedAt.orEmpty(),
                            imageResId = article.urlToImage.orEmpty(),
                            description = article.description.orEmpty()
                        )
                    )
                },
                popUpToFirstScreen = {
                    navController.popBackStack()
                }
            )
        }

    }
}



