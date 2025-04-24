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
import ir.fatemelyasi.compose.data.Message
import ir.fatemelyasi.compose.ui.theme.ComposeTheme
import ir.fatemelyasi.compose.utils.MyScreens
import ir.fatemelyasi.compose.R


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
    NavHost(navController = navController, startDestination = MyScreens.FirstScreen.route) {
        composable(
            route = MyScreens.FirstScreen.route
        ) {
            FirstScreen(
                navigateToSecondScreen = {
                    navController.navigate(MyScreens.SecondScreen.route)
                },
                message = ArrayList(messages),
                navigateToArticleScreen = {
                    navController.navigate(MyScreens.ArticleActivity.route)
                }
            )
        }

        composable(
            route = MyScreens.SecondScreen.route
        ) {
            SecondScreen(
                popUpToFirstScreen = {
                    navController.popBackStack()
                }, text = Message(
                    title = "Make a Successful Instagram ",
                    date = "October,4,2024",
                    imageResId = R.drawable.banner
                )
            )
        }

        composable(route = MyScreens.ArticleActivity.route) {
            ArticleActivity(
                navigateToSecondScreen = {
                    navController.navigate(MyScreens.SecondScreen.route)
                },
                popUpToFirstScreen = {
                    navController.popBackStack()
                }

            )
        }
    }
}



