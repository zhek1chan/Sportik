package com.example.sportik.domain.interactor

import com.example.sportik.data.Resource
import com.example.sportik.domain.model.News
import com.example.sportik.domain.model.NewsWithContent
import com.example.sportik.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsInteractorImpl(
    private val repository: NewsRepository
) : NewsInteractor {
    override suspend fun getNews(): Flow<Resource<List<News>>> =
        repository.getNews()

    override suspend fun getNewsWithDetails(id: Long): Flow<Resource<NewsWithContent>> =
        repository.getNewsWithDetails(id)
}