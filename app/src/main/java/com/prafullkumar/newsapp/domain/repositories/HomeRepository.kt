package com.prafullkumar.newsapp.domain.repositories

import com.prafullkumar.newsapp.domain.Resource
import com.prafullkumar.newsapp.domain.countryNewsDto.NewsDto
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getNewsByCountry() : Flow<Resource<NewsDto>>
}