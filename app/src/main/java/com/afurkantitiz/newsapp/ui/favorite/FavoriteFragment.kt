package com.afurkantitiz.newsapp.ui.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.afurkantitiz.newsapp.base.BaseFragment
import com.afurkantitiz.newsapp.data.entitiy.Article
import com.afurkantitiz.newsapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate),
    IUnFavouriteItem {
    private val viewModel: FavoriteViewModel by viewModels()
    private val favouriteAdapter: FavouriteAdapter = FavouriteAdapter()

    private lateinit var favoriteNewsList: ArrayList<Article>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        favoriteNewsList = viewModel.getFavoriteNews() as ArrayList<Article>
        favouriteAdapter.addListener(this)

        binding.apply {
            favouriteRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            favouriteRecyclerView.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
            favouriteAdapter.setFavourite(favoriteNewsList)
            favouriteRecyclerView.adapter = favouriteAdapter
        }

        val itemTouchHelper = ItemTouchHelper(SwipeToCard(favouriteAdapter))
        itemTouchHelper.attachToRecyclerView(binding.favouriteRecyclerView)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun unFavouriteItem(articleRoom: Article, position: Int) {
        viewModel.unFavouriteNews(articleRoom)
        favoriteNewsList.removeAt(position)
        favouriteAdapter.setFavourite(favoriteNewsList)
        favouriteAdapter.notifyItemRemoved(position)
        favouriteAdapter.notifyDataSetChanged()
    }
}