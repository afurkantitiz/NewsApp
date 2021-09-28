package com.afurkantitiz.newsapp.ui.news

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.afurkantitiz.newsapp.base.BaseFragment
import com.afurkantitiz.newsapp.data.entitiy.Article
import com.afurkantitiz.newsapp.databinding.FragmentNewsBinding
import com.afurkantitiz.newsapp.utils.Resource
import com.afurkantitiz.newsapp.utils.gone
import com.afurkantitiz.newsapp.utils.hideKeyboard
import com.afurkantitiz.newsapp.utils.show
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

        getNewsByQuery(defaultQuery, pageChanger)
        initViews()
        searchViewListener()
        onScrollListener()
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
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isEmpty()) {
                    resetSearch()
                    defaultQuery = "besiktas"
                    getNewsByQuery(defaultQuery, pageChanger)
                    binding.searchView.clearFocus()
                }
                return true
            }
        })

        binding.searchView.setOnCloseListener {
            hideKeyboard()
            false
        }
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
                    binding.newsRecyclerView.gone()
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.newsRecyclerView.show()
                    binding.progressBar.gone()

                    if (response.data?.articles?.size != 0) {
                        newsList.addAll(response.data?.articles!!)

                        val scrollDistance = newsList.size - response.data.articles.size
                        binding.newsRecyclerView.scrollToPosition(scrollDistance)
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
