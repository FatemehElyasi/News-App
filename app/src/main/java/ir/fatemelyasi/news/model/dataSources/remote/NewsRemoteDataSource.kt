package ir.fatemelyasi.news.model.dataSources.remote

import ir.fatemelyasi.news.model.network.responseModel.NewsResponseModel
import io.reactivex.rxjava3.core.Single

interface NewsRemoteDataSource {
    fun getNewsInformation(): Single<NewsResponseModel>}
