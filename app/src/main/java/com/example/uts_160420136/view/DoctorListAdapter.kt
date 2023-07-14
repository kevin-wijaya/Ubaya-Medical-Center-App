package com.example.uts_160420136.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.DoctorListItemBinding
import com.example.uts_160420136.model.Doctor
import com.example.uts_160420136.util.ButtonDetailDoctor
import com.example.uts_160420136.util.loadImage

class DoctorListAdapter(val doctorList: ArrayList<Doctor>):RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>(), ButtonDetailDoctor
{
    class DoctorViewHolder(val view: DoctorListItemBinding):RecyclerView.ViewHolder(view.root) //inner class

    fun updateDoctorList(newDoctorList:List<Doctor>) {
        doctorList.clear()
        doctorList.addAll(newDoctorList)
        notifyDataSetChanged() //merender ulang recyclerview agar terupdate dengan live data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<DoctorListItemBinding>(inflater, R.layout.doctor_list_item, parent, false)

        return DoctorViewHolder(v)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.view.doctor = doctorList[position]
        holder.view.detaillistener = this
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }

    override fun onClick(view: View) {
        val action = SearchFragmentDirections.actionDoctorDetailFragment(view.tag.toString())
        Navigation.findNavController(view).navigate(action)
    }
}