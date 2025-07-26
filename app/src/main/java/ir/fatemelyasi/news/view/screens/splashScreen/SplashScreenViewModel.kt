package ir.fatemelyasi.news.view.screens.splashScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ir.fatemelyasi.news.model.repository.authRepository.AuthRepository
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SplashViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    var isUserLoggedIn by mutableStateOf<Boolean?>(null)
        private set

    fun checkUserLoggedIn() {
        isUserLoggedIn = authRepository.isLoggedIn()
    }

}
