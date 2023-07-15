package com.example.uts_160420136.view

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CalendarView
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentAppointmentBinding
import com.example.uts_160420136.viewmodel.AppointmentiewModel
import java.text.SimpleDateFormat

class AppointmentFragment : Fragment() {

    private lateinit var viewModel:AppointmentiewModel
    private lateinit var dataBinding:FragmentAppointmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_appointment, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val doctorId = AppointmentFragmentArgs.fromBundle(requireArguments()).doctorId
        val userId = AppointmentFragmentArgs.fromBundle(requireArguments()).userId

        viewModel = ViewModelProvider(this).get(AppointmentiewModel::class.java)

        //val currentDate = Calendar.getInstance().timeInMillis + 86400000 //besok
        val currentDate = Calendar.getInstance().timeInMillis
        dataBinding.calendarAppointment.minDate = currentDate

        val arrs = arrayOf("08:00 AM", "10:00 AM", "12:00 PM", "14:00 PM", "16:00 PM", "18:00 PM")
        val d = Calendar.getInstance()
        val timesFormat = SimpleDateFormat("HH")
        val times = timesFormat.format(d.time)

        var arr = arrs.toMutableList()
        if (times < "12" && times >= "10"){
            arr.removeAt(0)
        } else if (times < "14" && times >= "12"){
            arr.removeAt(0)
            arr.removeAt(0)
        } else if (times < "16" && times >= "14"){
            arr.removeAt(0)
            arr.removeAt(0)
            arr.removeAt(0)
        } else if (times < "18" && times >= "16"){
            arr.removeAt(0)
            arr.removeAt(0)
            arr.removeAt(0)
            arr.removeAt(0)
        } else if (times < "20" && times >= "18"){
            arr.removeAt(0)
            arr.removeAt(0)
            arr.removeAt(0)
            arr.removeAt(0)
            arr.removeAt(0)
        }

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.spinner_layout, arr)
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item_layout)
        dataBinding.spinnerTimeAvailable.adapter = spinnerAdapter


        dataBinding.buttonBookNow.setOnClickListener {
            viewModel.addAppointment(userId, doctorId, "ada")
        }
    }
}