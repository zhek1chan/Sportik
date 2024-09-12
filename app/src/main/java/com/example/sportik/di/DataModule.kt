package com.example.sportik.di

import android.content.Context
import androidx.room.Room
import com.example.sportik.data.database.AppDatabase
import com.example.sportik.data.database.NewsDao
import com.example.sportik.data.network.NetworkClient
import com.example.sportik.data.network.RetrofitNetworkClient
import com.example.sportik.data.network.SportsApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "database.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideNewsDao(appDatabase: AppDatabase): NewsDao =
        appDatabase.newsDao()

    @Provides
    @Singleton
    fun provideApi(): SportsApi {
        val client = OkHttpClient()
            .newBuilder()
            .build()

        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.sports.ru")
            .build()
            .create(SportsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitNetworkClient(
        @ApplicationContext context: Context,
        api: SportsApi
    ): NetworkClient {
        return RetrofitNetworkClient(context, api)
    }


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}