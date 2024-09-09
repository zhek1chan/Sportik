package com.example.sportik.data.network

import com.example.sportik.data.Resource
import com.example.sportik.data.dto.NewsDto
import com.example.sportik.data.dto.NewsWithContentDto

interface NetworkClient {

    suspend fun getNews(): Resource<NewsDto>

    suspend fun getNewsWithDetails(): Resource<NewsWithContentDto>
}