package com.example.sportik.domain.interactor

import com.example.sportik.data.Resource
import com.example.sportik.domain.model.News
import com.example.sportik.domain.model.NewsWithContent
import kotlinx.coroutines.flow.Flow

interface NewsInteractor {
    suspend fun getNews(): Flow<Resource<List<News>>>
    suspend fun getNewsWithDetails(id: Int): Flow<Resource<NewsWithContent>>
}