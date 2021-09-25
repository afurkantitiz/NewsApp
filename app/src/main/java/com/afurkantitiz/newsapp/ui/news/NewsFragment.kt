package com.afurkantitiz.newsapp.ui.news

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.afurkantitiz.newsapp.base.BaseFragment
import com.afurkantitiz.newsapp.databinding.FragmentNewsBinding
import com.afurkantitiz.newsapp.utils.Resource
import com.afurkantitiz.newsapp.utils.gone
import com.afurkantitiz.newsapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>(FragmentNewsBinding::inflate) {
    private var newsAdapter: NewsAdapter = NewsAdapter()
    private val viewModel: NewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        getNewsByQuery("besiktas")
        searchViewListener()
    }

    private fun searchViewListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!!.isNotEmpty()) getNewsByQuery(newText) else getNewsByQuery("besiktas")
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
            newsRecyclerView.adapter = newsAdapter
        }
    }

    private fun getNewsByQuery(query: String) {
        viewModel.getNewsByQuery(query).observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    binding.newsRecyclerView.gone()
                    binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    binding.newsRecyclerView.show()
                    binding.progressBar.gone()

                    if (response.data?.articles?.size != 0)
                        newsAdapter.setNews(response.data?.articles!!)
                }
                Resource.Status.ERROR -> {
                    Log.v("news", "Error")
                }
            }
        })
    }
}