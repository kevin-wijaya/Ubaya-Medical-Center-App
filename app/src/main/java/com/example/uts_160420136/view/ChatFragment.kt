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
import androidx.navigation.Navigation
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentChatBinding
import com.example.uts_160420136.model.Report
import com.example.uts_160420136.model.UserPillCrossRef
import com.example.uts_160420136.viewmodel.AppointmentiewModel
import com.example.uts_160420136.viewmodel.ListPillViewModel
import com.example.uts_160420136.viewmodel.UserReportViewModel

class ChatFragment : Fragment() {

    private lateinit var viewModelAppointment:AppointmentiewModel
    private lateinit var viewModelReport:UserReportViewModel
    private lateinit var pillViewModel: ListPillViewModel
    private lateinit var dataBinding:FragmentChatBinding

    var doctorId = 0
    var userId = 0
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
        pillViewModel = ViewModelProvider(this).get(ListPillViewModel::class.java)

        dataBinding.buttonSend.setOnClickListener {
            when(dataBinding.editTextChat.text.toString()){
                "Done" -> {
                    viewModelAppointment.addAppointment(userId, null, null)
                    Toast.makeText(context, "Appointment Done!", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(view).popBackStack()
                }
                "Make Report" -> {
                    val report = Report(
                        (95..170).random(),
                        (150..450).random(),
                        arrayOf("A", "B", "AB", "O").random(),
                        (90..130).random(),
                        (60..85).random(),
                        userId
                    )
                    viewModelReport.addReport(report)
                    Toast.makeText(context, "Report Added!", Toast.LENGTH_SHORT).show()
                }
                "Make Pill" -> {
                    pillViewModel.addUserPill(userId.toLong(), (1..5).random().toLong())
                    Toast.makeText(context, "Pill Added!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}