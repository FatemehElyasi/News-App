package ir.fatemelyasi.news.model.dataSources.local.authLocalDataSource

interface AuthLocalDataSource {

    fun saveUserAuthenticationInfo(email: String, password: String, isLoggedIn: Boolean)
    fun logIn(email: String, password: String): Boolean
    fun isLoggedIn(): Boolean
    fun clearInformation()
}