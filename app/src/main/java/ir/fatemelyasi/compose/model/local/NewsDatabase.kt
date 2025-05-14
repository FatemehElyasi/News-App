package ir.fatemelyasi.compose.model.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.reactivex.rxjava3.schedulers.Schedulers.single
import org.koin.dsl.module
import kotlin.jvm.java

@Database(entities = [NewsEntity::class], version = 1 )
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao() : NewsDao

}

