package com.prafullkumar.newsapp.ui.favouriteFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prafullkumar.newsapp.data.local.FavouriteEntity
import com.prafullkumar.newsapp.domain.repositories.FavouritesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val repository: FavouritesRepo
) : ViewModel() {

    val favouriteList: StateFlow<List<FavouriteEntity>> = repository.getFavouritesFromDB()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun deleteFavourite(entity: FavouriteEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavouriteFromDB(entity)
        }
    }
}