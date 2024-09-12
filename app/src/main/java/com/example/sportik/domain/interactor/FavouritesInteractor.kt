package com.example.sportik.domain.interactor

import com.example.sportik.domain.model.NewsWithContent
import kotlinx.coroutines.flow.Flow

interface FavouritesInteractor {
    fun add(item: NewsWithContent)
    fun delete(id: Int)
    suspend fun get(id: Int): Flow<NewsWithContent>
    suspend fun getAll(): Flow<List<NewsWithContent>>
    suspend fun favouritesCheck(id: Int): Flow<Boolean>
    fun deleteAll()
}