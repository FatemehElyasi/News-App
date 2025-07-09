package ir.fatemelyasi.news.view.screens.splashScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.fatemelyasi.news.view.ui.theme.LocalCustomColors
import ir.fatemelyasi.news.view.ui.theme.LocalCustomTypography
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel(),
    navigateToLogin: () -> Unit,
    navigateToDashboard: () -> Unit
) {
    val isUserLoggedIn = remember { mutableStateOf<Boolean?>(null) }
    val colors = LocalCustomColors.current
    val typography = LocalCustomTypography.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.onSurface),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = ("News"),
                style = typography.headlineLarge.copy(
                    colors.onPrimary
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }


    LaunchedEffect(Unit) {
        delay(2800)
        isUserLoggedIn.value = viewModel.isUserLoggedIn()
    }

    when (isUserLoggedIn.value) {
        null -> navigateToDashboard()
        true -> navigateToDashboard()
        false -> navigateToLogin()
    }
}
