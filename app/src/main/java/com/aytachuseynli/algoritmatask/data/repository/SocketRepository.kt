package com.aytachuseynli.algoritmatask.data.repository

import com.aytachuseynli.algoritmatask.data.local.dao.SocketDao
import com.aytachuseynli.algoritmatask.data.local.model.SocketModel
import com.aytachuseynli.algoritmatask.data.network.SocketInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SocketRepository @Inject constructor(
    private val socketDao: SocketDao
) {

    // Save data to local and cache
    suspend fun saveData(socketModel: SocketModel) {
        withContext(Dispatchers.IO) {
            socketDao.insert(socketModel)
        }
    }

    // Delete all data from local and cache
    suspend fun deleteAllData() {
        withContext(Dispatchers.IO) {
            socketDao.deleteAll()
        }
    }

    // Get all data from local
    fun getAllDataFromLocal(): Flow<List<SocketModel>> {
        return socketDao.getAllSocketData()
    }

    // Get data from URL using SocketInstance
    fun getFromUrl(): SocketInstance {
        SocketInstance.setSocket()
        SocketInstance.establishConnection()
        return SocketInstance
    }
}