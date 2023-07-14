package com.example.uts_160420136.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.uts_160420136.model.Pill
import com.example.uts_160420136.model.UserReport
import com.example.uts_160420136.model.UserWithPills
import com.example.uts_160420136.util.buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListPillViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    val userWithPillsLD = MutableLiveData<List<UserWithPills>>()
    val loadingDoneLD = MutableLiveData<Boolean>()
    val loadingErrorLD = MutableLiveData<Boolean>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun load(id:String) {
        launch {
            val db = buildDb(getApplication())
            userWithPillsLD.postValue(db.Dao().getUserPill(id))
        }
    }
}