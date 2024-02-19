package com.prafullkumar.newsapp.ui.headlinesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton
import com.prafullkumar.newsapp.R
import com.prafullkumar.newsapp.adapters.ArticleAdapter
import com.prafullkumar.newsapp.databinding.FragmentHeadlinesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeadlinesFragment : Fragment(R.layout.fragment_headlines) {


    private val viewModel: HeadlinesViewModel by viewModels()
    lateinit var binding: FragmentHeadlinesBinding
    lateinit var headlinesAdapter: ArticleAdapter
    lateinit var retryButton:  MaterialButton
    lateinit var errorText: TextView
    lateinit var itemsHeadlineError: CardView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHeadlinesBinding.bind(view)



    }
}