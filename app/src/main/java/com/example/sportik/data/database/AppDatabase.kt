package com.example.sportik.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sportik.data.entity.NewsEntity

@Database(
    version = 1,
    entities = [NewsEntity::class]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}