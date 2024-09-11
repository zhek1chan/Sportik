package com.example.sportik.di

import com.example.sportik.data.converters.DtoMappers
import com.example.sportik.data.network.NetworkClient
import com.example.sportik.data.repository.NewsRepositoryImpl
import com.example.sportik.domain.interactor.NewsInteractor
import com.example.sportik.domain.interactor.NewsInteractorImpl
import com.example.sportik.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    /*@Provides
    @Singleton
    fun provideVacancyRepositoryDb(dao: VacancyDao, networkClient: NetworkClient): VacancyRepositoryDb {
        return VacancyRepositoryDb(dao, networkClient)
    }

    @Provides
    @Singleton
    fun provideNewsWithDetailsFromFavs(newsRepository: VacancyRepositoryDb): GetDataByIdRepo<Resource<VacancyDetails>> {
        return newsRepository
    }

    @Provides
    @Singleton
    fun provideVacancySaveRepo(vacancyRepo: VacancyRepositoryDb): SaveDataRepo<VacancyDetails> {
        return vacancyRepo
    }

    @Provides
    @Singleton
    fun provideVacancyDeleteRepo(vacancyRepo: VacancyRepositoryDb): DeleteDataRepo<String> {
        return vacancyRepo
    } */

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
    fun provideDtoMapper (): DtoMappers {
        return DtoMappers()
    }

    companion object {
        const val NEWS_REPOSITORY = "provideInteractorImpl"
    }
}