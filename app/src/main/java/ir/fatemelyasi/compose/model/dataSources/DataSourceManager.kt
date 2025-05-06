package ir.fatemelyasi.compose.model.dataSources

import android.content.Context
import ir.fatemelyasi.compose.model.dataSources.local.NewsLocalDataSource
import ir.fatemelyasi.compose.model.dataSources.local.NewsLocalDataSourceImpl
import ir.fatemelyasi.compose.model.dataSources.remote.NewsRemoteDataSource
import ir.fatemelyasi.compose.model.dataSources.remote.NewsRemoteDataSourceImpl
import ir.fatemelyasi.compose.model.local.LocalManager
import ir.fatemelyasi.compose.model.network.NetworkManager

class DataSourceManager(
    private val context: Context
) {

    // Singleton pattern for remoteDataSource
    val getRemoteDataSource: NewsRemoteDataSource by lazy {
        NewsRemoteDataSourceImpl(NetworkManager().getApiService()!!)
    }

    // Singleton pattern for localDataSource
    val getLocalDataSource : NewsLocalDataSource by lazy {
        NewsLocalDataSourceImpl(LocalManager.getNewsDao(context))

    }
}