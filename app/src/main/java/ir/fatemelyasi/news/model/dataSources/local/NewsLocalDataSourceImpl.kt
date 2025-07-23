package ir.fatemelyasi.news.model.dataSources.local

import io.reactivex.rxjava3.core.Observable
import ir.fatemelyasi.news.model.local.NewsDao
import ir.fatemelyasi.news.model.local.NewsEntity
import ir.fatemelyasi.news.model.sharedPrefHelper.SharedPrefHelper
import org.koin.core.annotation.Single

@Single
class NewsLocalDataSourceImpl(
    private val newsDao: NewsDao,
    private val sharedPrefHelper: SharedPrefHelper
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

    override fun saveUserAuthenticationInfo(email: String, password: String, isLoggedIn: Boolean) {
        return sharedPrefHelper.saveInfo(email, password, isLoggedIn)
    }
    override fun getEmail(): String? {
        return sharedPrefHelper.getEmail()
    }
    override fun getPassword(): String? {
        return sharedPrefHelper.getPassword()
    }
    override fun isLoggedIn(): Boolean {
        return sharedPrefHelper.isLoggedIn()
    }
    override fun clearInformation() {
        return sharedPrefHelper.logOut()
    }
}