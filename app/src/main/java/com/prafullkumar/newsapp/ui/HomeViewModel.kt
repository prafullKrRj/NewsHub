package com.prafullkumar.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prafullkumar.newsapp.domain.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

    private val _counter by lazy { MutableStateFlow(0) }
    val counter: StateFlow<Int> = _counter.asStateFlow()


    init {
        viewModelScope.launch(context = Dispatchers.IO) {
            for (i in 1..10) {
                delay(timeMillis = 1000)
                _counter.update { it + 1 }
            }
        }
    }
}