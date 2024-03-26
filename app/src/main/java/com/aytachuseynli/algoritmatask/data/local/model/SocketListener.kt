package com.aytachuseynli.algoritmatask.data.local.model

interface SocketListener {
    fun onConnect()
    fun onDisconnect()
    fun onError(error: Throwable?)
    fun onDataReceived(data: String?)
}