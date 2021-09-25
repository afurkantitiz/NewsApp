package com.afurkantitiz.newsapp.ui.newsdetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.afurkantitiz.newsapp.databinding.FragmentNewsDetailBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat

class NewsDetailFragment : Fragment() {
    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    private val args: NewsDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        onClickListener()
    }

    private fun onClickListener() {
        binding.apply {
            shareButton.setOnClickListener {
                Toast.makeText(requireContext(), "Share Button", Toast.LENGTH_SHORT).show()
            }

            likeButton.setOnClickListener {
                Toast.makeText(requireContext(), "Like Button", Toast.LENGTH_SHORT).show()
            }

            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            newsDetailSource.setOnClickListener {
                args.currentNews.let {
                    val action = NewsDetailFragmentDirections.actionNewsDetailFragmentToNewsDetailSourceFragment(args.currentNews!!.url)
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
                newsDetailAuthor.text = args.currentNews!!.author
                newsDetailCalendar.text =
                    SimpleDateFormat("dd/MM/yyyy").format(args.currentNews!!.publishedAt).toString()

                Glide
                    .with(requireContext())
                    .load(args.currentNews!!.urlToImage)
                    .into(imageView)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}