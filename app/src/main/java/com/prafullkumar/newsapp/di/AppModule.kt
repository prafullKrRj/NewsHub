package com.prafullkumar.newsapp.di

import com.prafullkumar.newsapp.BuildConfig
import com.prafullkumar.newsapp.data.NewsApiService
import com.prafullkumar.newsapp.data.repositories.HomeRepositoriesImpl
import com.prafullkumar.newsapp.domain.repositories.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesApiService(): NewsApiService {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .build()
            .create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @Singleton
    fun providesHomeRepository(
        newsApiService: NewsApiService,
        apiKey: String
    ): HomeRepository {
        return HomeRepositoriesImpl(newsApiService, apiKey)
    }
}
