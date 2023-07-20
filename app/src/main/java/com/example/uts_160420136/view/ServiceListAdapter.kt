package com.example.uts_160420136.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.ServiceListItemBinding
import com.example.uts_160420136.model.Service

class ServiceListAdapter(val serviceList: ArrayList<Service>) : RecyclerView.Adapter<ServiceListAdapter.ServiceViewHolder>() {
    class ServiceViewHolder(val view: ServiceListItemBinding):RecyclerView.ViewHolder(view.root) //inner class

    fun updateServiceList(newServiceList:List<Service>) {
        serviceList.clear()
        serviceList.addAll(newServiceList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ServiceListItemBinding>(inflater, R.layout.service_list_item, parent, false)

        return ServiceViewHolder(v)
    }

    override fun getItemCount(): Int {
        return serviceList.size
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.view.service = serviceList[position]
    }
}