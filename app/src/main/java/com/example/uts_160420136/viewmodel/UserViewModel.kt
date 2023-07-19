package com.example.uts_160420136.viewmodel

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.uts_160420136.model.Pill
import com.example.uts_160420136.model.User
import com.example.uts_160420136.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel (application: Application): AndroidViewModel(application), CoroutineScope{
    val userLD = MutableLiveData<User>()
    val usersLD = MutableLiveData<List<User>>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun checkUser() {
        launch {
            val db = buildDb(getApplication())
            usersLD.postValue(db.Dao().selectUsers())
        }
    }

    fun load(id:Int){
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.Dao().selectUser(id))
        }
    }

    fun update(user:User){
        launch {
            val db = buildDb(getApplication())
            db.Dao().updateUser(user)
        }
    }

    fun addUser(user:User){
        launch {
            val db = buildDb(getApplication())
            db.Dao().insertUser(user)
        }
    }

    fun checkLogin(username:String, password:String){
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.Dao().CheckUserLogin(username, password))
        }
    }

    fun checkRegister(username:String) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.Dao().CheckUserRegister(username))
        }
    }
}