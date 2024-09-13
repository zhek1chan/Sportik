package com.example.sportik.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportik.data.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(item: NewsEntity)

    @Query("SELECT * FROM news_table ORDER BY addedDate DESC")
    fun getNews(): List<NewsEntity>

    @Query("DELETE FROM news_table WHERE id = :itemId")
    fun deleteNews(itemId: Int)

    @Query("DELETE FROM news_table")
    fun deleteAllNews()

    @Query("SELECT * FROM news_table WHERE id=:searchId")
    fun queryId(searchId: Int): NewsEntity?
}