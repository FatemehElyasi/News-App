package ir.fatemelyasi.news.model.dataSources.local.authLocalDataSource

import ir.fatemelyasi.news.model.sharedPrefHelper.SharedPrefHelper

class AuthLocalDataSourceImp(
    private val sharedPrefHelper: SharedPrefHelper
) : AuthLocalDataSource {
    override fun saveUserAuthenticationInfo(email: String, password: String, isLoggedIn: Boolean) {
        return sharedPrefHelper.saveUserAuthenticationInfo(email, password, isLoggedIn)
    }

    override fun logIn(email: String, password: String): Boolean {
        return sharedPrefHelper.isUserValid(email, password)
    }

    override fun isLoggedIn(): Boolean {
        return sharedPrefHelper.isLoggedIn()
    }

    override fun clearInformation() {
        return sharedPrefHelper.logout()
    }
}