package com.prafullkumar.newsapp.ui.headlinesFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prafullkumar.newsapp.domain.Resource
import com.prafullkumar.newsapp.domain.countryNewsDto.Article
import com.prafullkumar.newsapp.domain.countryNewsDto.NewsDto
import com.prafullkumar.newsapp.domain.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _headlines = MutableStateFlow<Resource<NewsDto>>(Resource.Initial)
    val headlines = _headlines.asStateFlow()
    private var pageNumber = 1
    init {
        getHeadlines()
    }
    fun getHeadlines() {
        viewModelScope.launch {
            repository.getNewsByCountry(pageNumber).collect { resp ->
                when (resp) {
                    is Resource.Success -> {
                        _headlines.update {
                            Resource.Success(resp.data)
                        }
                        pageNumber++
                    }
                    is Resource.Error -> {
                        _headlines.update {
                            Resource.Error(resp.exception)
                        }
                        Log.d("HeadlinesViewModel", "getHeadlines: ${resp.exception.message}")
                    }
                    is Resource.Loading -> {
                        _headlines.update {
                            Resource.Loading
                        }
                    } else -> {
                        _headlines.update {
                            Resource.Initial
                        }
                    }
                }
            }
        }
    }
}