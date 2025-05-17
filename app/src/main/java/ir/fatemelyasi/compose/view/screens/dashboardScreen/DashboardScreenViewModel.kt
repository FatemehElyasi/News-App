package ir.fatemelyasi.compose.view.screens.dashboardScreen

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepository
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class DashboardScreenViewModel(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val disposables = CompositeDisposable()

    //save value = BehaviorSubject / get value = Observable
    private val _newsList = BehaviorSubject.create<List<ArticleViewEntity>>()
    val newsList: Observable<List<ArticleViewEntity>> = _newsList.hide()

    private val _loading = BehaviorSubject.createDefault(true)
    val loading: Observable<Boolean> = _loading.hide()

    private val _error = PublishSubject.create<Throwable>()
    val error: Observable<Throwable> = _error.hide()

    private val _hasLoadedInitialData = BehaviorSubject.createDefault(false)


    //get list of news
    fun fetchNewsItems(count: Int) {
        if (_hasLoadedInitialData.value == true) return
        _hasLoadedInitialData.onNext(true)

        val disposable = newsRepository.getTopNewsFromDb(count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { articles ->
                    _loading.onNext(false)
                    _newsList.onNext(articles)
                }, { throwable ->
                    _loading.onNext(false)
                    _error.onNext(throwable)
                })
        addDisposable(disposable)
    }

    fun searchNews(query: String) {
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

        addDisposable(disposable)
    }

    fun deleteArticle(article: ArticleViewEntity) {
        val disposable = Completable.fromAction {
            newsRepository.deleteNews(listOf(article))
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val updatedList = _newsList.value.orEmpty().toMutableList()
                updatedList.remove(article)
                _newsList.onNext(updatedList)

            }, {
                _error.onNext(it)
            })

        addDisposable(disposable)
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}