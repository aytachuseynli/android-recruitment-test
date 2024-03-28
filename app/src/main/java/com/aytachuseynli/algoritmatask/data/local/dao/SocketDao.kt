package com.aytachuseynli.algoritmatask.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aytachuseynli.algoritmatask.data.local.model.SocketModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SocketDao {
    @Insert
    suspend fun insert(socketModel: SocketModel)

    @Query("DELETE FROM socket_model_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM socket_model_table")
    fun getAllSocketData(): Flow<List<SocketModel>>
}