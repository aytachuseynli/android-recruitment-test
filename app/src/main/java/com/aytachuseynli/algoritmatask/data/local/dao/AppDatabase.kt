package com.aytachuseynli.algoritmatask.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aytachuseynli.algoritmatask.data.local.model.SocketModel

@Database(entities = [SocketModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun socketDao(): SocketDao
}