package com.example.sportik.di

import com.example.sportik.data.converters.DtoMappers
import com.example.sportik.data.database.AppDatabase
import com.example.sportik.data.network.NetworkClient
import com.example.sportik.data.repository.FavouritesRepositoryImpl
import com.example.sportik.data.repository.NewsRepositoryImpl
import com.example.sportik.domain.interactor.FavouritesInteractor
import com.example.sportik.domain.interactor.FavouritesinteractorImpl
import com.example.sportik.domain.interactor.NewsInteractor
import com.example.sportik.domain.interactor.NewsInteractorImpl
import com.example.sportik.domain.repository.FavouritesRepository
import com.example.sportik.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideInteractorImpl(
        newsRepository: NewsRepository,
    ): NewsInteractor {
        return NewsInteractorImpl(newsRepository)
    }

    @Provides
    @Singleton
    fun provideNewsRepoImpl(
        networkClient: NetworkClient,
        dtoMappers: DtoMappers
    ): NewsRepository {
        return NewsRepositoryImpl(networkClient, dtoMappers)
    }

    @Provides
    @Singleton
    fun provideFavRepositoryImpl(
        db: AppDatabase, mappers: DtoMappers
    ): FavouritesRepository {
        return FavouritesRepositoryImpl(db, mappers)
    }

    @Provides
    @Singleton
    fun provideFavInteractorImpl(
        repository: FavouritesRepository,
    ): FavouritesInteractor {
        return FavouritesinteractorImpl(repository)
    }

    @Provides
    fun provideDtoMapper (): DtoMappers {
        return DtoMappers()
    }

}