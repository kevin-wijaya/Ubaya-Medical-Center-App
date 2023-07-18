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
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentReportBinding
import com.example.uts_160420136.util.loadImage
import com.example.uts_160420136.viewmodel.UserReportViewModel
import com.example.uts_160420136.viewmodel.UserViewModel

class ReportFragment : Fragment() {
    private lateinit var viewModel: UserReportViewModel
    private lateinit var dataBinding:FragmentReportBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_report, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = SettingsFragmentArgs.fromBundle(requireArguments()).userId

        viewModel = ViewModelProvider(this).get(UserReportViewModel::class.java)
        viewModel.load(id!!)

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.UserReportLD.observe(viewLifecycleOwner, Observer {
            if (it.report != null){
                dataBinding.report = it.report
            } else {
                Toast.makeText(context, "Report doesn't exist!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}