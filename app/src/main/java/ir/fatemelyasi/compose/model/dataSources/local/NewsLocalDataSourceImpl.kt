package ir.fatemelyasi.compose.model.dataSources.local

import io.reactivex.rxjava3.core.Observable
import ir.fatemelyasi.compose.model.local.NewsDao
import ir.fatemelyasi.compose.model.local.NewsEntity

class NewsLocalDataSourceImpl(
    private val newsDao: NewsDao
) : NewsLocalDataSource {

    override fun insertNews(newsEntityModel: NewsEntity) {
       newsDao.insertAllNews(newsEntityModel)
    }

    override fun deleteNews(newsEntityModel: NewsEntity) {
       newsDao.deleteNews(newsEntityModel)
    }

    override fun getAllNews(): Observable<List<NewsEntity>> {
        return newsDao.getAllNews()
    }

    override fun searchNews(query: String): Observable<List<NewsEntity>> {
      return newsDao.searchNews(query)
    }
}