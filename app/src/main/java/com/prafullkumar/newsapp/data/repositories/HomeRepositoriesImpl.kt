package com.prafullkumar.newsapp.data.repositories

import android.util.Log
import com.prafullkumar.newsapp.data.NewsApiService
import com.prafullkumar.newsapp.domain.Resource
import com.prafullkumar.newsapp.domain.countryNewsDto.NewsDto
import com.prafullkumar.newsapp.domain.repositories.HomeRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class HomeRepositoriesImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val apiKey: String
) : HomeRepository {

    override suspend fun getNewsByCountry(page: Int): Flow<Resource<NewsDto>> {
        return callbackFlow {
            trySend(Resource.Loading)
            try {
                val response = newsApiService.getCountryNews("in", apiKey, page)
                if (response.status != "ok") {
                    trySend(Resource.Error(Exception("An unknown error occurred")))
                } else {
                    trySend(Resource.Success(response))
                }
            } catch (e: Exception) {
                trySend(Resource.Error(e))
            }
            awaitClose {}
        }
    }
}