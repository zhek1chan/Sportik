package com.example.sportik.domain.interactor

import com.example.sportik.domain.model.NewsWithContent
import com.example.sportik.domain.repository.FavouritesRepository
import kotlinx.coroutines.flow.Flow

class FavouritesinteractorImpl(private val repository: FavouritesRepository) :
    FavouritesInteractor {
    override fun add(item: NewsWithContent) {
        repository.add(item)
    }

    override fun delete(id: Int) {
        repository.delete(id)
    }

    override suspend fun getAll(): Flow<List<NewsWithContent>> {
        return repository.getAll()
    }

    override suspend fun get(id: Int): Flow<NewsWithContent> {
        return repository.get(id)
    }

    override suspend fun favouritesCheck(id: Int): Flow<Boolean> {
        return repository.favouritesCheck(id)
    }

    override fun deleteAll() {
        repository.deleteAll()
    }
}