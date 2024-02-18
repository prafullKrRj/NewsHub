package com.prafullkumar.newsapp.domain.countryNewsDto

import com.google.gson.annotations.SerializedName

data class NewsCountryDto(
    @SerializedName("articles")
    val articles: List<Article>,

    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int
)