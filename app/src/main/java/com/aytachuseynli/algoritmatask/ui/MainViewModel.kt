package com.aytachuseynli.algoritmatask.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Update
import com.aytachuseynli.algoritmatask.data.local.model.SocketListener
import com.aytachuseynli.algoritmatask.data.local.model.SocketModel
import com.aytachuseynli.algoritmatask.data.network.SocketInstance
import com.aytachuseynli.algoritmatask.data.repository.SocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val socketRepository: SocketRepository
) : ViewModel(), SocketListener {

    private val _socketModelList = MutableStateFlow<List<SocketModel>>(emptyList())
    val socketModelList: StateFlow<List<SocketModel>> get() = _socketModelList

    private val _isOnline = MutableStateFlow(false)
    val isOnline: StateFlow<Boolean> get() = _isOnline

    fun checkInternetConnectivity(context: Context) {
        val isOnline = isOnline(context)
        _isOnline.value = isOnline

        if (isOnline) {
            // Establish WebSocket connection if online
            SocketInstance.setSocket()
            SocketInstance.establishConnection()
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    init {
        // Connect to socket when ViewModel is created
        connectToWebSocket()
    }

    private fun connectToWebSocket() {
        SocketInstance.addListener(this)
    }

    override fun onConnect() {
        // Handle socket connection event if needed
        viewModelScope.launch {
            _isOnline.emit(true)
        }


    }

    override fun onDisconnect() {
        // Handle socket disconnection event if needed
        viewModelScope.launch {
            _isOnline.emit(false)
        }
    }

    override fun onError(error: Throwable?) {
        // Handle socket error event if needed
    }

    override fun onDataReceived(data: String?) {
//         Update UI with received data
//        data?.let {
//            val socketModel = SocketModel(it)
//            _socketModelList.value += socketModel
//        }
    }

    override fun onCleared() {
        // Remove listener when ViewModel is cleared
        SocketInstance.removeListener(this)
        super.onCleared()
    }
}
