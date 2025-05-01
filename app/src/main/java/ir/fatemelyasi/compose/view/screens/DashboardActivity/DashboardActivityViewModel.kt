package ir.fatemelyasi.compose.view.screens.DashboardActivity

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ir.fatemelyasi.compose.model.ViewEntity.ArticleViewEntity
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepository

class DashboardActivityViewModel(
    private val newsRepository: NewsRepository,
    ) : ViewModel() {


    val newsSubject: BehaviorSubject<List<ArticleViewEntity>> = BehaviorSubject.create()

    private val disposables = CompositeDisposable()

    //get list of news
    fun getNews(): Observable<List<ArticleViewEntity>> {
        return newsRepository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    //getBanners Item
    /*
    fun getBannersItem(): Observable<List<ArticleViewEntity>> {
        return newsRepository.getBanners()
    }
     */

}