package ir.fatemelyasi.news.model.repository.authRepository


interface AuthRepository {
    fun signUp(email: String, password: String, isLoggedIn: Boolean)
    fun login(email: String, password: String): Boolean
    fun isLoggedIn(): Boolean
    fun clearInformation()
}