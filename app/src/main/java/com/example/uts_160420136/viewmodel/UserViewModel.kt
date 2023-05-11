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
import com.example.uts_160420136.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserViewModel (application: Application): AndroidViewModel(application){
    val userLD = MutableLiveData<User>()

    val TAG = "UserTag"
    private var queue: RequestQueue? = null

    fun load(id: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://kevinwijaya.000webhostapp.com/ANMP/UTS/json.php" +
                "?T0KEN=ANMPUTS160420136KEVINWIJAYA&4CCESS=USER&user_id=$id"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val sType = object : TypeToken<User>() { }.type
                val result = Gson().fromJson<User>(response, sType)
                userLD.value = result

                Log.d("volley", response.toString())
            },
            { error ->
                Log.d("volley", error.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}