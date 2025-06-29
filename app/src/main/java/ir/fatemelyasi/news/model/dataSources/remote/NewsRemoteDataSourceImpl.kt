package ir.fatemelyasi.news.model.dataSources.remote

import android.util.Log
import io.reactivex.rxjava3.core.Single
import ir.fatemelyasi.news.model.network.apiService.ApiService
import ir.fatemelyasi.news.model.network.responseModel.NewsResponseModel

@org.koin.core.annotation.Single
class NewsRemoteDataSourceImpl(
    private val apiService: ApiService
) : NewsRemoteDataSource {
     override fun getNewsInformation(): Single<NewsResponseModel> {
         Log.d("API_CALL", "Calling getNews() from ApiService")
         return apiService.getNews()
     }
 }

