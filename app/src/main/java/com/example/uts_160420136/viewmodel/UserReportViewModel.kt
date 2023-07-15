package com.example.uts_160420136.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.uts_160420136.model.Report
import com.example.uts_160420136.model.UserWithReport
import com.example.uts_160420136.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserReportViewModel (application: Application): AndroidViewModel(application), CoroutineScope{
    val UserReportLD = MutableLiveData<UserWithReport>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun load(id: String) {
        launch {
            val db = buildDb(getApplication())
            UserReportLD.postValue(db.Dao().getUserReport(id))
        }
    }

    fun addReport(report: Report){
        launch {
            val db = buildDb(getApplication())
            db.Dao().addReport(report)
        }
    }

    fun deleteReport(report: Report){
        launch {
            val db = buildDb(getApplication())
            db.Dao().deleteReport(report)
        }
    }
}