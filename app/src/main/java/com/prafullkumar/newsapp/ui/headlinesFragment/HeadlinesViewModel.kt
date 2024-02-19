package com.prafullkumar.newsapp.ui.headlinesFragment

import androidx.lifecycle.ViewModel
import com.prafullkumar.newsapp.domain.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {



}