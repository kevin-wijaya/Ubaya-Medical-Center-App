package com.example.uts_160420136.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.uts_160420136.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hostFragment = supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navView = findViewById<NavigationView>(R.id.navView)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        //menginisialisasi navhost terhadap navigation controller
        navController = (hostFragment).navController

        //AtionBar - NavController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        //Setup NavView agar dihandle oleh navController
        NavigationUI.setupWithNavController(navView, navController)

        //BottomNav - Agar dapat pindah fragment sesuai dengan navController
        bottomNav.setupWithNavController(navController)

        // botomNav dan drawerLayout GONE apabila tidak berada home, search, profile
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.itemHome ||
                destination.id == R.id.itemSearch ||
                destination.id == R.id.itemProfile) {
                bottomNav.visibility = View.VISIBLE
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
            else {
                bottomNav.visibility = View.GONE
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        return NavigationUI.navigateUp(navController, drawerLayout)

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}