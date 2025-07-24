package ir.fatemelyasi.news.view.screens.dashboardScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import ir.fatemelyasi.news.model.repository.newsRepository.NewsRepository
import ir.fatemelyasi.news.model.viewEntity.ArticleViewEntity
import org.koin.android.annotation.KoinViewModel
import java.util.concurrent.TimeUnit

@KoinViewModel
class DashboardScreenViewModel(
    private val newsRepository: NewsRepository,
) : ViewModel() {
    companion object {
        private const val DEFAULT_NEWS_ITEM_COUNT = 4
    }

    private val disposables = CompositeDisposable()

    private val _newsList = BehaviorSubject.create<List<ArticleViewEntity>>()
    val newsList: Observable<List<ArticleViewEntity>> = _newsList.hide()

    private val _query = BehaviorSubject.createDefault("")
    val query: Observable<String> = _query

    private val _error = PublishSubject.create<Throwable>()
    val error: Observable<Throwable> = _error.hide()

    private val _loading = BehaviorSubject.createDefault(true)
    val loading: Observable<Boolean> = _loading.hide()

    private val _isUserLoggedInSubject = BehaviorSubject.createDefault(false)
    var isUserLoggedIn by mutableStateOf(false)
        private set

    init {
        _isUserLoggedInSubject.subscribe { loggedIn ->
                isUserLoggedIn = loggedIn
            }
            .let { disposable -> disposables.add(disposable) }
    }

    private var hasLoadedInitialData = false

    fun fetchNewsItems() {
        if (hasLoadedInitialData == true) return

        val disposable = newsRepository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ articles ->
                _loading.onNext(false)
                _newsList.onNext(articles.take(DEFAULT_NEWS_ITEM_COUNT))
                hasLoadedInitialData = true
            }, { throwable ->
                _loading.onNext(false)
                _error.onNext(throwable)
            })

        disposables.add(disposable)
    }

    fun updateQuery(newQuery: String) {
        _query.onNext(newQuery)
    }

    fun searchNews() {
        val disposable = _query
            .debounce(300, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap { query ->

                newsRepository.searchNews(query)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe { _loading.onNext(true) }
                    .onErrorReturn { throwable ->
                        _error.onNext(throwable)
                        emptyList()
                    }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { articles ->
                _loading.onNext(false)
                _newsList.onNext(articles)
            }

        disposables.add(disposable)
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

        disposables.add(disposable)
    }

    fun loggedOut() = newsRepository.clearInformation()

    fun checkUserLoggedIn() {
        val isLoggedIn = newsRepository.isLoggedIn()
        _isUserLoggedInSubject.onNext(isLoggedIn)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}