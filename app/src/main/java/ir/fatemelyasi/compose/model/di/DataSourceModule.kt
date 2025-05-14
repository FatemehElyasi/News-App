package ir.fatemelyasi.compose.model.di

import ir.fatemelyasi.compose.model.dataSources.local.NewsLocalDataSource
import ir.fatemelyasi.compose.model.dataSources.local.NewsLocalDataSourceImpl
import ir.fatemelyasi.compose.model.dataSources.remote.NewsRemoteDataSource
import ir.fatemelyasi.compose.model.dataSources.remote.NewsRemoteDataSourceImpl
import org.koin.dsl.module

/*
val dataSourceModule = module {

    single<NewsRemoteDataSource> {
        NewsRemoteDataSourceImpl(get())
    }// ApiService inject

    single<NewsLocalDataSource> {
        NewsLocalDataSourceImpl(get())
    }// database inject
}
 */

