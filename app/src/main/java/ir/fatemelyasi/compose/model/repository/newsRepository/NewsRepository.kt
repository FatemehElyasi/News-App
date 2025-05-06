package ir.fatemelyasi.compose.model.repository.newsRepository

import io.reactivex.rxjava3.core.Single
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity

interface NewsRepository {

    fun getNewsFromServer(): Single<List<ArticleViewEntity>>


}