package ir.fatemelyasi.news.model.repository.authRepository

import ir.fatemelyasi.news.model.dataSources.local.authLocalDataSource.AuthLocalDataSource

@org.koin.core.annotation.Single
class AuthRepositoryImp(
    private val authLocalDataSource: AuthLocalDataSource,
) : AuthRepository {

    override fun signUp(email: String, password: String, isLoggedIn: Boolean) {
        authLocalDataSource.saveUserAuthenticationInfo(email, password, isLoggedIn)
    }

    override fun login(email: String, password: String): Boolean {
        return authLocalDataSource.logIn(email, password)
    }

    override fun isLoggedIn(): Boolean {
        return authLocalDataSource.isLoggedIn()
    }

    override fun clearInformation() {
        return authLocalDataSource.clearInformation()
    }

}