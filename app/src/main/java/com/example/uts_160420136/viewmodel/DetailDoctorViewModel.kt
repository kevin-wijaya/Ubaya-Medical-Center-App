package com.example.uts_160420136.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
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
import java.util.function.DoubleBinaryOperator
import kotlin.coroutines.CoroutineContext

class DetailDoctorViewModel(application: Application): AndroidViewModel(application), CoroutineScope{
    val doctorLD = MutableLiveData<Doctor>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun load(id: String) {
        launch {
            val db = buildDb(getApplication())
            doctorLD.postValue(db.Dao().selectDoctorById(id))
        }
    }

}