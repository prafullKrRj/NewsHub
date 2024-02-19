package com.prafullkumar.newsapp.domain.repositories

import androidx.lifecycle.LiveData
import com.prafullkumar.newsapp.data.local.FavouriteEntity

interface FavouritesRepo {
    suspend fun getFavouritesFromDB(): LiveData<List<FavouriteEntity>>
    suspend fun deleteFavouriteFromDB(favouriteEntity: FavouriteEntity)
}