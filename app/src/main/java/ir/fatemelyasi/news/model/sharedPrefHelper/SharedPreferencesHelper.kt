package ir.fatemelyasi.news.model.sharedPrefHelper

import android.R.attr.password
import android.content.Context

class SharedPrefHelper(context: Context) {

    private val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    fun saveEmail(email: String, password: String) {
        prefs.edit().putString("email", email).putString("password", password).apply()
    }

    fun getEmail(): String? {
        return prefs.getString("email", null)
    }

    fun getPassword(): String? {
        return prefs.getString("password", null)
    }

    fun isLoggedIn(): Boolean = getEmail() != null && getPassword() != null

    //optional
    fun logout() {
        prefs.edit().clear().apply()
    }
}
