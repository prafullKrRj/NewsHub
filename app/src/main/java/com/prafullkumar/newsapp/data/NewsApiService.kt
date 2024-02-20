package com.prafullkumar.newsapp.data

import com.prafullkumar.newsapp.domain.countryNewsDto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/v2/top-headlines")
    suspend fun getCountryNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int
    ) : NewsDto

    @GET("/everything/")
    suspend fun getSearchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int = 1
    ) : NewsDto
}