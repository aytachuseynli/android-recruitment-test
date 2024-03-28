package com.aytachuseynli.algoritmatask.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aytachuseynli.algoritmatask.common.utils.ConnectivityUtil
import com.aytachuseynli.algoritmatask.data.local.model.SocketModel
import com.aytachuseynli.algoritmatask.data.repository.SocketRepository
import convert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val socketRepository: SocketRepository
) : ViewModel() {

    private val _socketModelList = MutableStateFlow<List<SocketModel>>(emptyList())
    val socketModelList: StateFlow<List<SocketModel>> get() = _socketModelList

    private val _socketIsConnected = MutableStateFlow(false)
    val socketIsConnected: StateFlow<Boolean> get() = _socketIsConnected

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun fetchData(context: Context) {
        val isConnected = ConnectivityUtil.isOnline(context)
        if (isConnected) {
            getDataFromUrl()
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                socketRepository.getAllDataFromLocal().collect { data ->
                    _socketModelList.value = data
                    _socketIsConnected.value = false
                    _isLoading.value = false
                }
            }
        }
    }

    private fun getDataFromUrl() {

        val socket = socketRepository.getFromUrl()
        socket.on("message") { args ->
            val array = Array<Any>(args.size) { 0 }
            viewModelScope.launch(Dispatchers.IO) {
                val list = convert(args, array)
                storeInSqlite(list)
                _socketIsConnected.value = socket.connected()
            }
        }
    }

    private fun storeInSqlite(list: List<SocketModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            socketRepository.deleteAllData()
            socketRepository.saveData(list)
            _socketModelList.value = list
        }
    }
}
