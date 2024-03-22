package com.aytachuseynli.algoritmatask.ui

import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import com.aytachuseynli.algoritmatask.data.local.model.SocketEvent
import com.aytachuseynli.algoritmatask.data.local.model.SocketModel
import com.aytachuseynli.algoritmatask.data.local.model.SocketListener
import com.aytachuseynli.algoritmatask.data.repository.SocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val socketRepository: SocketRepository,
    private val connectivityManager: ConnectivityManager
) : ViewModel(), SocketListener {

    private val _socketModelList = MutableStateFlow<List<SocketModel>>(emptyList())
    val socketModelList: StateFlow<List<SocketModel>> get() = _socketModelList

    init {
        // Connect to socket when ViewModel is created
        connectSocket()
    }

    private fun connectSocket() {

    }

    private fun disconnectSocket() {

    }

    override fun onEvent(event: SocketEvent) {
        when (event) {
            is SocketEvent.Connect -> {

            }
            is SocketEvent.Disconnect -> {

            }
            is SocketEvent.Error -> {

            }
            is SocketEvent.Data -> {

            }
        }
    }

    override fun onCleared() {
        // Disconnect socket when ViewModel is cleared
        disconnectSocket()
        super.onCleared()
    }
}
