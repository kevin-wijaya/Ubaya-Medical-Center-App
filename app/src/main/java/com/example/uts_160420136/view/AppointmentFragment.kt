package com.example.uts_160420136.view

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CalendarView
import android.widget.Spinner
import com.example.uts_160420136.R

class AppointmentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appointment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendarAppointment = view.findViewById<CalendarView>(R.id.calendarAppointment)
        val spinnerTimeAvailable = view.findViewById<Spinner>(R.id.spinnerTimeAvailable)

        val currentDate = Calendar.getInstance().timeInMillis
        calendarAppointment.minDate = currentDate

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.spinner_layout, resources.getStringArray(R.array.time_available))
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item_layout)
        spinnerTimeAvailable.adapter = spinnerAdapter
    }
}