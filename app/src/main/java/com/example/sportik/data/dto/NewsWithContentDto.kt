package com.example.sportik.data.dto

data class NewsWithContentDto(
    val newsId: Long,
    val title: String,
    val commentCount: String,
    val socialImage: String,
    val postedTime: String,
    val content: String
)
