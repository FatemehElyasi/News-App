package ir.fatemelyasi.news.model.repository.authRepository

import ir.fatemelyasi.news.model.dataSources.local.NewsLocalDataSource

@org.koin.core.annotation.Single
class AuthRepositoryImp(
    private val newsLocalDataSource: NewsLocalDataSource,
) : AuthRepository {

    override fun signUp(email: String, password: String, isLoggedIn: Boolean) {
        newsLocalDataSource.saveUserAuthenticationInfo(email, password, isLoggedIn)
    }

    override fun login(email: String, password: String): Boolean {
        return newsLocalDataSource.logIn(email, password)
    }

    override fun isLoggedIn(): Boolean {
        return newsLocalDataSource.isLoggedIn()
    }

    override fun clearInformation() {
        return newsLocalDataSource.clearInformation()
    }

}