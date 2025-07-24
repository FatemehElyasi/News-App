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
import ir.fatemelyasi.news.view.utils.SortOrder
import org.koin.android.annotation.KoinViewModel

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

    fun fetchArticles() {
        if (_hasLoadedInitialData.value == true) return
        _hasLoadedInitialData.onNext(true)

        val disposable = newsRepository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    currentArticles = it
                    if (currentSortOrder != null) {
                        sortArticles(currentSortOrder!!)
                    } else {
                        _articles.onNext(it)
                    }
                },
                { it.printStackTrace() }
            )
        addDisposable(disposable)
    }


    fun sortArticles(order: SortOrder) {
        currentSortOrder = order

        val sorted = when (order) {
            SortOrder.ASCENDING -> currentArticles.sortedBy { it.publishedAt }
            SortOrder.DESCENDING -> currentArticles.sortedByDescending { it.publishedAt }
        }
        val distinctSorted = sorted.distinctBy { it.publishedAt }

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

