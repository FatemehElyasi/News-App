package ir.fatemelyasi.news.model.dataSources.local.newsLocalDataSource

import io.reactivex.rxjava3.core.Observable
import ir.fatemelyasi.news.model.local.NewsDao
import ir.fatemelyasi.news.model.local.NewsEntity
import ir.fatemelyasi.news.model.sharedPrefHelper.SharedPrefHelper
import org.koin.core.annotation.Single

@Single
class NewsLocalDataSourceImpl(
    private val newsDao: NewsDao,
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