package com.prafullkumar.newsapp.domain.repositories

import com.prafullkumar.newsapp.data.local.FavouriteEntity
import kotlinx.coroutines.flow.Flow

interface FavouritesRepo {
    fun getFavouritesFromDB(): Flow<List<FavouriteEntity>>
    suspend fun deleteFavouriteFromDB(favouriteEntity: FavouriteEntity)
}