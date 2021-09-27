package com.afurkantitiz.newsapp.ui.favorite

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.newsapp.R
import com.afurkantitiz.newsapp.data.entitiy.ArticleRoom
import com.afurkantitiz.newsapp.databinding.ItemFavouriteSwipeCardBinding
import com.bumptech.glide.Glide
import com.daimajia.swipe.SwipeLayout

class FavouriteAdapter: RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {
    private var favouriteList: List<ArticleRoom> = emptyList()
    private var listener: IUnFavouriteItem? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteAdapter.FavouriteViewHolder {
        val binding = ItemFavouriteSwipeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteAdapter.FavouriteViewHolder, position: Int) {
        val favourite = favouriteList[position]

        holder.binding.apply {
            newsTitle.text = favourite.title
            newsDescription.text = favourite.description

            deleteButton.setOnClickListener {
                listener?.let {
                    listener?.unFavouriteItem(favourite, position)
                    notifyItemRemoved(position)
                }
            }

            Glide
                .with(newsImage.context)
                .load(favourite.urlToImage)
                .placeholder(R.drawable.image_not_found)
                .error(R.drawable.image_not_found)
                .into(newsImage)

            swipeCard.setOnClickListener {

            }

            swipeCard.showMode = SwipeLayout.ShowMode.PullOut
            swipeCard.isRightSwipeEnabled = false
            swipeCard.addDrag(SwipeLayout.DragEdge.Left, holder.binding.linearSol)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFavourite(favourite: List<ArticleRoom>) {
        this.favouriteList = favourite
        notifyDataSetChanged()
    }

    fun addListener(listener: IUnFavouriteItem) {
        this.listener = listener
    }

    fun removeListeners() {
        this.listener = null
    }

    override fun getItemCount(): Int = favouriteList.size

    inner class FavouriteViewHolder(val binding: ItemFavouriteSwipeCardBinding) : RecyclerView.ViewHolder(binding.root)
}