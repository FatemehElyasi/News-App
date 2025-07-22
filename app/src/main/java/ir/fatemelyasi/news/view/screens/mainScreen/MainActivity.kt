package ir.fatemelyasi.news.view.screens.mainScreen

import AuthenticationScreen
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ir.fatemelyasi.news.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.news.view.screens.allArticleScreen.AllArticleScreenViewModel
import ir.fatemelyasi.news.view.screens.allArticleScreen.AllArticlesScreen
import ir.fatemelyasi.news.view.screens.articleDetailScreen.ArticleDetailScreen
import ir.fatemelyasi.news.view.screens.dashboardScreen.DashboardScreen
import ir.fatemelyasi.news.view.screens.logInScreen.LoginScreen
import ir.fatemelyasi.news.view.screens.signUpScreen.SignUpScreen
import ir.fatemelyasi.news.view.screens.splashScreen.SplashScreen
import ir.fatemelyasi.news.view.ui.theme.ComposeTheme
import ir.fatemelyasi.news.view.utils.MyScreens
import org.koin.compose.viewmodel.koinViewModel


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Navigation()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MyScreens.SplashScreen::class
    ) {
        composable<MyScreens.SplashScreen> {
            SplashScreen(
                navigateToDashboard = {
                    navController.navigate(MyScreens.DashboardScreen)
                },
                navigateToAuthenticationScreen = {
                    navController.navigate(MyScreens.AuthenticationScreen)
                }
            )
        }
        composable<MyScreens.AuthenticationScreen> {
            AuthenticationScreen(
                navigateToSignUpScreen = {
                    navController.navigate(MyScreens.SignUpScreen)
                },
                navigateToLogInScreen = {
                    navController.navigate(MyScreens.LogInInScreen)
                }
            )
        }
        composable<MyScreens.SignUpScreen> {
            SignUpScreen(
                navigateToLogInScreen = {
                    navController.navigate(MyScreens.LogInInScreen)
                },
                navigateToDashboardScreen = {
                    navController.navigate(MyScreens.DashboardScreen){
                        popUpTo(0){
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<MyScreens.LogInInScreen> {
            LoginScreen(
                navigateToSignUpScreen = {
                    navController.navigate(MyScreens.SignUpScreen)
                },
                navigateToDashboardScreen = {
                    navController.navigate(MyScreens.DashboardScreen){
                        popUpTo(0){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<MyScreens.DashboardScreen> {
            DashboardScreen(
                navigateToSecondScreen = { data ->
                    navController.navigate(
                        MyScreens.ArticleDetailScreen(
                            title = data.title,
                            date = data.publishedAt.orEmpty(),
                            imageResId = data.urlToImage,
                            description = data.description.orEmpty()
                        )
                    )
                },
                navigateToArticleScreen = {
                    navController.navigate(MyScreens.AllArticlesScreen)
                },
                navigateToAuthenticationScreen = {
                    navController.navigate(MyScreens.AuthenticationScreen) {
                        popUpTo(MyScreens.SplashScreen::class) {
                            inclusive = true
                        }

                    }
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
            val viewModel: AllArticleScreenViewModel = koinViewModel()

            AllArticlesScreen(
                navigateToSecondScreen = { article ->
                    navController.navigate(
                        MyScreens.ArticleDetailScreen(
                            title = article.title,
                            date = article.publishedAt.orEmpty(),
                            imageResId = article.urlToImage,
                            description = article.description.orEmpty()
                        )
                    )
                },
                popUpToFirstScreen = {
                    navController.popBackStack()
                },
                viewModel = viewModel,
                deleteArticle = { article -> viewModel.deleteArticle(article) }
            )
        }

    }
}



