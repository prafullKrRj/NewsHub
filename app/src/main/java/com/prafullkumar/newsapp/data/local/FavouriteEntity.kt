package com.prafullkumar.newsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.prafullkumar.newsapp.domain.countryNewsDto.Source


@Entity(tableName = "favourite_table")
data class FavouriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val source: Source?
)

class SourceTypeConverter {
    @TypeConverter
    fun fromSource(source: Source?): String? {
        return source?.name
    }
    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}