package com.prafullkumar.newsapp.domain.repositories

import com.prafullkumar.newsapp.domain.Resource
import com.prafullkumar.newsapp.domain.countryNewsDto.NewsDto
import kotlinx.coroutines.flow.Flow

interface SearchRepo {
    suspend fun searchNews(query: String): Flow<Resource<NewsDto>>
}