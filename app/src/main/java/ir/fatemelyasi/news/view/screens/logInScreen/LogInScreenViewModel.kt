package ir.fatemelyasi.news.view.screens.logInScreen

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ir.fatemelyasi.news.model.repository.newsRepository.NewsRepository
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun onEmailChange(newEmail: String) {
        email = newEmail.lowercase()
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword.lowercase()
    }

    fun onLoginClick(onSuccess: () -> Unit, onError: (String) -> Unit) {

        if (email.isBlank() || password.isBlank()) {
            onError("Please fill in all fields.")
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            onError("Invalid email.")
            return
        }

        if (password.length < 8) {
            onError("Password must be at least 8 characters.")
            return
        }

        if (newsRepository.getEmail() != email || newsRepository.getPassword() != password) {
            onError("User not registered. Please sign up first.")
            return
        }

        isLoading = true
        newsRepository.saveInfo(email, password, true)
        isLoading = false
        onSuccess()

    }
}

