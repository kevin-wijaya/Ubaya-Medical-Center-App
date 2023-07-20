package com.example.uts_160420136.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentServiceBinding
import com.example.uts_160420136.viewmodel.ListServiceViewModel

class ServiceFragment : Fragment() {
    private lateinit var viewModel:ListServiceViewModel
    private var serviceListAdapter = ServiceListAdapter(arrayListOf())
    private lateinit var dataBinding:FragmentServiceBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_service, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewService = dataBinding.recyclerViewService
        viewModel = ViewModelProvider(this).get(ListServiceViewModel::class.java)
        viewModel.selectServices()

        recyclerViewService.layoutManager = LinearLayoutManager(context)
        recyclerViewService.adapter = serviceListAdapter

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.servicesLd.observe(viewLifecycleOwner, Observer {

            if (it.isEmpty()){
                dataBinding.textServiceError.visibility = View.VISIBLE
                dataBinding.progressServiceError.visibility = View.VISIBLE
            }
            else{
                dataBinding.textServiceError.visibility = View.GONE
                dataBinding.progressServiceError.visibility = View.GONE
            }
            serviceListAdapter.updateServiceList(it)
        })
    }

}