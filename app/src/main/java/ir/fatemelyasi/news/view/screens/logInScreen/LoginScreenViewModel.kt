package ir.fatemelyasi.news.view.screens.logInScreen

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ir.fatemelyasi.news.R
import ir.fatemelyasi.news.model.repository.authRepository.AuthRepository
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val authRepository: AuthRepository,
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


    fun onLoginClick(onSuccess: () -> Unit, onError: (Int) -> Unit) {

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

        if (!authRepository.login(email, password)) {
            onError(R.string.error_user_not_registered)
            return
        }

        isLoading = true
        authRepository.signUp(email, password, true)
        isLoading = false
        onSuccess()
    }
}

