package com.example.uts_160420136.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420136.R
import com.example.uts_160420136.model.Doctor
import com.example.uts_160420136.model.Pill

class PillListAdapter(val pillList: ArrayList<Pill>): RecyclerView.Adapter<PillListAdapter.PillViewHolder>(){
    class PillViewHolder(val view: View):RecyclerView.ViewHolder(view) //inner class

    fun updatePillList(newPillList:List<Pill>) {
        pillList.clear()
        pillList.addAll(newPillList)
        notifyDataSetChanged() //merender ulang recyclerview agar terupdate dengan live data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PillViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.pill_list_item, parent, false)

        return PillViewHolder(v)
    }

    override fun onBindViewHolder(holder: PillViewHolder, position: Int) {
        with(holder.view) {
            findViewById<TextView>(R.id.textPillName).text = pillList[position].name
            findViewById<TextView>(R.id.textPillFrequency).text = pillList[position].frequency.toString()
            findViewById<TextView>(R.id.textPillTime).text = pillList[position].time
        }
    }

    override fun getItemCount(): Int {
        return pillList.size
    }

}