package com.example.sportik.data.dto

data class NewsDto(
    val newsId: Long,
    val title: String,
    val content: String,
    val comment_count: String,
    val social_image: String,
    val posted_time: String
)
