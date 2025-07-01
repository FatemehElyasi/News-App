package ir.fatemelyasi.news.view.screens.articleDetailScreen

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ir.fatemelyasi.news.model.repository.newsRepository.NewsRepository
import ir.fatemelyasi.news.model.viewEntity.ArticleViewEntity
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ArticleDetailScreenViewModel(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _articleSubject = BehaviorSubject.create<ArticleViewEntity>()
    val articleObservable: Observable<ArticleViewEntity> = _articleSubject.hide()

    fun setArticle(article: ArticleViewEntity) {
        _articleSubject.onNext(article)

    }
    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}
