package ir.fatemelyasi.compose.model.dataSources.remote

import io.reactivex.rxjava3.core.Observable
import ir.fatemelyasi.compose.model.network.apiService.ApiService
import ir.fatemelyasi.compose.model.network.responseModel.NewsResponseModel


class NewsRemoteDataSourceImpl(
    private val apiService: ApiService
) : NewsRemoteDataSource {
     override fun getNewsInformation(): Observable<NewsResponseModel> {
         return apiService.getNews()
     }
 }

