package com.afurkantitiz.newsapp.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.newsapp.R
import com.afurkantitiz.newsapp.data.entitiy.Article
import com.bumptech.glide.Glide
import com.afurkantitiz.newsapp.databinding.ItemNewsCardBinding

class FavouriteAdapter : RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {
    private var favouriteList: List<Article> = emptyList()
    private var listener: IUnFavouriteItem? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteAdapter.FavouriteViewHolder {
        val binding = ItemNewsCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavouriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteAdapter.FavouriteViewHolder, position: Int) {
        val favourite = favouriteList[position]

        holder.binding.apply {
            newsTitle.text = favourite.title
            newsDescription.text = favourite.description

            Glide
                .with(newsImage.context)
                .load(favourite.urlToImage)
                .placeholder(R.drawable.image_not_found)
                .error(R.drawable.image_not_found)
                .into(newsImage)

            newsCardView.setOnClickListener {
                val action =
                    FavoriteFragmentDirections.actionFavoriteFragmentToNewsDetailFragment(favourite)
                it.findNavController().navigate(action)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFavourite(favourite: List<Article>) {
        this.favouriteList = favourite
        notifyDataSetChanged()
    }

    fun unFavourite(position: Int) {
        listener?.let {
            listener?.unFavouriteItem(favouriteList[position], position)
        }
    }

    fun addListener(listener: IUnFavouriteItem) {
        this.listener = listener
    }

    fun removeListeners() {
        this.listener = null
    }

    override fun getItemCount(): Int = favouriteList.size

    inner class FavouriteViewHolder(val binding: ItemNewsCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}