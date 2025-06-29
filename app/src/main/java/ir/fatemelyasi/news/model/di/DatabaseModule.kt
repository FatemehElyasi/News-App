package ir.fatemelyasi.news.model.di

import android.content.Context
import androidx.room.Room
import ir.fatemelyasi.news.model.local.NewsDao
import ir.fatemelyasi.news.model.local.NewsDatabase
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class DatabaseModule {
    @Single
    fun provideDatabase(context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            "news-db"
        ).build()
    }

    @Single
    fun provideDao(newsDatabase: NewsDatabase): NewsDao {
        return newsDatabase.newsDao()
    }
}

