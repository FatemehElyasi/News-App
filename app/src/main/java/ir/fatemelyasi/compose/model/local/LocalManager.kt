package ir.fatemelyasi.compose.model.local

import android.content.Context
import androidx.room.Room

object LocalManager {

    @Volatile
    private var instance: NewsDatabase? = null

    private fun getRoomInstance(context: Context): NewsDatabase {
        return instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "news_db"
            ).build().also { instance = it }
        }
    }

    fun getNewsDao(context: Context): NewsDao {
        return getRoomInstance(context).newsDao()
    }
}




