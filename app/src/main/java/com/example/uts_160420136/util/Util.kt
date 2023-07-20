package com.example.uts_160420136.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.uts_160420136.R
import com.example.uts_160420136.model.UMCDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

@BindingAdapter("imageUrl", "progressBar")
fun loadPhotoUrl(view:ImageView, url:String?, pb:ProgressBar){
    view.loadImage(url, pb)
}

fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {
    Picasso.get()
        .load(url)
        .resize(130,130)
        .centerCrop()
        .error(R.drawable.baseline_person_24)
        .into(this, object:Callback {
            override fun onSuccess() { progressBar.visibility = View.GONE }
            override fun onError(e: Exception?) { }
        })
}

val DB_NAME = "UMCdb"
fun buildDb(context: Context): UMCDatabase = Room.databaseBuilder(context, UMCDatabase::class.java, DB_NAME)
    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5).build()

//Migration 1: nambah tabel user pills
//Migration 2: nambah kolom di tabel report (userId)
//Migration 3: nambah kolom di tabel user (doctorId)
//Migration 4: nambah tabel service, article
val MIGRATION_1_2 = object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("")
    }
}

val MIGRATION_2_3 = object: Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("")
    }
}

val MIGRATION_3_4 = object: Migration(3,4){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("")
    }
}

val MIGRATION_4_5 = object: Migration(4,5){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("")
    }
}