package com.aytachuseynli.algoritmatask.data.local.model

import org.json.JSONObject

sealed class SocketEvent {
    object Connect : SocketEvent()
    object Disconnect : SocketEvent()
    data class Error(val error: Throwable) : SocketEvent()
    data class Data(val data: JSONObject) : SocketEvent()
}

interface SocketListener {
    fun onEvent(event: SocketEvent)
}