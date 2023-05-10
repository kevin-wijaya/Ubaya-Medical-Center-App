package com.example.uts_160420136.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.uts_160420136.R
import com.example.uts_160420136.viewmodel.ListDoctorViewModel

class SearchFragment : Fragment() {
    private lateinit var viewModel: ListDoctorViewModel
    private var doctorListAdapter = DoctorListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewDoctors = view.findViewById<RecyclerView>(R.id.recyclerViewDoctors)

        viewModel = ViewModelProvider(this).get(ListDoctorViewModel::class.java)
        viewModel.load()

        recyclerViewDoctors.layoutManager = LinearLayoutManager(context)
        recyclerViewDoctors.adapter = doctorListAdapter

        observeViewModel(view)
    }

    fun observeViewModel(view: View) {
        viewModel.doctorsLD.observe(viewLifecycleOwner, Observer {
            doctorListAdapter.updateDoctorList(it)
        })

        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer{
            view.findViewById<TextView>(R.id.textSearchError).visibility = if(it) View.VISIBLE else View.GONE
        })

        viewModel.loadingDoneLD.observe(viewLifecycleOwner, Observer{
            val recyclerViewDoctors = view.findViewById<RecyclerView>(R.id.recyclerViewDoctors)
            if(it) {
                view.findViewById<ProgressBar>(R.id.progressLoadDoctors).visibility = View.GONE
                recyclerViewDoctors.visibility = View.VISIBLE
            }
            else {
                view.findViewById<ProgressBar>(R.id.progressLoadDoctors).visibility = View.VISIBLE
                recyclerViewDoctors.visibility = View.GONE
            }
        })
    }
}