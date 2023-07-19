package com.example.uts_160420136.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentLoginBinding
import com.example.uts_160420136.databinding.FragmentRegisterBinding
import com.example.uts_160420136.model.User
import com.example.uts_160420136.viewmodel.UserViewModel

class RegisterFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var dataBinding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false )
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        dataBinding.textLogin.setOnClickListener{
            val action = RegisterFragmentDirections.actionLoginFragment()
            Navigation.findNavController(it).navigate(action)
        }

        dataBinding.buttonSave.setOnClickListener {
            if( dataBinding.textUsernameRegister.text.toString() != "" &&
                dataBinding.textPasswordRegister.text.toString() != "" &&
                dataBinding.textEmailRegister.text.toString() != "" &&
                dataBinding.textBodRegister.text.toString() != "" ) {
                viewModel.checkRegister(dataBinding.textUsernameRegister.text.toString())
            }
            else {
                Toast.makeText(context, "please fill all textbox", Toast.LENGTH_SHORT).show()
            }

        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            if(it == null) {
                viewModel.addUser(User(
                    dataBinding.textUsernameRegister.text.toString(),
                    dataBinding.textUsernameRegister.text.toString(),
                    dataBinding.textPasswordRegister.text.toString(),
                    dataBinding.textEmailRegister.text.toString(),
                    dataBinding.textBodRegister.text.toString(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                ))
                Toast.makeText(context, "account has been registered", Toast.LENGTH_SHORT).show()
                val action = RegisterFragmentDirections.actionLoginFragment()
                Navigation.findNavController(view!!).navigate(action)
            }
            else {
                Toast.makeText(context, "username already used", Toast.LENGTH_SHORT).show()
            }
        })
    }
}