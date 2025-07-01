package ir.fatemelyasi.news.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.rxjava3.core.Observable

@Dao
interface NewsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNewsToDb(newsEntity: List<NewsEntity>)

    @Delete
    fun deleteNews(newsEntity: List<NewsEntity>)

    //Observable for getting live data
    @Query(" SELECT * FROM NEWS_TABLE ")
    fun getAllNews(): Observable<List<NewsEntity>>

    @Query("SELECT * FROM NEWS_TABLE LIMIT :count")
    fun getTopNews(count: Int): Observable<List<NewsEntity>>

    @Query(" SELECT * FROM NEWS_TABLE WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' ")
    fun searchNews(query: String): Observable<List<NewsEntity>>

    //optional
    @Update
    fun updateNews(vararg newsEntity: NewsEntity)


}