package com.example.uts_160420136.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentHomeBinding
import com.example.uts_160420136.model.Article
import com.example.uts_160420136.model.User
import com.example.uts_160420136.util.loadImage
import com.example.uts_160420136.viewmodel.ListArticleViewModel
import com.example.uts_160420136.viewmodel.UserViewModel
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var listArticleViewModel: ListArticleViewModel
    private var articleListAdapter = ArticleListAdapter(arrayListOf())
    private lateinit var shared:SharedPreferences
    private lateinit var dataBinding:FragmentHomeBinding
    private var uid:Int = -1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewArticles = dataBinding.recyclerViewArticles
        shared = context!!.getSharedPreferences("com.example.uts_160420136", Context.MODE_PRIVATE)
        uid = shared.getInt("UID",-1)
        if(uid == -1) {
            val intent = Intent(context, AuthActivity::class.java)
            startActivity(intent)
        }
        else {
            userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            userViewModel.load(uid)

            listArticleViewModel = ViewModelProvider(this).get(ListArticleViewModel::class.java)
            listArticleViewModel.selectArticles()

            observeViewModel()

            recyclerViewArticles.layoutManager = LinearLayoutManager(context)
            recyclerViewArticles.adapter = articleListAdapter
        }
    }

    fun observeViewModel() {
        userViewModel.userLD.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                dataBinding.user = it
            }
        })
        listArticleViewModel.articlesLd.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()){
                dataBinding.textArticleError.visibility = View.VISIBLE
                dataBinding.progressArticleError.visibility = View.VISIBLE
            }
            else{
                dataBinding.textArticleError.visibility = View.GONE
                dataBinding.progressArticleError.visibility = View.GONE
            }
            articleListAdapter.updateArticleList(it)
        })


    }
}