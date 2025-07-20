package ir.fatemelyasi.news.model.sharedPrefHelper

import android.content.Context

class SharedPrefHelper(context: Context) {

    companion object {
        private const val PREF_NAME = "my_prefs"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveInfo(email: String, password: String, isLoggedIn: Boolean) {
        prefs.edit()
            .putString(KEY_EMAIL, email)
            .putString(KEY_PASSWORD, password)
            .putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
            .apply()
    }

    fun getEmail(): String? = prefs.getString(KEY_EMAIL, null)

    fun getPassword(): String? = prefs.getString(KEY_PASSWORD, null)

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    fun logOut() {
        prefs.edit()
            .putBoolean(KEY_IS_LOGGED_IN, false)
            .apply()
    }
}
