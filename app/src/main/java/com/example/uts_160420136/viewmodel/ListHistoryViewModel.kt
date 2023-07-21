package com.example.uts_160420136.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.uts_160420136.model.History
import com.example.uts_160420136.model.HistoryWithDoctor
import com.example.uts_160420136.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListHistoryViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    val historyLd = MutableLiveData<List<HistoryWithDoctor>>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun selectUserHistory(userId:Int) {
        launch {
            val db = buildDb(getApplication())
            historyLd.postValue(db.Dao().selectDoctorHistory(userId))
        }
    }

    fun addUserHistory(history: History) {
        launch {
            val db = buildDb(getApplication())
            db.Dao().addUserHistory(history)
        }
    }
}