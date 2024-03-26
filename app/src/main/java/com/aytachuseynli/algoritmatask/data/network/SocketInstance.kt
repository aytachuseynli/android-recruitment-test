package com.aytachuseynli.algoritmatask.data.network

import com.aytachuseynli.algoritmatask.common.utils.Constant
import com.aytachuseynli.algoritmatask.data.local.model.SocketListener
import okhttp3.WebSocket
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocketListener
import okio.ByteString
import java.util.concurrent.TimeUnit

object SocketInstance {
    private lateinit var webSocket: WebSocket
    private val listeners = mutableListOf<SocketListener>()

    fun setSocket() {
        val request = Request.Builder().url(Constant.URL).build()
        val client = OkHttpClient.Builder()
            .readTimeout(0, TimeUnit.MILLISECONDS)
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                // Notify listeners about socket connection
                listeners.forEach { it.onConnect() }
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                // Notify listeners about received data
                listeners.forEach { it.onDataReceived(text) }
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                super.onMessage(webSocket, bytes)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                // Notify listeners about socket disconnection
                listeners.forEach { it.onDisconnect() }
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                // Notify listeners about socket error
                listeners.forEach { it.onError(t) }
            }
        })
    }

    fun disconnectWebSocket() {
        webSocket.cancel()
    }

    fun addListener(listener: SocketListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: SocketListener) {
        listeners.remove(listener)
    }

    fun establishConnection() {
        setSocket()
    }
}
