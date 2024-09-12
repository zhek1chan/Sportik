package com.example.sportik.data.repository

import com.example.sportik.data.converters.DtoMappers
import com.example.sportik.data.database.AppDatabase
import com.example.sportik.data.entity.NewsEntity
import com.example.sportik.domain.model.NewsWithContent
import com.example.sportik.domain.repository.FavouritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavouritesRepositoryImpl(
    private val db: AppDatabase,
    private val mappers: DtoMappers
) :
    FavouritesRepository {
    override fun add(item: NewsWithContent) {
        db.newsDao().insertNews(mappers.newsWithContentToNewsEntity(item))
    }

    override fun delete(id: Int) {
        db.newsDao().deleteNews(id)
    }

    override suspend fun get(id: Int): Flow<NewsWithContent> = flow {
        emit(mappers.newsEntityToNewsWithContent(db.newsDao().queryId(id)!!))
    }

    override suspend fun getAll(): Flow<List<NewsWithContent>> = flow {
        val tracks = db.newsDao().getNews()
        emit(convertFromEntity(tracks.reversed()))
    }

    override suspend fun favouritesCheck(id: Int): Flow<Boolean> = flow {
        emit(db.newsDao().queryId(id) != null)
    }

    override fun deleteAll() {
        db.newsDao().deleteAllNews()
    }

    private fun convertFromEntity(list: List<NewsEntity>): List<NewsWithContent> {
        return list.map { item -> mappers.newsEntityToNewsWithContent(item) }
    }
}