package com.prafullkumar.newsapp.domain.countryNewsDto

import com.google.gson.annotations.SerializedName
import com.prafullkumar.newsapp.data.local.FavouriteEntity
import java.io.Serializable

data class Article(
    @SerializedName("author")
    val author: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("publishedAt")
    val publishedAt: String,

    @SerializedName("source")
    val source: Source?,

    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("urlToImage")
    val urlToImage: String
) : Serializable {
    fun toFavouriteEntity(): FavouriteEntity {
        return FavouriteEntity(
            author = author,
            content = content,
            description = description,
            publishedAt = publishedAt,
            title = title,
            url = url,
            urlToImage = urlToImage,
            source = source
        )
    }
}