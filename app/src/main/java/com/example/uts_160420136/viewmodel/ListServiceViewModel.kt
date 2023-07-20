package com.example.uts_160420136.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.uts_160420136.model.Pill
import com.example.uts_160420136.model.Service
import com.example.uts_160420136.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListServiceViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    val servicesLd = MutableLiveData<List<Service>>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun selectServices() {
        launch {
            val db = buildDb(getApplication())
            servicesLd.postValue(db.Dao().selectServices())
        }
    }

    fun addServices(services:List<Service>){
        launch {
            val db = buildDb(getApplication())
            for (service in services){
                db.Dao().insertService(service)
            }
        }
    }
}