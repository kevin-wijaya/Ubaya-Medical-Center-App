package com.example.uts_160420136.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentArticleDetailBinding
import com.example.uts_160420136.viewmodel.DetailArticleViewModel


class ArticleDetailFragment : Fragment() {
    private lateinit var viewModel: DetailArticleViewModel
    private lateinit var dataBinding:FragmentArticleDetailBinding
    var articleId = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleId = ArticleDetailFragmentArgs.fromBundle(requireArguments()).articleid
        viewModel = ViewModelProvider(this).get(DetailArticleViewModel::class.java)
        viewModel.load(articleId)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.articleLD.observe(viewLifecycleOwner, Observer {
            dataBinding.article = it
        })
    }
}