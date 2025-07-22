package ir.fatemelyasi.news.view.screens.allArticleScreen

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ir.fatemelyasi.news.model.repository.newsRepository.NewsRepository
import ir.fatemelyasi.news.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.news.view.utils.stateHandling.ErrorState
import ir.fatemelyasi.news.R
import ir.fatemelyasi.news.view.utils.SortOrder
import org.koin.android.annotation.KoinViewModel
import kotlin.collections.orEmpty
import kotlin.collections.toMutableList

@KoinViewModel
class AllArticleScreenViewModel(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private var currentSortOrder: SortOrder? = null
    private var currentArticles: List<ArticleViewEntity> = emptyList()

    private val _articles = BehaviorSubject.create<List<ArticleViewEntity>>()
    val articles: Observable<List<ArticleViewEntity>> = _articles
        .distinctUntilChanged()
        .hide()

    private val _hasLoadedInitialData = BehaviorSubject.createDefault(false)

    private val _query = BehaviorSubject.createDefault("")
    val query: Observable<String> = _query

    private val _error = BehaviorSubject.createDefault<ErrorState>(ErrorState.None)
    val error: Observable<ErrorState> = _error.hide()

    private val _loading = BehaviorSubject.createDefault(true)
    val loading: Observable<Boolean> = _loading.hide()


    fun fetchArticles() {
        if (_hasLoadedInitialData.value == true && _articles.value.orEmpty().isNotEmpty()) return

        _loading.onNext(true)
        _error.onNext(ErrorState.None)

        val disposable = newsRepository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                _loading.onNext(false)
            }
            .subscribe(
                { result ->
                    currentArticles = result
                    _hasLoadedInitialData.onNext(true)
                    currentSortOrder?.let { sortArticles(it) } ?: _articles.onNext(result)
                },
                { throwable ->
                    _error.onNext(ErrorState.Message(R.string.unknown_error))
                }
            )
        addDisposable(disposable)
    }


    fun sortArticles(order: SortOrder) {
        currentSortOrder = order

        val sorted = when (order) {
            SortOrder.ASCENDING -> currentArticles.sortedBy { it.publishedAt?.take(10) }
            SortOrder.DESCENDING -> currentArticles.sortedByDescending { it.publishedAt?.take(10) }
        }
        val distinctSorted = sorted.distinctBy { it.publishedAt?.take(10) }

        _articles.onNext(distinctSorted)
    }

    fun deleteArticle(article: ArticleViewEntity) {
        val disposable = Completable.fromAction {
            newsRepository.deleteNews(listOf(article))
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                val updatedList = _articles.value.orEmpty().toMutableList()
                updatedList.remove(article)
                _articles.onNext(updatedList)

                fetchArticles()

            }, { it.printStackTrace() })

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
