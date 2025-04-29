package ir.fatemelyasi.compose.model.dataSources.remote

import NewsRemoteDataSource
import ir.fatemelyasi.compose.model.network.NetworkManager.Companion.apiService
import ir.fatemelyasi.compose.model.network.apiService.ApiService
import ir.fatemelyasi.compose.model.network.responseModel.NewsResponse
import retrofit2.Call


public class NewsRemoteDataSourceIMP(
    private val apiService: ApiService? = null
) : NewsRemoteDataSource {

    override fun getNews(): Call<NewsResponse> {
        return apiService!!.getNews()
    }
}

