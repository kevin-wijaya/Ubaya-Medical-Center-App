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

class ListArticleViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    val articlesLd = MutableLiveData<List<Article>>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun selectArticles() {
        launch {
            val db = buildDb(getApplication())
            articlesLd.postValue(db.Dao().selectArticles())
        }
    }

    fun addArticles(articles:List<Article>){
        launch {
            val db = buildDb(getApplication())
            for (article in articles){
                db.Dao().insertArticle(article)
            }
        }
    }
}