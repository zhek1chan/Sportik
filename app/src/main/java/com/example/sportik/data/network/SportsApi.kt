package com.example.sportik.data.network

import com.example.sportik.data.dto.ListNewsDto
import com.example.sportik.data.dto.NewsWithContentDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsApi {
    @GET("/stat/export/iphone/news.json?category_id=208&from=0&count=10")
    suspend fun getNews(): Response<ListNewsDto>

    @GET("/stat/export/iphone/news_item.json")
    suspend fun getNewsWithDetails(@Query("id") id: Int): Response<NewsWithContentDto>

    @GET("/stat/export/iphone/news.json?category_id=208")
    suspend fun getNewsPaginated(
        @Query("from") from: Int,
        @Query("count") count: Int
    ): Response<ListNewsDto>
}