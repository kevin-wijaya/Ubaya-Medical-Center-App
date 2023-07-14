package com.example.uts_160420136.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uts_160420136.R
import com.example.uts_160420136.util.loadImage
import com.example.uts_160420136.viewmodel.UserReportViewModel
import com.example.uts_160420136.viewmodel.UserViewModel

class ReportFragment : Fragment() {
    private lateinit var viewModel: UserReportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = SettingsFragmentArgs.fromBundle(requireArguments()).userId

        viewModel = ViewModelProvider(this).get(UserReportViewModel::class.java)
        viewModel.load(id!!)

        observeViewModel(view)
    }

    fun observeViewModel(view: View) {
        viewModel.UserReportLD.observe(viewLifecycleOwner, Observer {
            with(view) {
                findViewById<TextView>(R.id.textHeartRate).text = it.report.heartRate.toString()
                findViewById<TextView>(R.id.textBloodType).text = it.report.bloodType.toString()
                findViewById<TextView>(R.id.textTrombosit).text = it.report.thrombocyte.toString()
                findViewById<TextView>(R.id.textSystolik).text = it.report.systolik.toString()
                findViewById<TextView>(R.id.textDiastolik).text = it.report.diastolik.toString()
            }
        })
    }
}