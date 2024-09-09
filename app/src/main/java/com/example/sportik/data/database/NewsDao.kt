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

    @Query("SELECT * FROM news_table")
    fun getNews(): List<NewsEntity>

    //@Query("SELECT newsId FROM news_table")
    //fun getPlaylistId(): Flow<List<Long>>

    @Query("DELETE FROM news_table WHERE newsId = :itemId")
    fun deleteNews(itemId: Long)

    @Query("DROP TABLE news_table")
    fun deleteAllNews(playlistId: Long)
}