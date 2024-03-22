package com.aytachuseynli.algoritmatask.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aytachuseynli.algoritmatask.data.local.model.SocketModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SocketDao {

    @Query("SELECT * FROM socket_model_table")
    fun getAllSocketModels(): Flow<List<SocketModel>>

    @Insert
    suspend fun insertAll(socketModels: List<SocketModel>)
}