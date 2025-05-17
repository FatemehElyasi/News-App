package ir.fatemelyasi.compose.view.screens.allArticleScreen

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepository
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity
import org.koin.android.annotation.KoinViewModel
import kotlin.collections.orEmpty
import kotlin.collections.toMutableList

@KoinViewModel
class AllArticleScreenViewModel(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _articles = BehaviorSubject.create<List<ArticleViewEntity>>()
    val articles: Observable<List<ArticleViewEntity>> = _articles.hide()

    private val _hasLoadedInitialData = BehaviorSubject.createDefault(false)

    fun fetchArticles() {
        _hasLoadedInitialData.onNext(true)

        val disposable = newsRepository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _articles.onNext(it) },
                { it.printStackTrace() }
            )
        addDisposable(disposable)
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
