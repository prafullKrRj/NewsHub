package com.prafullkumar.newsapp.data.repositories

import com.prafullkumar.newsapp.BuildConfig
import com.prafullkumar.newsapp.data.NewsApiService
import com.prafullkumar.newsapp.domain.Resource
import com.prafullkumar.newsapp.domain.countryNewsDto.NewsDto
import com.prafullkumar.newsapp.domain.repositories.SearchRepo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class SearchRepoImpl @Inject constructor(
    private val newsApiService: NewsApiService,
): SearchRepo {
    override suspend fun searchNews(query: String): Flow<Resource<NewsDto>> {
        return callbackFlow {
            trySend(Resource.Loading)
            try {
                val response = newsApiService.getSearchNews(
                    query,
                    BuildConfig.API_KEY,
                    1
                )
                if (response.status != "ok") {
                    trySend(Resource.Error(Exception("An unknown error occurred")))
                } else {
                    trySend(Resource.Success(response))
                }
            } catch (e: Exception) {
                trySend(Resource.Error(e))
            }
            awaitClose { }
        }
    }

}