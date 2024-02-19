package com.prafullkumar.newsapp.ui.articleFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prafullkumar.newsapp.data.local.Dao
import com.prafullkumar.newsapp.domain.countryNewsDto.Article
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleViewModel @Inject constructor(
    private val dao:  Dao
) : ViewModel() {


    fun saveArticle(article: Article) {
        viewModelScope.launch {
            dao.insertNews(article.toFavouriteEntity())
        }
    }
}