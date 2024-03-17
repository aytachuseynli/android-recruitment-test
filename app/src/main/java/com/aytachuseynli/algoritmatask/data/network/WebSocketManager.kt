package com.aytachuseynli.algoritmatask.data.network

import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.*
import javax.inject.Inject

@ActivityScoped
class WebSocketManager @Inject constructor(private val client: OkHttpClient) {

    private var webSocket: WebSocket? = null

    fun connect(url: String, listener: WebSocketListener) {
        val request = Request.Builder().url(url).build()
        webSocket = client.newWebSocket(request, listener)
    }

    fun disconnect() {
        webSocket?.close(1000, "Disconnecting")
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
    }
}
