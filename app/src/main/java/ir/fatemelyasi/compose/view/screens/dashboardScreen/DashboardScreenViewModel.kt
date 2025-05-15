package ir.fatemelyasi.compose.view.screens.dashboardScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepository
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class DashboardScreenViewModel(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val disposables = CompositeDisposable()

    //save value = BehaviorSubject / get value = Observable
    private val _newsList = BehaviorSubject.create<List<ArticleViewEntity>>()
    val newsList: Observable<List<ArticleViewEntity>> = _newsList.hide()

    private val _loading = BehaviorSubject.createDefault(false)
    val loading: Observable<Boolean> = _loading.hide()

    private val _error = PublishSubject.create<Throwable>()
    val error: Observable<Throwable> = _error.hide()

    //get list of news
    fun fetchNews() {
        _loading.onNext(true)
        val disposable = newsRepository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ articles ->

                Log.e("NewsFetch DashboardScreenViewModel", "Articles fetched: $articles")

                _loading.onNext(false)
                _newsList.onNext(articles)

            }, { throwable ->

                Log.e("NewsFetch DashboardScreenViewModel", "Error: ", throwable)

                _loading.onNext(false)
                _error.onNext(throwable)
            })
        disposables.add(disposable)
    }

    fun searchNews(query: String) {
        _loading.onNext(true)

        val disposable = newsRepository.searchNews(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ articles ->
                _loading.onNext(false)
                _newsList.onNext(articles)
            }, { throwable ->
                _loading.onNext(false)
                _error.onNext(throwable)
            })

        disposables.add(disposable)
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}