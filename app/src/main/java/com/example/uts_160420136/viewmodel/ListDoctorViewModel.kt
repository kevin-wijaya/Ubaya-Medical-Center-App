package com.example.uts_160420136.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uts_160420136.model.Doctor
import com.example.uts_160420136.util.buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListDoctorViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val doctorsLD = MutableLiveData<List<Doctor>>()
    val loadingDoneLD = MutableLiveData<Boolean>()
    val loadingErrorLD = MutableLiveData<Boolean>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun load() {
        loadingErrorLD.value = true
        loadingDoneLD.value = true
        launch {
            val db = buildDb(getApplication())
            doctorsLD.postValue(db.Dao().selectDoctor())
        }
    }

    fun addDoctor(doctors:List<Doctor>){
        launch {
            val db = buildDb(getApplication())
            for (doctor in doctors){
                db.Dao().insertDoctor(doctor)
            }
        }
    }
}