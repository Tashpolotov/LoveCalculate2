package com.example.lovecalculate.main

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.migration.Migration
import com.example.lovecalculate.model.room.AppDataBase

class App:Application() {

    companion object{
        lateinit var dataBase: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(applicationContext, AppDataBase::class.java, "love_file")
            .allowMainThreadQueries()
            .build()
    }
}