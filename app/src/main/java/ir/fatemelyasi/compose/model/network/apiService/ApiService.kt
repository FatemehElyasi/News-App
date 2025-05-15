package ir.fatemelyasi.compose.model.network.apiService

import io.reactivex.rxjava3.core.Single
import ir.fatemelyasi.compose.model.network.responseModel.NewsResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "67de6a889f734c53bbc200c0827dfcd7"
private const val QUERY = "keyword"

interface ApiService {
    @GET("v2/everything")
    fun getNews(
        @Query("q") query: String = QUERY,
        @Query("apiKey") apiKey: String = API_KEY
    ): Single<NewsResponseModel>
}


