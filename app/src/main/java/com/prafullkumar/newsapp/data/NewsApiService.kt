package com.prafullkumar.newsapp.data

import com.prafullkumar.newsapp.domain.Resource
import com.prafullkumar.newsapp.domain.countryNewsDto.NewsCountryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApiService {

    @GET("/top-headlines/")
    suspend fun getCountryNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ) : Resource<NewsCountryDto>
}