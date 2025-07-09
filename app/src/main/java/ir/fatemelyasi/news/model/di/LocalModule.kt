package ir.fatemelyasi.news.model.di

import android.content.Context
import ir.fatemelyasi.news.model.sharedPrefHelper.SharedPrefHelper
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class LocalModule {

    @Single
    fun provideSharedPrefHelper(context: Context): SharedPrefHelper {
        return SharedPrefHelper(context)
    }

}