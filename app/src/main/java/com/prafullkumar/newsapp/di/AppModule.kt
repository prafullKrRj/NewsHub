package com.prafullkumar.newsapp.di

import android.app.Application
import androidx.room.Room
import com.prafullkumar.newsapp.BuildConfig
import com.prafullkumar.newsapp.data.NewsApiService
import com.prafullkumar.newsapp.data.local.Dao
import com.prafullkumar.newsapp.data.local.Database
import com.prafullkumar.newsapp.data.repositories.FavouritesRepoImpl
import com.prafullkumar.newsapp.data.repositories.HomeRepositoriesImpl
import com.prafullkumar.newsapp.data.repositories.SearchRepoImpl
import com.prafullkumar.newsapp.domain.repositories.FavouritesRepo
import com.prafullkumar.newsapp.domain.repositories.HomeRepository
import com.prafullkumar.newsapp.domain.repositories.SearchRepo
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

    @Provides
    @Singleton
    fun providesDatabase(context: Application): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "news_database"
        ).build()
    }
    @Provides
    @Singleton
    fun providesDao(database: Database): Dao {
        return database.dao()
    }
    @Provides
    @Singleton
    fun providesFavoriteRepository(dao: Dao): FavouritesRepo {
        return FavouritesRepoImpl(dao)
    }
    @Provides
    @Singleton
    fun providesSearchRepository(
        newsApiService: NewsApiService,
    ): SearchRepo {
        return SearchRepoImpl(newsApiService)
    }
}
