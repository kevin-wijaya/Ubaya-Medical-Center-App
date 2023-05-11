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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListPillViewModel (application: Application): AndroidViewModel(application) {
    val pillsLD = MutableLiveData<List<Pill>>()
    val loadingDoneLD = MutableLiveData<Boolean>()
    val loadingErrorLD = MutableLiveData<Boolean>()

    private val TAG = "listPillsTag" //identifier pada string request
    private var queue: RequestQueue?= null

    fun load() {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://kevinwijaya.000webhostapp.com/ANMP/UTS/json.php" +
                "?T0KEN=ANMPUTS160420136KEVINWIJAYA&4CCESS=PILL"
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                loadingDoneLD.value = true
                val sType = object : TypeToken<List<Pill>>() { }.type
                val result = Gson().fromJson<List<Pill>>(response, sType)
                pillsLD.value = result
                Log.d("volley", response.toString())
            },
            { error ->
                loadingDoneLD.value = true
                loadingErrorLD.value = true
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