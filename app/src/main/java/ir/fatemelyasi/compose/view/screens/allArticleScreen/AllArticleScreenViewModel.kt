package ir.fatemelyasi.compose.view.screens.allArticleScreen

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ir.fatemelyasi.compose.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.compose.model.repository.newsRepository.NewsRepository

class AllArticleScreenViewModel(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _articles = BehaviorSubject.create<List<ArticleViewEntity>>()
    val articles: Observable<List<ArticleViewEntity>> = _articles.hide()

    private fun fetchArticles() {
        val disposable = newsRepository.getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _articles.onNext(it) },
                { it.printStackTrace() }
            )
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}