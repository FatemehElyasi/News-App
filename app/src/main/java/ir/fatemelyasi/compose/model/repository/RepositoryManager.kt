package ir.fatemelyasi.compose.model.repository

import android.content.Context
import ir.fatemelyasi.compose.model.dataSources.local.NewsLocalDataSource
import ir.fatemelyasi.compose.model.dataSources.local.NewsLocalDataSourceImpl
import ir.fatemelyasi.compose.model.dataSources.remote.NewsRemoteDataSource
import ir.fatemelyasi.compose.model.dataSources.remote.NewsRemoteDataSourceImpl
import ir.fatemelyasi.compose.model.local.LocalManager
import ir.fatemelyasi.compose.model.network.NetworkManager
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepository
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepositoryImpl

class  RepositoryManager(context:Context) {

    private val remoteDataSource: NewsRemoteDataSource by lazy {
        NewsRemoteDataSourceImpl(NetworkManager().getApiService()!!)
    }

    private val localDataSource: NewsLocalDataSource by lazy {
        val dao = LocalManager.getNewsDao(context.applicationContext)
        NewsLocalDataSourceImpl(dao)
    }

    val newsRepository: NewsRepository by lazy {
        NewsRepositoryImpl(remoteDataSource, localDataSource)
    }


}






