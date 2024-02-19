package com.prafullkumar.newsapp.ui.searchFragment

import androidx.lifecycle.ViewModel
import com.prafullkumar.newsapp.domain.repositories.SearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor (
    private val repository: SearchRepo
): ViewModel() {

}