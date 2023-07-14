package com.example.uts_160420136.model

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.uts_160420136.util.buildDb

@Database(entities = [Doctor::class, User::class, Report::class, Pill::class, UserPillCrossRef::class], version = 4)
abstract class UMCDatabase:RoomDatabase() {

    abstract fun Dao() : UMCDao

    companion object{
        @Volatile private var instance : UMCDatabase ?= null
        private val LOCK = Any()

        operator fun invoke(context : Context){
            if(instance != null) {
                synchronized(LOCK) {
                    instance ?: buildDb(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}