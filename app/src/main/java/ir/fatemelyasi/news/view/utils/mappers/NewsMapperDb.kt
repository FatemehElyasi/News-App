package ir.fatemelyasi.news.view.utils.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import ir.fatemelyasi.news.model.local.NewsEntity
import ir.fatemelyasi.news.model.viewEntity.ArticleViewEntity
import ir.fatemelyasi.news.view.utils.toFormattedDate


@RequiresApi(Build.VERSION_CODES.O)
fun NewsEntity.toViewEntity(): ArticleViewEntity = ArticleViewEntity(
    title = this.title,
    description = this.description,
    urlToImage = this.urlToImage ?: "",
    publishedAt = this.publishedAt.toFormattedDate()
)

@RequiresApi(Build.VERSION_CODES.O)
fun ArticleViewEntity.toViewEntity(): NewsEntity = NewsEntity(
    id = null,
    title = this.title,
    description = this.description ?: "",
    urlToImage = this.urlToImage,
    publishedAt = this.publishedAt.toFormattedDate()
)


