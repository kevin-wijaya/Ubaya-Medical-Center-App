package com.example.uts_160420136.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.uts_160420136.R

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonProfileReports = view.findViewById<Button>(R.id.buttonProfileReports)
        val buttonProfileSettings = view.findViewById<Button>(R.id.buttonProfileSettings)

        buttonProfileReports.setOnClickListener {
            val action = ProfileFragmentDirections.actionReportFragment()
            Navigation.findNavController(it).navigate(action)
        }
        buttonProfileSettings.setOnClickListener {
            val action = ProfileFragmentDirections.actionSettingsFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}