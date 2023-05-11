package com.example.uts_160420136.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420136.R
import com.example.uts_160420136.model.Doctor
import com.example.uts_160420136.util.loadImage

class DoctorListAdapter(val doctorList: ArrayList<Doctor>):RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>()
{
    class DoctorViewHolder(val view: View):RecyclerView.ViewHolder(view) //inner class

    fun updateDoctorList(newDoctorList:List<Doctor>) {
        doctorList.clear()
        doctorList.addAll(newDoctorList)
        notifyDataSetChanged() //merender ulang recyclerview agar terupdate dengan live data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.doctor_list_item, parent, false)

        return DoctorViewHolder(v)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        with(holder.view) {
            findViewById<ImageView>(R.id.imageDoctorItem).loadImage(doctorList[position].photoUrl.toString(), findViewById(R.id.progressItemBar))

            findViewById<TextView>(R.id.textDoctorNameItem).text = doctorList[position].name
            findViewById<TextView>(R.id.textSpecialistItem).text = doctorList[position].specialist
            findViewById<TextView>(R.id.textRate).text = doctorList[position].rating.toString()
            findViewById<TextView>(R.id.textExperienceItem).text = doctorList[position].experience.toString()
            findViewById<TextView>(R.id.textPatientsItem).text = doctorList[position].patients.toString()

            findViewById<CardView>(R.id.cardDoctorItem).setOnClickListener{
                val action = SearchFragmentDirections.actionDoctorDetailFragment(doctorList[position].id.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }
}