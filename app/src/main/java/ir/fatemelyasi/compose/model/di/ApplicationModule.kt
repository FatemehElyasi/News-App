package ir.fatemelyasi.compose.model.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(
    [
        DatabaseModule::class,
        NetworkModule::class,
    ]
)
@ComponentScan("ir.fatemelyasi.compose")
class ApplicationModule
