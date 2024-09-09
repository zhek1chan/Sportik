package com.example.sportik.domain.model

data class NewsWithContent(
    val newsId: Long,
    val title: String,
    val commentCount: String,
    val socialImage: String,
    val postedTime: String,
    val content: String
)
