package ir.fatemelyasi.compose.model.di

import android.content.Context
import androidx.room.Room
import ir.fatemelyasi.compose.model.local.NewsDao
import ir.fatemelyasi.compose.model.local.NewsDatabase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.dsl.module

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

