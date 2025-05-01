package ir.fatemelyasi.compose.model.dataSources.remote

import io.reactivex.rxjava3.core.Observable
import ir.fatemelyasi.compose.model.network.responseModel.NewsResponseModel

interface NewsRemoteDataSource {
    fun getNewsInformation(): Observable<NewsResponseModel>}
