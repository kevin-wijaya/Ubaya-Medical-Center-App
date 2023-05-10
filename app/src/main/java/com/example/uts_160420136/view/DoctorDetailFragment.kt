package com.example.uts_160420136.view

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import androidx.navigation.Navigation
import com.example.uts_160420136.R

class DoctorDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonChat = view.findViewById<Button>(R.id.buttonChat)
        val buttonMakeAppointment = view.findViewById<Button>(R.id.buttonMakeAppointment)

        buttonChat.setOnClickListener {
            val action = DoctorDetailFragmentDirections.actionChatFragment()
            Navigation.findNavController(it).navigate(action)
        }

        buttonMakeAppointment.setOnClickListener {
            val action = DoctorDetailFragmentDirections.actionAppointmentFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}