package com.example.uts_160420136.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentHomeBinding
import com.example.uts_160420136.databinding.FragmentLoginBinding
import com.example.uts_160420136.viewmodel.UserViewModel

class LoginFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var shared: SharedPreferences
    private lateinit var dataBinding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shared = context!!.getSharedPreferences("com.example.uts_160420136", Context.MODE_PRIVATE)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        dataBinding.textRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }

        dataBinding.buttonLogin.setOnClickListener {
            viewModel.checkLogin(
                dataBinding.editTextUsername.text.toString(),
                dataBinding.editTextPassword.text.toString()
            )
        }
        dataBinding.buttonGoogleLogin.setOnClickListener {
            viewModel.load(1)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        var editor:SharedPreferences.Editor = shared.edit()
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                editor.putInt("UID", it.userId)
                editor.apply()
                Log.d("cek", it.userId.toString())
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(context, "user not found", Toast.LENGTH_SHORT).show()
            }
        })
    }
}