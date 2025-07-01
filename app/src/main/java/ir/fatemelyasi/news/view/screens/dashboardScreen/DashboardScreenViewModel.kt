package ir.fatemelyasi.news.view.screens.dashboardScreen

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ir.fatemelyasi.news.model.repository.newsRepository.NewsRepository
import ir.fatemelyasi.news.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.news.view.utils.ErrorState
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

    private val _error = BehaviorSubject.createDefault<ErrorState>(ErrorState.None)
    val error: Observable<ErrorState> = _error.hide()

    private val _loading = BehaviorSubject.createDefault(true)
    val loading: Observable<Boolean> = _loading.hide()

    private var hasLoadedInitialData = false

    fun fetchNewsItems() {

        val disposable = newsRepository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ articles ->
                _loading.onNext(false)
                _newsList.onNext(articles.take(DEFAULT_NEWS_ITEM_COUNT))
                hasLoadedInitialData = true
                _error.onNext(ErrorState.None)
            }, { throwable ->
                _loading.onNext(false)
                _error.onNext(ErrorState.Error(throwable))
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
                        _error.onNext(ErrorState.Error(throwable))
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
        _loading.onNext(true)

        val disposable = Completable.fromAction {
            newsRepository.deleteNews(listOf(article))
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val updatedList = _newsList.value.orEmpty().toMutableList()
                updatedList.remove(article)
                _newsList.onNext(updatedList)
                _loading.onNext(false)
            }, { throwable ->
                _error.onNext(ErrorState.Error(throwable))
                _loading.onNext(false)
            })

        disposables.add(disposable)
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}