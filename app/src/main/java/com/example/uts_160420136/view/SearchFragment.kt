package com.example.uts_160420136.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentSearchBinding
import com.example.uts_160420136.viewmodel.ListDoctorViewModel

class SearchFragment : Fragment() {
    private lateinit var viewModel: ListDoctorViewModel
    private var doctorListAdapter = DoctorListAdapter(arrayListOf())
    private lateinit var dataBinding:FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListDoctorViewModel::class.java)
        viewModel.load()

        dataBinding.recyclerViewDoctors.layoutManager = LinearLayoutManager(context)
        dataBinding.recyclerViewDoctors.adapter = doctorListAdapter

        dataBinding.refreshLayout.setOnRefreshListener {
            dataBinding.recyclerViewDoctors.visibility = View.GONE
            dataBinding.textSearchError.visibility = View.GONE
            dataBinding.progressLoadDoctors.visibility = View.VISIBLE
            viewModel.load()
            dataBinding.refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.doctorsLD.observe(viewLifecycleOwner, Observer {
            dataBinding.progressLoadDoctors.visibility = View.GONE
            dataBinding.recyclerViewDoctors.visibility = View.VISIBLE
            if(it != null){
                dataBinding.textSearchError.visibility = View.GONE
                doctorListAdapter.updateDoctorList(it)
            }
            else{
                dataBinding.textSearchError.visibility = View.VISIBLE
                dataBinding.recyclerViewDoctors.visibility = View.GONE
            }
        })
    }

}
