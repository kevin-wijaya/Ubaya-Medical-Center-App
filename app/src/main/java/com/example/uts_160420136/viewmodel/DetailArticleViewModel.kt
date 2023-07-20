package com.example.uts_160420136.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.uts_160420136.model.Article
import com.example.uts_160420136.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailArticleViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    var articleLD = MutableLiveData<Article>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

        fun load(id: Int) {
        launch {
            val db = buildDb(getApplication())
            articleLD.postValue(db.Dao().selectArticle(id))
        }
    }
}