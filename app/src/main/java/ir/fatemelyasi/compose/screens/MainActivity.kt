package ir.fatemelyasi.compose.screens

import Message
import android.R.id.message
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
    NavHost(navController = navController, startDestination = MyScreens.FirstScreen.route) {
        composable(
            route = MyScreens.SecondScreen.route
        ) {
            SecondScreen(navController)
        }




    composable(
        route = MyScreens.SecondScreen.route
    ) {
        SecondScreen(navController)
    }
    composable(route = MyScreens.ArticleActivity.route) {
        ArticleActivity(navController)
    }


}
}



