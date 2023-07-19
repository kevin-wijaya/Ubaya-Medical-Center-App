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
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentHomeBinding
import com.example.uts_160420136.model.User
import com.example.uts_160420136.util.loadImage
import com.example.uts_160420136.viewmodel.UserViewModel
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
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
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.checkUser()
        observeViewModel()

        shared = context!!.getSharedPreferences("com.example.uts_160420136", Context.MODE_PRIVATE)
        uid = shared.getInt("UID",-1)
        if(uid == -1) {
            val intent = Intent(context, AuthActivity::class.java)
            startActivity(intent)
        }
        else {
            viewModel.load(uid)
        }
    }

    fun observeViewModel() {
        viewModel.usersLD.observe(viewLifecycleOwner, Observer {
            var admin = User(
                "ubaya",
                "ubaya",
                "ubaya",
                "ubaya@ubaya.ac.id",
                "1968-03-11",
                "(031) 2981005",
                "Jl. Raya Kalirungkut, Kali Rungkut, Kec. Rungkut, Surabaya, Jawa Timur 60293",
                "-",
                "https://www.ubaya.ac.id/wp-content/uploads/sites/20/2023/05/logoUbaya200.png",
                null,
                null
            )

            if(it.isEmpty()){
                viewModel.addUser(admin)
            }
        })
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                dataBinding.user = it
            }
        })
    }
}