package ir.fatemelyasi.news.model.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(
    [
        DatabaseModule::class,
        NetworkModule::class,
    ]
)
@ComponentScan("ir.fatemelyasi.news")
class ApplicationModule
