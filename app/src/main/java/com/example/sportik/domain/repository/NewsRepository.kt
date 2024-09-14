package com.example.sportik.domain.repository

import com.example.sportik.data.Resource
import com.example.sportik.domain.model.News
import com.example.sportik.domain.model.NewsWithContent
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(page: Int): Flow<Resource<List<News>>>

    suspend fun getNewsWithDetails(id: Int): Flow<Resource<NewsWithContent>>
}