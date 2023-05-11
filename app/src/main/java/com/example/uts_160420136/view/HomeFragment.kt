package com.example.uts_160420136.view

import android.content.Context
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uts_160420136.R
import com.example.uts_160420136.util.loadImage
import com.example.uts_160420136.viewmodel.UserViewModel
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private var id = "1"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.load(id)

        observeViewModel(view)
    }

    fun observeViewModel(view: View) {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            with(view) {
                findViewById<ImageView>(R.id.imageUserHome).loadImage(it.photoUrl.toString(), findViewById(R.id.progressUserHomeBar))
                findViewById<TextView>(R.id.textNameUserHome).text = it.name
            }
        })
    }
}