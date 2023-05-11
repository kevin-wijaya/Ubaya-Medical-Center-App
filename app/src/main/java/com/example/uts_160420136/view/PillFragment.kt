package com.example.uts_160420136.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420136.R
import com.example.uts_160420136.viewmodel.ListPillViewModel

class PillFragment : Fragment() {
    private lateinit var viewModel: ListPillViewModel
    private var pillListAdapter = PillListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewPill = view.findViewById<RecyclerView>(R.id.recyclerViewPill)

        viewModel = ViewModelProvider(this).get(ListPillViewModel::class.java)
        viewModel.load()

        recyclerViewPill.layoutManager = LinearLayoutManager(context)
        recyclerViewPill.adapter = pillListAdapter

        observeViewModel(view)
    }

    fun observeViewModel(view: View) {
        viewModel.pillsLD.observe(viewLifecycleOwner, Observer {
            pillListAdapter.updatePillList(it)
        })

        viewModel.loadingErrorLD.observe(viewLifecycleOwner, Observer{
            view.findViewById<TextView>(R.id.textPillError).visibility = if(it) View.VISIBLE else View.GONE
        })

        viewModel.loadingDoneLD.observe(viewLifecycleOwner, Observer{
            val recyclerViewPill = view.findViewById<RecyclerView>(R.id.recyclerViewPill)
            val progressLoadPill = view.findViewById<ProgressBar>(R.id.progressLoadPill)
            if(it) {
                progressLoadPill.visibility = View.GONE
                recyclerViewPill.visibility = View.VISIBLE
            }
            else {
                progressLoadPill.visibility = View.VISIBLE
                recyclerViewPill.visibility = View.GONE
            }
        })

    }
}