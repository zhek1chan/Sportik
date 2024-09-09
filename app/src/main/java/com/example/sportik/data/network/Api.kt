package com.example.sportik.data.network

import com.example.sportik.data.dto.NewsDto
import com.example.sportik.data.dto.NewsWithContentDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("https://www.sports.ru/stat/export/iphone/news.json?category_id=208&from=0&count=10\n")
    suspend fun getNews(): Response<NewsDto>

    @GET("https://www.sports.ru/stat/export/iphone/news_item.json?id={id}")
    suspend fun getNewsWithDetails(@Path("id") id: Long): Response<NewsWithContentDto>
}