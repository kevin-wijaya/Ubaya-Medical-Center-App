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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentProfileBinding
import com.example.uts_160420136.util.ButtonEditUser
import com.example.uts_160420136.util.loadImage
import com.example.uts_160420136.viewmodel.UserViewModel

class ProfileFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var shared: SharedPreferences
    private lateinit var dataBinding : FragmentProfileBinding
    private var uid:Int = -1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shared = context!!.getSharedPreferences("com.example.uts_160420136", Context.MODE_PRIVATE)
        var editor:SharedPreferences.Editor = shared.edit()
        uid = shared.getInt("UID",-1)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.load(uid)

        dataBinding.buttonProfileReports.setOnClickListener {
            val action = ProfileFragmentDirections.actionReportFragment(uid)
            Navigation.findNavController(it).navigate(action)
        }
        dataBinding.buttonProfileSettings.setOnClickListener {
            val action = ProfileFragmentDirections.actionSettingsFragment(uid)
            Navigation.findNavController(it).navigate(action)
        }
        dataBinding.buttonProfilePills.setOnClickListener {
            val action = ProfileFragmentDirections.actionPillFragment(uid)
            Navigation.findNavController(it).navigate(action)
        }

        dataBinding.buttonLogOut.setOnClickListener {
            editor.clear()
            editor.apply()
            val intent = Intent(context, AuthActivity::class.java)
            startActivity(intent)
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
        })
    }
}