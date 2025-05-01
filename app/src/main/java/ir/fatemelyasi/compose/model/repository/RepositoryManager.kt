package ir.fatemelyasi.compose.model.repository

import ir.fatemelyasi.compose.model.dataSources.remote.NewsRemoteDataSource
import ir.fatemelyasi.compose.model.dataSources.remote.NewsRemoteDataSourceImpl
import ir.fatemelyasi.compose.model.network.NetworkManager
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepository
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepositoryImpl

class RepositoryManager {

    // Singleton for NewsRemoteDataSource
    private val remoteDataSource: NewsRemoteDataSource by lazy {
        NewsRemoteDataSourceImpl(NetworkManager().getApiService()!!)
    }
/*
    // Optional: LocalDataSource, if you are using it
    private val localDataSource: NewsLocalDataSource by lazy {
        NewsLocalDataSourceIMP(LocalDatabase.getInstance().newsDao())
    }

 */

    // Singleton for NewsRepository
    val newsRepository: NewsRepository by lazy {
        NewsRepositoryImpl(remoteDataSource) // If you are using localDataSource, you can also pass it
    }

}




