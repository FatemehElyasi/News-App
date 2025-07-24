package ir.fatemelyasi.news.view.screens.splashScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ir.fatemelyasi.news.model.repository.newsRepository.NewsRepository
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SplashViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {
    var isUserLoggedIn by mutableStateOf<Boolean?>(null)
        private set

    fun checkUserLoggedIn() {
        isUserLoggedIn = newsRepository.isLoggedIn()
    }

}
