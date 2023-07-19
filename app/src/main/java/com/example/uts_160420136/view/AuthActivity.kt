package com.example.uts_160420136.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.ActivityAuthBinding
import com.example.uts_160420136.databinding.ActivityMainBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var dataBinding:ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityAuthBinding.inflate(layoutInflater)
        val v = dataBinding.root
        setContentView(v)

        val authFragment = supportFragmentManager.findFragmentById(R.id.authFragment) as NavHostFragment

        //menginisialisasi navhost terhadap navigation controller
        navController = (authFragment).navController
    }
}