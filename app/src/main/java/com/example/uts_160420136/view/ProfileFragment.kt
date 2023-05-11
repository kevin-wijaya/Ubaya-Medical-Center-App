package com.example.uts_160420136.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uts_160420136.R
import com.example.uts_160420136.util.loadImage
import com.example.uts_160420136.viewmodel.UserViewModel

class ProfileFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    var id = "1"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.load(id)

        view.findViewById<Button>(R.id.buttonProfileReports).setOnClickListener {
            val action = ProfileFragmentDirections.actionReportFragment(id)
            Navigation.findNavController(it).navigate(action)
        }
        view.findViewById<Button>(R.id.buttonProfileSettings).setOnClickListener {
            val action = ProfileFragmentDirections.actionSettingsFragment(id)
            Navigation.findNavController(it).navigate(action)
        }
        view.findViewById<Button>(R.id.buttonProfilePills).setOnClickListener {
            val action = ProfileFragmentDirections.actionPillFragment()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel(view, id)
    }

    fun observeViewModel(view: View, id: String) {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            with(view) {
                findViewById<TextView>(R.id.textName).text = it.name
                findViewById<TextView>(R.id.textEmail).text = it.gmail
                findViewById<TextView>(R.id.textPhone).text = it.numberPhone

                findViewById<ImageView>(R.id.imageUserProfile).loadImage(it.photoUrl.toString(), findViewById(R.id.progressPofileBar))
            }
        })
    }
}