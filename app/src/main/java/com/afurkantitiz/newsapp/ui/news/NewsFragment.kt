package com.afurkantitiz.newsapp.ui.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.afurkantitiz.newsapp.databinding.FragmentNewsBinding
import com.afurkantitiz.newsapp.utils.Resource
import com.afurkantitiz.newsapp.utils.gone
import com.afurkantitiz.newsapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private var newsAdapter: NewsAdapter = NewsAdapter()
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        getNewsByQuery()
    }

    private fun initViews() {
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.newsRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))
        binding.newsRecyclerView.adapter = newsAdapter
    }

    private fun getNewsByQuery() {
        viewModel.getNewsByQuery("bitcoin").observe(viewLifecycleOwner, { response->
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
                    Log.v("news","Error")
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}