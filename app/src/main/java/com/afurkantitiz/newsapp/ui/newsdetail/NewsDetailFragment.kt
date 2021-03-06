package com.afurkantitiz.newsapp.ui.newsdetail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.afurkantitiz.newsapp.R
import com.afurkantitiz.newsapp.base.BaseFragment
import com.afurkantitiz.newsapp.data.entitiy.Article
import com.afurkantitiz.newsapp.databinding.FragmentNewsDetailBinding
import com.afurkantitiz.newsapp.ui.MainActivity
import com.afurkantitiz.newsapp.utils.toast
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class NewsDetailFragment :
    BaseFragment<FragmentNewsDetailBinding>(FragmentNewsDetailBinding::inflate) {

    private val args: NewsDetailFragmentArgs by navArgs()
    private val viewModel: NewsDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        onClickListener()
    }

    private fun isInFavourite(): Boolean {
        var isInFavourite = false

        for (news in viewModel.getFavoriteNews()) {
            isInFavourite = news.title == args.currentNews?.title
            if (isInFavourite) break
        }
        return isInFavourite
    }

    private fun onClickListener() {
        binding.apply {
            shareButton.setOnClickListener {
                val shareIntent = Intent().apply {
                    args.currentNews.let {
                        this.action = Intent.ACTION_SEND
                        this.putExtra(Intent.EXTRA_TEXT, args.currentNews!!.url)
                        this.type = "text/plain"
                    }
                }
                startActivity(Intent.createChooser(shareIntent, "News Url"))
            }

            likeButton.setOnClickListener {
                args.currentNews.let {
                    if (!isInFavourite()) {
                        viewModel.addFavorite(
                            Article(
                                0,
                                args.currentNews?.author ?: "Unknown",
                                args.currentNews!!.content,
                                args.currentNews!!.description,
                                args.currentNews!!.publishedAt,
                                args.currentNews!!.title,
                                args.currentNews!!.url,
                                args.currentNews!!.urlToImage,
                            )
                        )
                        toast("Successfully added to favourites")
                        binding.likeButton.setImageResource(R.drawable.ic_add_favourite)
                    } else
                        toast("This news is already in favourites")
                }
            }

            backButton.setOnClickListener {
                findNavController().popBackStack()
                (activity as MainActivity).showNavigationBar()
            }

            newsDetailSource.setOnClickListener {
                args.currentNews.let {
                    val action =
                        NewsDetailFragmentDirections.actionNewsDetailFragmentToNewsDetailSourceFragment(
                            args.currentNews!!.url
                        )
                    findNavController().navigate(action)
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun initViews() {
        args.currentNews.let {
            binding.apply {
                newsDetailTitle.text = args.currentNews!!.title
                newsDetailDescription.text = args.currentNews!!.description
                newsDetailAuthor.text = args.currentNews?.author ?: "Unknown"
                newsDetailCalendar.text =
                    SimpleDateFormat("dd/MM/yyyy").format(args.currentNews!!.publishedAt).toString()

                if (isInFavourite()) likeButton.setImageResource(R.drawable.ic_add_favourite)
                else likeButton.setImageResource(R.drawable.ic_un_favourite)

                Glide
                    .with(requireContext())
                    .load(args.currentNews!!.urlToImage)
                    .into(imageView)
            }
        }
    }

    override fun isNavigationBarVisible(): Boolean = false
}