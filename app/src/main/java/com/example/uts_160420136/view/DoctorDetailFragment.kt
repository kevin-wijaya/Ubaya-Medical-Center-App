package com.example.uts_160420136.view

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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_160420136.R
import com.example.uts_160420136.util.loadImage
import com.example.uts_160420136.viewmodel.DetailDoctorViewModel

class DoctorDetailFragment : Fragment() {
    private lateinit var viewModel: DetailDoctorViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = DoctorDetailFragmentArgs.fromBundle(requireArguments()).doctorId.toString()

        viewModel = ViewModelProvider(this).get(DetailDoctorViewModel::class.java)
        viewModel.load(id)

        observeViewModel(view)
    }

    fun observeViewModel(view: View) {
        viewModel.doctorLD.observe(viewLifecycleOwner, Observer {
            with(view) {
                findViewById<ImageView>(R.id.imageDoctorDetail).loadImage(it.photoUrl.toString(), findViewById(R.id.progressDetailBar))

                findViewById<TextView>(R.id.textDoctorNameDetail).text = it.name
                findViewById<TextView>(R.id.textSpecialistDetail).text = it.specialist
                findViewById<TextView>(R.id.textExperienceDetail).text = it.experience.toString() + " Years"
                findViewById<TextView>(R.id.textPatientsDetail).text = it.patients.toString()
                findViewById<TextView>(R.id.textRatingDetail).text = it.rating.toString() + "%"
                findViewById<TextView>(R.id.textBioDetail).text = it.bio
                findViewById<TextView>(R.id.textEducationsDetail).text = it.educations
                findViewById<TextView>(R.id.textAddressDetail).text = it.address
                findViewById<TextView>(R.id.textDateAppointmentDetail).text = it.appointmentDate
                findViewById<TextView>(R.id.textTimeAppointmentDetail).text = it.appointmentTime

                findViewById<Button>(R.id.buttonChat).setOnClickListener {
                    val action = DoctorDetailFragmentDirections.actionChatFragment()
                    Navigation.findNavController(it).navigate(action)
                }
                findViewById<Button>(R.id.buttonMakeAppointment).setOnClickListener {
                    val action = DoctorDetailFragmentDirections.actionAppointmentFragment()
                    Navigation.findNavController(it).navigate(action)
                }
            }
        })
    }
}