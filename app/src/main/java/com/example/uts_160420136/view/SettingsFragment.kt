package com.example.uts_160420136.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentSettingsBinding
import com.example.uts_160420136.model.User
import com.example.uts_160420136.util.ButtonEditUser
import com.example.uts_160420136.util.loadImage
import com.example.uts_160420136.viewmodel.UserViewModel

class SettingsFragment : Fragment(), ButtonEditUser {
    private lateinit var viewModel: UserViewModel
    private lateinit var dataBinding:FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = SettingsFragmentArgs.fromBundle(requireArguments()).userId

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.load(id!!)

        dataBinding.savelistener = this

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it
        })
    }

    override fun onSaveClick(view: View, user: User) {
        viewModel.update(user)
        Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).popBackStack()
    }
}