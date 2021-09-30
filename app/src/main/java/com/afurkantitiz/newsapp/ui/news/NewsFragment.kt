package com.afurkantitiz.newsapp.ui.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.newsapp.base.BaseFragment
import com.afurkantitiz.newsapp.data.entitiy.Article
import com.afurkantitiz.newsapp.databinding.FragmentNewsBinding
import com.afurkantitiz.newsapp.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>(FragmentNewsBinding::inflate) {
    private var newsAdapter: NewsAdapter = NewsAdapter()
    private val viewModel: NewsViewModel by viewModels()
    private val newsList: ArrayList<Article> = arrayListOf()

    private var pageChanger = 1
    private var defaultQuery = "besiktas"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationTextControl()
        initViews()
        searchViewListener()
        onScrollListener()
    }

    private fun notificationTextControl() {
        if (requireActivity().intent.extras != null) {
            for (key in requireActivity().intent.extras!!.keySet()) {
                if (key == "title") {
                    defaultQuery = requireActivity().intent.extras!!.getString("title", "")
                    break
                } else defaultQuery = "Besiktas"
            }

            resetSearch()
            binding.searchView.setQuery(defaultQuery, false)
            getNewsByQuery(defaultQuery, pageChanger)
        } else {
            resetSearch()
            defaultQuery = "Besiktas"
            binding.searchView.setQuery(defaultQuery, false)
            getNewsByQuery(defaultQuery, pageChanger)
        }
    }

    private fun onScrollListener() {
        binding.newsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.newsRecyclerView.canScrollVertically(1) &&
                    newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    pageChanger++
                    getNewsByQuery(defaultQuery, pageChanger)
                }
            }
        })
    }

    private fun searchViewListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                resetSearch()
                getNewsByQuery(query!!, pageChanger)
                defaultQuery = query
                hideKeyboard()
                binding.newsRecyclerView.show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isEmpty()) {
                    resetSearch()
                    defaultQuery = "besiktas"
                    getNewsByQuery(defaultQuery, pageChanger)
                    binding.searchView.clearFocus()
                    binding.newsRecyclerView.gone()
                }
                return true
            }
        })
    }

    private fun initViews() {
        binding.apply {
            newsRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            newsRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
            newsAdapter.setNews(newsList)
            newsRecyclerView.adapter = newsAdapter
        }
    }

    private fun getNewsByQuery(query: String, page: Int) {
        viewModel.getNewsByQuery(query, page).observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.gone()

                    if (response.data?.articles?.size != 0) {
                        newsList.addAll(response.data?.articles!!)

                        val scrollDistance = newsList.size - response.data.articles.size
                        binding.newsRecyclerView.scrollToPosition(scrollDistance)

                        newsAdapter.setNews(newsList)
                        binding.newsRecyclerView.adapter = newsAdapter
                    }
                }
                Resource.Status.ERROR -> {
                    Log.v("news", "Error")
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun resetSearch() {
        pageChanger = 1
        newsList.clear()
        newsAdapter.notifyDataSetChanged()
    }
}
