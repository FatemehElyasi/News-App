package ir.fatemelyasi.compose.view

import android.app.Application
import ir.fatemelyasi.compose.model.di.DatabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(

            )
        }
    }
}