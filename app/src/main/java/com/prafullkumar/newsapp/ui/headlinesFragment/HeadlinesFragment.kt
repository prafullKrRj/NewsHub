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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.prafullkumar.newsapp.R
import com.prafullkumar.newsapp.adapters.ArticleAdapter
import com.prafullkumar.newsapp.databinding.FragmentHeadlinesBinding
import com.prafullkumar.newsapp.domain.Resource
import com.prafullkumar.newsapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HeadlinesFragment : Fragment(R.layout.fragment_headlines) {


    private val viewModel: HeadlinesViewModel by viewModels()
    lateinit var binding: FragmentHeadlinesBinding
    lateinit var headlinesAdapter: ArticleAdapter
    lateinit var retryButton:  MaterialButton
    lateinit var errorText: TextView
    lateinit var itemsHeadlineError: CardView
    val isError = false
    val isLoading = false
    val isLastPage = false
    var isScrolling = false
    var pageNumber = 1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHeadlinesBinding.bind(view)
        itemsHeadlineError = view.findViewById(R.id.itemHeadlinesError)
        val inflater = LayoutInflater.from(activity)
        val inflatedLayout: View = inflater.inflate(R.layout.item_error, null, false)
        retryButton = inflatedLayout.findViewById(R.id.retryButton)
        errorText = inflatedLayout.findViewById(R.id.errorText)

        setUpHeadlines()
        headlinesAdapter.setOnItemClickListener { article ->
            if (article.source?.name != null && !article.url.isNullOrEmpty()) {
                val bundle = Bundle().apply {
                    putSerializable("article", article)
                }
                findNavController().navigate(
                    R.id.action_headlinesFragment_to_articleFragment,
                    bundle
                )
            }
        }
        lifecycleScope.launch {
            viewModel.headlines.collect { resp ->
                when (resp) {
                    is Resource.Error -> {
                        hideProgressBar()
                        errorText.text = resp.exception.message
                        retryButton.setOnClickListener {
                            viewModel.getHeadlines()
                        }
                        showError()
                    }
                    Resource.Initial -> {

                    }
                    Resource.Loading -> {
                        showProgressBar()
                        hideError()
                    }
                    is Resource.Success -> {
                        hideProgressBar()
                        hideError()
                        headlinesAdapter.differ.submitList(resp.data?.articles)
                    }
                }
            }
        }
    }
    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }
    private fun hideError() {
        itemsHeadlineError.visibility = View.INVISIBLE
        retryButton.visibility = View.INVISIBLE
    }
    private fun showError() {
        itemsHeadlineError.visibility = View.VISIBLE
        retryButton.visibility = View.VISIBLE
    }

    /**
     *  Pagination
     * */
    private val scrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNoError = !isError
            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0

            val isTotalMoreThanVisible = totalItemCount >= Constants.QUERY_PAGE_SIZE

            val shouldPaginate = isNoError && isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible

            if (shouldPaginate) {
                viewModel.getHeadlines()
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                isScrolling = true
            }
        }
    }
    /**
     *       Set up the recycler view
     * */
    private fun setUpHeadlines() {
        headlinesAdapter = ArticleAdapter()
        binding.recyclerHeadlines.apply {
            adapter = headlinesAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@HeadlinesFragment.scrollListener)
        }
    }
}