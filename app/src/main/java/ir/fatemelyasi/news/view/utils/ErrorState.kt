package ir.fatemelyasi.news.view.utils

sealed class ErrorState {
    object None : ErrorState()
    data class Error(val throwable: Throwable) : ErrorState()
}

