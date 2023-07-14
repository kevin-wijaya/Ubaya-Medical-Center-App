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

    private lateinit var dataBinding:FragmentHomeBinding
    private var uid = "1"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.load(uid)

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            if(it != null){
                dataBinding.user = it
            }
            else{
                val user = User(
                    "Kevin Wijaya",
                    "kevinwijaya",
                    "kevinwijaya",
                    "kevinwijaya@gmail.com",
                    "2002-02-02",
                    "0892131381301",
                    "Perumahan Pondok Jati Sidoarjo, Jawa Timur",
                    "A",
                    "https://media.licdn.com/dms/image/D5603AQEyOxEvzr0Q7w/profile-displayphoto-shrink_800_800/0/1673446061622?e=2147483647&v=beta&t=BMU500PdtTfksFpX2eDFZldabWSIIDf2S8JAzkuiKZc"
                )
                viewModel.addUser(user)
            }
        })
    }
}