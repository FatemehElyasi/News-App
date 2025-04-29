package ir.fatemelyasi.compose.model.network

import ir.fatemelyasi.compose.model.network.apiService.ApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

public class NetworkManager {

    companion object{

        var apiService: ApiService? = null
        var retrofit: Retrofit? = null

        private fun getBaseUrl(): String ="https://newsapi.org/"

    }

    private fun getRetrofit(): Retrofit {
        if (retrofit != null) return retrofit!!
        retrofit = Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        return retrofit!!
    }

    fun getApiService(): ApiService? {
        if (apiService == null) {
            apiService = getRetrofit().create<ApiService?>(ApiService::class.java)
        }
        return apiService
    }
}
