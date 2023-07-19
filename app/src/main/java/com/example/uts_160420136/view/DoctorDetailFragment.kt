package com.example.uts_160420136.view

import android.content.Context
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420136.R
import com.example.uts_160420136.databinding.FragmentDoctorDetailBinding
import com.example.uts_160420136.util.ButtonChatDoctor
import com.example.uts_160420136.util.ButtonMakeAppointment
import com.example.uts_160420136.util.loadImage
import com.example.uts_160420136.viewmodel.DetailDoctorViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

class DoctorDetailFragment : Fragment(), ButtonChatDoctor, ButtonMakeAppointment {
    private lateinit var viewModel: DetailDoctorViewModel
    private lateinit var dataBinding:FragmentDoctorDetailBinding
    var doctorId = 0
    private lateinit var shared: SharedPreferences
    private var uid:Int = -1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_doctor_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shared = context!!.getSharedPreferences("com.example.uts_160420136", Context.MODE_PRIVATE)
        var editor:SharedPreferences.Editor = shared.edit()
        uid = shared.getInt("UID",-1)
        doctorId = DoctorDetailFragmentArgs.fromBundle(requireArguments()).doctorId
        viewModel = ViewModelProvider(this).get(DetailDoctorViewModel::class.java)
        viewModel.load(doctorId)
        viewModel.loadappointment(uid)

        dataBinding.chatlistener = this
        dataBinding.appointmentlistener = this

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.doctorLD.observe(viewLifecycleOwner, Observer {
            dataBinding.doctor = it
        })
        viewModel.appointmentLD.observe(viewLifecycleOwner, Observer {
            if(it.user.appointmentUser == null){
                dataBinding.buttonChat.isEnabled = false
                dataBinding.buttonMakeAppointment.isEnabled = true
            }
            else{
                if (it.user.doctorId == doctorId){
                    dataBinding.buttonChat.isEnabled = true
                    dataBinding.buttonMakeAppointment.isEnabled = false
                }
                else{
                    dataBinding.buttonChat.isEnabled = false
                    dataBinding.buttonMakeAppointment.isEnabled = false
                }
            }
        })
    }

    override fun onClickChat(view: View) {
        val action = DoctorDetailFragmentDirections.actionChatFragment(uid, view.tag.toString().toInt())
        Navigation.findNavController(view).navigate(action)
    }

    override fun onClickAppointment(view: View) {
        val action = DoctorDetailFragmentDirections.actionAppointmentFragment(uid, view.tag.toString().toInt())
        Navigation.findNavController(view).navigate(action)
    }
}