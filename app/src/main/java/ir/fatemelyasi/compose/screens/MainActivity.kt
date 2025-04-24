package ir.fatemelyasi.compose.screens

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
import ir.fatemelyasi.compose.data.MessageViewEntity
import ir.fatemelyasi.compose.ui.theme.ComposeTheme
import ir.fatemelyasi.compose.utils.MyScreens


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
                            title = data.title,
                            date = data.date,
                            imageResId = data.imageResId
                        )
                    )
                },
                messageViewEntity = ArrayList(messageViewEntities),
                navigateToArticleScreen = {
                    navController.navigate(MyScreens.AllArticlesScreen)
                }
            )
        }

        composable<MyScreens.ArticleDetailScreen> { backStackEntry ->
            val dataModel: MyScreens.ArticleDetailScreen = backStackEntry.toRoute()
            val messageViewEntity = MessageViewEntity(
                title = dataModel.title,
                date = dataModel.date,
                imageResId = dataModel.imageResId,
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
                navigateToSecondScreen = {
                    navController.navigate(MyScreens.ArticleDetailScreen())
                },
                popUpToFirstScreen = {
                    navController.popBackStack()
                },
                messageViewEntities = messageViewEntities,
            )
        }
    }
}



