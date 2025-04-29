package ir.fatemelyasi.compose.model.network.apiService

import ir.fatemelyasi.compose.model.network.responseModel.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val API_KEY = "67de6a889f734c53bbc200c0827dfcd7"
private const val QUERY = "keyword"

public interface ApiService {
    @GET("v2/everything")
    public fun getNews(
        @Query("q") query: String = QUERY,
        @Query("apiKey") apiKey: String = API_KEY
    ): Call<NewsResponse>
}


