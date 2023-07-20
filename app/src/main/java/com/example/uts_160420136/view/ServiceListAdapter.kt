package com.example.uts_160420136.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420136.databinding.PillListItemBinding
import com.example.uts_160420136.databinding.ServiceListItemBinding
import com.example.uts_160420136.model.Service

class ServiceListAdapter(val serviceList: ArrayList<Service>) : RecyclerView.Adapter<ServiceListAdapter.ServiceViewHolder>() {
    class ServiceViewHolder(val view: ServiceListItemBinding):RecyclerView.ViewHolder(view.root) //inner class

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}