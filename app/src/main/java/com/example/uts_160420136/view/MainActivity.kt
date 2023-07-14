package com.example.uts_160420136.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.uts_160420136.R
import com.example.uts_160420136.model.Doctor
import com.example.uts_160420136.viewmodel.ListDoctorViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var doctorViewModel:ListDoctorViewModel
    private lateinit var doctorList:List<Doctor>
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

        doctorViewModel = ViewModelProvider(this).get(ListDoctorViewModel::class.java)
        doctorList = listOf()
        setDoctor()
    }

    override fun onSupportNavigateUp(): Boolean {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        return NavigationUI.navigateUp(navController, drawerLayout)

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun setDoctor(){
        doctorViewModel.load()
        doctorViewModel.doctorsLD.observe(this, Observer {
            doctorList = it
        })
        val doctors = listOf(
            Doctor(
                "Dr. Winny Budiharto",
                "Dokter Umum",
                "Dr. Winny Budiharto merupakan dokter Umum. Ia menyelesaikan studi kedokteran di Universitas Airlangga. Sekarang, ia berpraktik di Rumah Sakit Mitra Keluarga Kenjeran, Rumah Sakit Mitra.",
                "Sarjana Kedokteran, Universitas Airlangga, 2010",
                "Surabaya, Jawa Timur, Indonesia",
                13,
                542,
                "100".toDouble(),
                "15 Mei 2023",
                "01.00 PM",
                "https://static.guesehat.com/static/directories_thumb/CRM111201000463_Winny_Budiharto.jpg"
            ),
            Doctor(
                "Dr. Winny Budiharto",
                "Dokter Umum",
                "Dr. Winny Budiharto merupakan dokter Umum. Ia menyelesaikan studi kedokteran di Universitas Airlangga. Sekarang, ia berpraktik di Rumah Sakit Mitra Keluarga Kenjeran, Rumah Sakit Mitra.",
                "Sarjana Kedokteran, Universitas Airlangga, 2010",
                "Surabaya, Jawa Timur, Indonesia",
                13,
                542,
                "100".toDouble(),
                "15 Mei 2023",
                "01.00 PM",
                "https://static.guesehat.com/static/directories_thumb/CRM111201000463_Winny_Budiharto.jpg"
            ),
            Doctor(
                "Dr. Winny Budiharto",
                "Dokter Umum",
                "Dr. Winny Budiharto merupakan dokter Umum. Ia menyelesaikan studi kedokteran di Universitas Airlangga. Sekarang, ia berpraktik di Rumah Sakit Mitra Keluarga Kenjeran, Rumah Sakit Mitra.",
                "Sarjana Kedokteran, Universitas Airlangga, 2010",
                "Surabaya, Jawa Timur, Indonesia",
                13,
                542,
                "100".toDouble(),
                "15 Mei 2023",
                "01.00 PM",
                "https://static.guesehat.com/static/directories_thumb/CRM111201000463_Winny_Budiharto.jpg"
            ),
            Doctor(
                "Dr. Winny Budiharto",
                "Dokter Umum",
                "Dr. Winny Budiharto merupakan dokter Umum. Ia menyelesaikan studi kedokteran di Universitas Airlangga. Sekarang, ia berpraktik di Rumah Sakit Mitra Keluarga Kenjeran, Rumah Sakit Mitra.",
                "Sarjana Kedokteran, Universitas Airlangga, 2010",
                "Surabaya, Jawa Timur, Indonesia",
                13,
                542,
                "100".toDouble(),
                "15 Mei 2023",
                "01.00 PM",
                "https://static.guesehat.com/static/directories_thumb/CRM111201000463_Winny_Budiharto.jpg"
            ),
            Doctor(
                "Dr. Winny Budiharto",
                "Dokter Umum",
                "Dr. Winny Budiharto merupakan dokter Umum. Ia menyelesaikan studi kedokteran di Universitas Airlangga. Sekarang, ia berpraktik di Rumah Sakit Mitra Keluarga Kenjeran, Rumah Sakit Mitra.",
                "Sarjana Kedokteran, Universitas Airlangga, 2010",
                "Surabaya, Jawa Timur, Indonesia",
                13,
                542,
                "100".toDouble(),
                "15 Mei 2023",
                "01.00 PM",
                "https://static.guesehat.com/static/directories_thumb/CRM111201000463_Winny_Budiharto.jpg"
            ),
            Doctor(
                "Dr. Winny Budiharto",
                "Dokter Umum",
                "Dr. Winny Budiharto merupakan dokter Umum. Ia menyelesaikan studi kedokteran di Universitas Airlangga. Sekarang, ia berpraktik di Rumah Sakit Mitra Keluarga Kenjeran, Rumah Sakit Mitra.",
                "Sarjana Kedokteran, Universitas Airlangga, 2010",
                "Surabaya, Jawa Timur, Indonesia",
                13,
                542,
                "100".toDouble(),
                "15 Mei 2023",
                "01.00 PM",
                "https://static.guesehat.com/static/directories_thumb/CRM111201000463_Winny_Budiharto.jpg"
            )
        )

        if(doctorList.isEmpty()){
            doctorViewModel.addDoctor(doctors)
        }
    }
}