package ir.fatemelyasi.compose.model.dataSources

import NewsRemoteDataSource
import ir.fatemelyasi.compose.model.dataSources.remote.NewsRemoteDataSourceIMP

class DataSourceManager {

    //Singleton
    private val newsRemoteDataSource: NewsRemoteDataSource by lazy {
        NewsRemoteDataSourceIMP()
    }

}