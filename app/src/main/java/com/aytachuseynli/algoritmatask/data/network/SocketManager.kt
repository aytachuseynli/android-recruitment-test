package com.aytachuseynli.algoritmatask.data.network

import com.aytachuseynli.algoritmatask.data.local.model.SocketListener
import java.net.Socket

object SocketManager {

    private const val SOCKET_URL = "https://q.investaz.az/live"

    private lateinit var socket: Socket
    private val listeners = mutableListOf<SocketListener>()


}