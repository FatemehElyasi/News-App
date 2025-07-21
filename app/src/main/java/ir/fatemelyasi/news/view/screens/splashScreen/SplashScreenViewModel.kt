package ir.fatemelyasi.news.view.screens.splashScreen

import androidx.lifecycle.ViewModel
import ir.fatemelyasi.news.model.repository.newsRepository.NewsRepository
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SplashViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun isUserLoggedIn(): Boolean {
        return newsRepository.isLoggedIn()
    }

    fun getEmail(): String? = newsRepository.getEmail()
    fun getPassword(): String? = newsRepository.getPassword()

}
