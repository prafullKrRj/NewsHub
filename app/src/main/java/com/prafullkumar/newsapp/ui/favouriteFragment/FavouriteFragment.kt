package com.prafullkumar.newsapp.ui.favouriteFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.prafullkumar.newsapp.R
import com.prafullkumar.newsapp.databinding.FragmentFavouriteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteFragment : Fragment(R.layout.fragment_favourite) {
    private val viewModel: FavouriteViewModel by viewModels()
    lateinit var binding: FragmentFavouriteBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavouriteBinding.bind(view)
    }

}