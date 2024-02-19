package com.prafullkumar.newsapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: FavouriteEntity)

    @Query("SELECT * FROM favourite_table")
    fun getFavouriteNews(): Flow<List<FavouriteEntity>>

    @Delete
    suspend fun deleteNews(news: FavouriteEntity)
}
