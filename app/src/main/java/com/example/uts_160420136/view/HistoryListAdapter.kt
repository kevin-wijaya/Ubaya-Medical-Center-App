package com.example.uts_160420136.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.HistoryListItemBinding
import com.example.uts_160420136.model.HistoryWithDoctor

class HistoryListAdapter(val historyList:ArrayList<HistoryWithDoctor>) :
    RecyclerView.Adapter<HistoryListAdapter.HistoryListViewHolder>() {
    class HistoryListViewHolder(val view: HistoryListItemBinding):RecyclerView.ViewHolder(view.root)
    fun updateHistoryList(newHistoryList:List<HistoryWithDoctor>) {
        historyList.clear()
        historyList.addAll(newHistoryList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<HistoryListItemBinding>(inflater, R.layout.history_list_item, parent, false)

        return HistoryListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: HistoryListViewHolder, position: Int) {
        holder.view.doctorhistory = historyList[position]
    }
}