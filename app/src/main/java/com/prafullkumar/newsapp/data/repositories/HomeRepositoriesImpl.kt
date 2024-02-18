package com.prafullkumar.newsapp.data.repositories

import com.prafullkumar.newsapp.data.NewsApiService
import com.prafullkumar.newsapp.domain.Resource
import com.prafullkumar.newsapp.domain.countryNewsDto.NewsCountryDto
import com.prafullkumar.newsapp.domain.repositories.HomeRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class HomeRepositoriesImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val apiKey: String
) : HomeRepository {

    
    override suspend fun getNewsByCountry(): Flow<Resource<NewsCountryDto>> {
        return callbackFlow {
            trySend(Resource.Loading)
            try {
                val response = newsApiService.getCountryNews("in", apiKey)
               trySend(response)
            } catch (e: Exception) {
                trySend(Resource.Error(e))
            }
            awaitClose {}
        }
    }

}