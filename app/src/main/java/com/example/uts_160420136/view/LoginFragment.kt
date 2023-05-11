package com.example.uts_160420136.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.uts_160420136.R
import com.example.uts_160420136.viewmodel.UserViewModel

class LoginFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.textRegister).setOnClickListener {
            val action = LoginFragmentDirections.actionRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }

        view.findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            val action = LoginFragmentDirections.actionItemHome()
            Navigation.findNavController(it).navigate(action)
        }

        view.findViewById<Button>(R.id.buttonGoogleLogin).setOnClickListener {
            val action = LoginFragmentDirections.actionItemHome()
            Navigation.findNavController(it).navigate(action)
        }
    }

}