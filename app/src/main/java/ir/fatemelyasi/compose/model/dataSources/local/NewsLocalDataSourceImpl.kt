package ir.fatemelyasi.compose.model.dataSources.local

import io.reactivex.rxjava3.core.Observable
import ir.fatemelyasi.compose.model.local.NewsDao
import ir.fatemelyasi.compose.model.local.NewsEntity
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity
import org.koin.core.annotation.Single

@Single
class NewsLocalDataSourceImpl(
    private val newsDao: NewsDao
) : NewsLocalDataSource {

    override fun saveNewsToDb(newsEntityModel: List<NewsEntity>) {
       newsDao.saveNewsToDb(newsEntityModel)
    }

    override fun deleteNews(newsEntityModel: List<NewsEntity>) {
       newsDao.deleteNews(newsEntityModel)
    }

    override fun getAllNews(): Observable<List<NewsEntity>> {
        return newsDao.getAllNews()
    }

    override fun getTopNews(count: Int): Observable<List<NewsEntity>> {
        return newsDao.getTopNews(count)
    }


    override fun searchNews(query: String): Observable<List<NewsEntity>> {
      return newsDao.searchNews(query)
    }
}