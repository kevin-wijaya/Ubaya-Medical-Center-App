package com.example.uts_160420136.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentChatBinding
import com.example.uts_160420136.model.Report
import com.example.uts_160420136.viewmodel.AppointmentiewModel
import com.example.uts_160420136.viewmodel.UserReportViewModel

class ChatFragment : Fragment() {

    private lateinit var viewModelAppointment:AppointmentiewModel
    private lateinit var viewModelReport:UserReportViewModel
    private lateinit var dataBinding:FragmentChatBinding

    var doctorId = ""
    var userId = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doctorId = ChatFragmentArgs.fromBundle(requireArguments()).doctorId
        userId = ChatFragmentArgs.fromBundle(requireArguments()).userId
        viewModelAppointment = ViewModelProvider(this).get(AppointmentiewModel::class.java)
        viewModelReport = ViewModelProvider(this).get(UserReportViewModel::class.java)

        dataBinding.buttonSend.setOnClickListener {
            when(dataBinding.editTextChat.text.toString()){
                "test" -> Toast.makeText(context, "Test Muncul", Toast.LENGTH_SHORT).show()
                "Halo" -> Toast.makeText(context, "Halo Juga", Toast.LENGTH_SHORT).show()
                "Done" -> {
                    viewModelAppointment.addAppointment(userId, null, null)
                }
                "Make Report" -> {
                    val report = Report(
                        (70..140).random(),
                        (70..140).random(),
                        "A",
                        (70..140).random(),
                        (70..140).random(),
                        userId.toInt()
                    )
                    viewModelReport.addReport(report)
                }
            }
        }
    }
}