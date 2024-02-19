package com.prafullkumar.newsapp.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.prafullkumar.newsapp.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_activity)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

}