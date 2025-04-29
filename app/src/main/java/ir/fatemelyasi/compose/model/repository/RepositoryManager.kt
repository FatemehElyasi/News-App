package ir.fatemelyasi.compose.model.repository

import android.content.Context
import ir.fatemelyasi.compose.model.dataSources.DataSourceManager
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepository

class RepositoryManager {

    private var newsRepository: NewsRepository? = null

    fun getNewsRepository(context: Context): NewsRepository {
        if (newsRepository != null) return newsRepository!!



        return newsRepository!!
    }
}


