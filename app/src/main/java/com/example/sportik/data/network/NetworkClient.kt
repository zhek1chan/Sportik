package com.example.sportik.data.network

import com.example.sportik.data.Resource
import com.example.sportik.data.dto.ListNewsDto
import com.example.sportik.data.dto.NewsWithContentDto

interface NetworkClient {

    suspend fun getNews(): Resource<ListNewsDto>

    suspend fun getNewsWithDetails(id: Long): Resource<NewsWithContentDto>
}