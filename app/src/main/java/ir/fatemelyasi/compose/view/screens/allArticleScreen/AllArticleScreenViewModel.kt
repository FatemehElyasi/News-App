package ir.fatemelyasi.compose.view.screens.allArticleScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepository
import org.koin.android.annotation.KoinViewModel
import org.koin.java.KoinJavaComponent.inject

@KoinViewModel
class AllArticleScreenViewModel(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _articles = BehaviorSubject.create<List<ArticleViewEntity>>()
    val articles: Observable<List<ArticleViewEntity>> = _articles.hide()

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        val disposable = newsRepository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("AllArticleScreenViewModel", "Articles fetched: ${it.size}")
                    _articles.onNext(it)
                },
                {
                    Log.e("AllArticleScreenViewModel", "Error fetching articles", it)
                    it.printStackTrace()
                }
            )
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
