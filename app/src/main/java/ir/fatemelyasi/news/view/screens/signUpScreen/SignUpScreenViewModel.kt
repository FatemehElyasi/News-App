package ir.fatemelyasi.news.view.screens.signUpScreen

import android.annotation.SuppressLint
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ir.fatemelyasi.news.R
import ir.fatemelyasi.news.model.repository.newsRepository.NewsRepository
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SignUpScreenViewModel(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var rePassword by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set


    fun onNameChange(value: String) {
        name = value
    }

    fun onEmailChange(value: String) {
        email = value
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    fun onRePasswordChange(value: String) {
        rePassword = value
    }

    @SuppressLint("CheckResult")
    fun onSignUpClick(onSuccess: () -> Unit, onError: (Int) -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            onError(R.string.error_empty_fields)
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            onError(R.string.error_invalid_email)
            return
        }

        if (password.length < 8) {
            onError(R.string.error_invalid_password)
            return
        }

        if (password != rePassword) {
            onError(R.string.error_match_passwords)
            return
        }

        isLoading = true
        newsRepository.signUp(email, password, true)
        isLoading = false
        onSuccess()
    }
}

