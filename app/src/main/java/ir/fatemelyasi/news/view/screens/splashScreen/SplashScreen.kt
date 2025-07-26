package ir.fatemelyasi.news.view.screens.splashScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.fatemelyasi.news.view.ui.theme.LocalCustomColors
import ir.fatemelyasi.news.view.ui.theme.LocalCustomTypography
import org.koin.compose.viewmodel.koinViewModel
import ir.fatemelyasi.news.R

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel(),
    navigateToDashboard: () -> Unit,
    navigateToAuthenticationScreen: () -> Unit
) {
    val colors = LocalCustomColors.current
    val typography = LocalCustomTypography.current
    var shouldNavigate by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.checkUserLoggedIn()
    }
    LaunchedEffect(Unit) {
        if (viewModel.isUserLoggedIn == true) {
            navigateToDashboard()
        } else {
            navigateToAuthenticationScreen()
        }
        shouldNavigate = true
    }
    if (!shouldNavigate) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.onSurface),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id= R.string.news),
                    style = typography.headlineLarge.copy(
                        colors.onPrimary
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
