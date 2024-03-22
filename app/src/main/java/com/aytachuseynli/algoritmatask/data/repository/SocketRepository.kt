package com.aytachuseynli.algoritmatask.data.repository

import com.aytachuseynli.algoritmatask.data.local.dao.SocketDao
import com.aytachuseynli.algoritmatask.data.local.model.SocketModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SocketRepository @Inject constructor(
    private val socketDao: SocketDao
) {
    // Function to get all SocketModel objects from the database
    fun getSocketModels(): Flow<List<SocketModel>> {
        return socketDao.getAllSocketModels()
    }

    fun disconnect(){

    }

    fun connect(){

    }

}