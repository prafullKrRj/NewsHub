package com.prafullkumar.newsapp.domain.repositories

import com.prafullkumar.newsapp.domain.Resource
import com.prafullkumar.newsapp.domain.countryNewsDto.NewsCountryDto
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getNewsByCountry() : Flow<Resource<NewsCountryDto>>
}