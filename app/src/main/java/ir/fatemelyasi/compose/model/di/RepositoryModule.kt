package ir.fatemelyasi.compose.model.di

import io.reactivex.rxjava3.schedulers.Schedulers.single
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepository
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepositoryImpl
import org.koin.dsl.module

/*
val repositoryModule = module {
    single<NewsRepository> {
        NewsRepositoryImpl(get()) // NewsRemoteDataSource inject
    }
}
 */
