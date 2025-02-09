package com.example.sportik.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class NewsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val commentCount: String,
    val socialImage: String,
    val postedTime: String,
    val content: String,
    val addedDate: Long
)