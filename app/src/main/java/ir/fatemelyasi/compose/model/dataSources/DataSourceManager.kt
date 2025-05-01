package ir.fatemelyasi.compose.model.dataSources

import ir.fatemelyasi.compose.model.dataSources.remote.NewsRemoteDataSource
import ir.fatemelyasi.compose.model.dataSources.remote.NewsRemoteDataSourceImpl
import ir.fatemelyasi.compose.model.network.NetworkManager

class DataSourceManager {

    // Singleton pattern for remoteDataSource
    val remoteDataSource: NewsRemoteDataSource by lazy {
        NewsRemoteDataSourceImpl(NetworkManager().getApiService()!!)
    }


    /*
    private var remoteDataSource: NewsRemoteDataSource? = null

    fun getRemoteDataSource(): NewsRemoteDataSource {
        if (remoteDataSource != null) return remoteDataSource!!
        remoteDataSource = NewsRemoteDataSourceImpl(NetworkManager().getApiService())
        return remoteDataSource!!
    }
     */



}