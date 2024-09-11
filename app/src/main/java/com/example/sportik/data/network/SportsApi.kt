package com.example.sportik.data.network

import com.example.sportik.data.dto.ListNewsDto
import com.example.sportik.data.dto.NewsWithContentDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SportsApi {
    @GET("/stat/export/iphone/news.json?category_id=208&from=0&count=10")
    suspend fun getNews(): Response<ListNewsDto>

    @GET("/stat/export/iphone/news_item.json?id={id}")
    suspend fun getNewsWithDetails(@Path("id") id: Long): Response<NewsWithContentDto>
}