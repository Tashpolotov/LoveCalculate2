package com.example.lovecalculate.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lovecalculate.model.LoveModel

@Database(version = 1, entities = [LoveModel::class], )
abstract class AppDataBase:RoomDatabase() {

    abstract fun getLoveDao():LoveDao

}