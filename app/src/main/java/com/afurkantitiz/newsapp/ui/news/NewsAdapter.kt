package com.afurkantitiz.newsapp.ui.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.afurkantitiz.newsapp.R
import com.afurkantitiz.newsapp.data.entitiy.Article
import com.afurkantitiz.newsapp.databinding.ItemNewsCardBinding
import com.bumptech.glide.Glide

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private lateinit var newsList: List<Article>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {
        val binding =
            ItemNewsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        val news = newsList[position]

        holder.binding.apply {
            newsTitle.text = news.title
            newsDescription.text = news.description
        }

        Glide
            .with(holder.binding.newsImage.context)
            .load(news.urlToImage)
            .placeholder(R.drawable.image_not_found)
            .into(holder.binding.newsImage)

        holder.binding.newsCardView.setOnClickListener {
            val action = NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(news)
            it.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNews(news: List<Article>) {
        this.newsList = news
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = newsList.size

    inner class NewsViewHolder(val binding: ItemNewsCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}