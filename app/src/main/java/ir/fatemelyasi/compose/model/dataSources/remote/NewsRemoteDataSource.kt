import io.reactivex.Single
import ir.fatemelyasi.compose.model.network.apiService.ApiService
import ir.fatemelyasi.compose.model.network.responseModel.NewsResponse
import retrofit2.Call

public interface NewsRemoteDataSource {

    fun getNews(): Call<NewsResponse>
}
