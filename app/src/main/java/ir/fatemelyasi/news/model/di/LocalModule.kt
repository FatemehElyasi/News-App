package ir.fatemelyasi.news.model.di

import android.content.Context
import android.content.SharedPreferences
import ir.fatemelyasi.news.model.sharedPrefHelper.SharedPrefHelper
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class LocalModule {
    companion object {
        private const val KEY_PREF_NAME: String = "authentication_preferences"
    }

    @Single
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(KEY_PREF_NAME, Context.MODE_PRIVATE)
    }

    @Single
    fun provideSharedPrefHelper(prefs: SharedPreferences): SharedPrefHelper {
        return SharedPrefHelper(prefs)
    }

}