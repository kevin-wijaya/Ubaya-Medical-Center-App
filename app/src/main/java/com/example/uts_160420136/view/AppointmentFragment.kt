package com.example.uts_160420136.view

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentAppointmentBinding
import com.example.uts_160420136.util.ButtonBookNow
import com.example.uts_160420136.viewmodel.AppointmentiewModel
import java.text.SimpleDateFormat

class AppointmentFragment : Fragment(), ButtonBookNow {

    private lateinit var viewModel:AppointmentiewModel
    private lateinit var dataBinding:FragmentAppointmentBinding

    var dayofmonths = ""
    var monthss = ""
    var yearss = ""

    var doctorId = 0
    var userId = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_appointment, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doctorId = AppointmentFragmentArgs.fromBundle(requireArguments()).doctorId
        userId = AppointmentFragmentArgs.fromBundle(requireArguments()).userId

        viewModel = ViewModelProvider(this).get(AppointmentiewModel::class.java)

        //val currentDate = Calendar.getInstance().timeInMillis + 86400000 //besok
        val currentDate = Calendar.getInstance().timeInMillis
        dataBinding.calendarAppointment.minDate = currentDate

        val arrs = arrayOf("08:00 AM", "10:00 AM", "12:00 PM", "14:00 PM", "16:00 PM", "18:00 PM")
        val d = Calendar.getInstance()
        val timesFormat = SimpleDateFormat("HH")
        val times = timesFormat.format(d.time)

        val arr = arrs.toMutableList()
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

        var spinnerAdapter = ArrayAdapter(requireContext(), R.layout.spinner_layout, arr)
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item_layout)
        dataBinding.spinnerTimeAvailable.adapter = spinnerAdapter

        val sdfDate = SimpleDateFormat("dd")
        val sdfMonth = SimpleDateFormat("M")

        dayofmonths = sdfDate.format(currentDate)
        monthss = sdfMonth.format(currentDate)
        yearss = "2023"

        dataBinding.calendarAppointment.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = sdfDate.format(dataBinding.calendarAppointment.date)
            val months = sdfMonth.format(dataBinding.calendarAppointment.date)
            if(dayOfMonth.toString() != date.toString()){
                spinnerAdapter = ArrayAdapter(requireContext(), R.layout.spinner_layout, arrs)
                spinnerAdapter.setDropDownViewResource(R.layout.spinner_item_layout)
                dataBinding.spinnerTimeAvailable.adapter = spinnerAdapter
            }
            else if (dayOfMonth.toString() == date.toString() && month.toString() != months.toString()){
                spinnerAdapter = ArrayAdapter(requireContext(), R.layout.spinner_layout, arr)
                spinnerAdapter.setDropDownViewResource(R.layout.spinner_item_layout)
                dataBinding.spinnerTimeAvailable.adapter = spinnerAdapter
            }

            dayofmonths = dayOfMonth.toString()
            monthss = month.toString()
            yearss = year.toString()
        }
        dataBinding.buttonBookNow.setOnClickListener {
            val time = dataBinding.spinnerTimeAvailable.selectedItem.toString().take(2)
            viewModel.addAppointment(userId, doctorId, "$dayofmonths-$monthss-$yearss-$time")
            Toast.makeText(context, "Book Success!", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).popBackStack()
        }
    }

    override fun onClickBook(view: View) {
        //Toast.makeText(context, "Masuk kok sebenernya", Toast.LENGTH_SHORT).show()
//        val time = dataBinding.spinnerTimeAvailable.selectedItem.toString().take(2)
//        viewModel.addAppointment(userId, doctorId, "$dayofmonths-$monthss-$yearss-$time")
//        Toast.makeText(context, "Book Success!", Toast.LENGTH_SHORT).show()
//        Navigation.findNavController(view).popBackStack()
    }
}