package com.example.sportik.data.repository

import com.example.sportik.data.Resource
import com.example.sportik.data.converters.DtoMappers
import com.example.sportik.data.network.NetworkClient
import com.example.sportik.domain.model.News
import com.example.sportik.domain.model.NewsWithContent
import com.example.sportik.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: DtoMappers
) : NewsRepository {
    override suspend fun getNews(page: Int): Flow<Resource<List<News>>> = flow {
        when (val response = networkClient.getNews(page)) {
            is Resource.Data -> {
                with(response) {
                    val data = mapper.newsDtoToNews(value.news)
                    emit(Resource.Data(data))
                }
            }

            is Resource.NotFound -> emit(Resource.NotFound(response.message))
            is Resource.ConnectionError -> {
                emit(Resource.ConnectionError(response.message))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getNewsWithDetails(id: Int): Flow<Resource<NewsWithContent>> = flow {
        when (val response = networkClient.getNewsWithDetails(id)) {
            is Resource.Data -> {
                with(response) {
                    val data = mapper.newsWithContentDtoToNewsWithContent(value)
                    emit(Resource.Data(data))
                }
            }

            is Resource.NotFound -> emit(Resource.NotFound(response.message))
            is Resource.ConnectionError -> {
                emit(Resource.ConnectionError(response.message))
            }
        }
    }.flowOn(Dispatchers.IO)
}