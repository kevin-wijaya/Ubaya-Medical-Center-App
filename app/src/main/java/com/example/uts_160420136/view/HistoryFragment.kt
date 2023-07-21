package com.example.uts_160420136.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentHistoryBinding
import com.example.uts_160420136.viewmodel.ListHistoryViewModel


class HistoryFragment : Fragment() {
    private lateinit var viewModel: ListHistoryViewModel
    private lateinit var dataBinding:FragmentHistoryBinding
    private var historyListAdapter = HistoryListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = HistoryFragmentArgs.fromBundle(requireArguments()).userId
        viewModel = ViewModelProvider(this).get(ListHistoryViewModel::class.java)
        viewModel.selectUserHistory(id)

        dataBinding.recyclerViewHistory.layoutManager = LinearLayoutManager(context)
        dataBinding.recyclerViewHistory.adapter = historyListAdapter

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.historyLd.observe(viewLifecycleOwner, Observer {
            dataBinding.textHistoryError.visibility = View.VISIBLE
            dataBinding.progressHistoryError.visibility = View.VISIBLE

           if (it.isEmpty()){
               dataBinding.textHistoryError.visibility = View.GONE
               dataBinding.progressHistoryError.visibility = View.GONE
               Toast.makeText(context, "History is empty, try to make appointment with doctors :3", Toast.LENGTH_SHORT).show()
            }
            else{
                dataBinding.textHistoryError.visibility = View.GONE
                dataBinding.progressHistoryError.visibility = View.GONE
            }
            historyListAdapter.updateHistoryList(it)
            Log.d("cek", it.toString())
        })
    }
}