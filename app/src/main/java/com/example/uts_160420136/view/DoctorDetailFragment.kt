package com.example.uts_160420136.view

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentDoctorDetailBinding
import com.example.uts_160420136.util.loadImage
import com.example.uts_160420136.viewmodel.DetailDoctorViewModel

class DoctorDetailFragment : Fragment() {
    private lateinit var viewModel: DetailDoctorViewModel
    private lateinit var dataBinding:FragmentDoctorDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_doctor_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = DoctorDetailFragmentArgs.fromBundle(requireArguments()).doctorId
        viewModel = ViewModelProvider(this).get(DetailDoctorViewModel::class.java)
        viewModel.load(id!!)

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.doctorLD.observe(viewLifecycleOwner, Observer {
            dataBinding.doctor = it
        })
    }
}