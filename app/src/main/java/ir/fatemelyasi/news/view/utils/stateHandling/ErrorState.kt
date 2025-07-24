package ir.fatemelyasi.news.view.utils.stateHandling

sealed class ErrorState {
    object None : ErrorState()
    data class Error(val throwable: Throwable) : ErrorState()
    data class Message(val resId: Int) : ErrorState()
}