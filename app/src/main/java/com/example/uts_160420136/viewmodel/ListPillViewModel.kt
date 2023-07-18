package com.example.uts_160420136.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.uts_160420136.model.Doctor
import com.example.uts_160420136.model.Pill
import com.example.uts_160420136.model.UserWithPills
import com.example.uts_160420136.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListPillViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    val userWithPillsLD = MutableLiveData<List<UserWithPills>>()
    val pillsLd = MutableLiveData<List<Pill>>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun load(id:Int) {
        launch {
            val db = buildDb(getApplication())
            userWithPillsLD.postValue(db.Dao().getUserPill(id))
        }
    }

    fun getPills(){
        launch {
            val db = buildDb(getApplication())
            pillsLd.postValue(db.Dao().getAllPills())
        }
    }

    fun addUserPill(userId:Long, pillId:Long){
        launch {
            val db = buildDb(getApplication())
            db.Dao().addUserPill(userId, pillId)
        }
    }

    fun addPill(pills:List<Pill>){
        launch {
            val db = buildDb(getApplication())
            for (pill in pills){
                db.Dao().insertPill(pill)
            }
        }
    }
}