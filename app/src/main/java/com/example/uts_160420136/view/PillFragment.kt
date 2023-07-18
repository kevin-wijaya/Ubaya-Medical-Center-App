package com.example.uts_160420136.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentPillBinding
import com.example.uts_160420136.viewmodel.ListPillViewModel

class PillFragment : Fragment() {
    private lateinit var viewModel: ListPillViewModel
    private var pillListAdapter = PillListAdapter(arrayListOf())
    private lateinit var dataBinding:FragmentPillBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pill, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewPill = view.findViewById<RecyclerView>(R.id.recyclerViewPill)
        val id = PillFragmentArgs.fromBundle(requireArguments()).userId
        viewModel = ViewModelProvider(this).get(ListPillViewModel::class.java)
        viewModel.load(id)

        recyclerViewPill.layoutManager = LinearLayoutManager(context)
        recyclerViewPill.adapter = pillListAdapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.userWithPillsLD.observe(viewLifecycleOwner, Observer {
            dataBinding.progressLoadPill.visibility = View.GONE
            if (it.isEmpty()){
                dataBinding.textPillError.visibility = View.VISIBLE
            }
            else{
                dataBinding.textPillError.visibility = View.GONE
            }
            pillListAdapter.updatePillList(it[0].pill)
        })
    }
}