package ir.fatemelyasi.compose.model.dataSources.remote

import io.reactivex.rxjava3.core.Single
import ir.fatemelyasi.compose.model.network.apiService.ApiService
import ir.fatemelyasi.compose.model.network.responseModel.NewsResponseModel


class NewsRemoteDataSourceImpl(
    private val apiService: ApiService
) : NewsRemoteDataSource {
     override fun getNewsInformation(): Single<NewsResponseModel> {
         return apiService.getNews()
     }
 }

