package com.prafullkumar.newsapp.data.repositories

import androidx.lifecycle.LiveData
import com.prafullkumar.newsapp.data.local.Dao
import com.prafullkumar.newsapp.data.local.FavouriteEntity
import com.prafullkumar.newsapp.domain.repositories.FavouritesRepo
import javax.inject.Inject

class FavouritesRepoImpl @Inject constructor(
    private val dao: Dao,
) : FavouritesRepo {
    override suspend fun getFavouritesFromDB(): LiveData<List<FavouriteEntity>> {
        return dao.getFavouriteNews()
    }

    override suspend fun deleteFavouriteFromDB(favouriteEntity: FavouriteEntity) {
        dao.deleteNews(favouriteEntity)
    }

}