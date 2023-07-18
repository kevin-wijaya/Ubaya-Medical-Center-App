package com.example.uts_160420136.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.uts_160420136.model.UserWithDoctorAppointment
import com.example.uts_160420136.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AppointmentiewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun addAppointment(userId:Int, doctorId:Int?, dateTimeAppointment:String?){
        launch {
            val db = buildDb(getApplication())
            db.Dao().addAppointment(userId, doctorId, dateTimeAppointment)
        }
    }
}