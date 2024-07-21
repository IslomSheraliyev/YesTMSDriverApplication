package com.yestms.driver.android.data.remote.socket

import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.net.URISyntaxException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SocketManager @Inject constructor() {

    private lateinit var socket: Socket

    init {
        try {
            socket = IO.socket("wss://sockets.yes-tms.com")
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    fun connect() {
        socket.connect()
    }

    fun disconnect() {
        socket.disconnect()
    }

    fun on(event: String, listener: Emitter.Listener) {
        socket.on(event, listener)
    }

    fun emit(event: String, data: Any) {
        socket.emit(event, data)
    }

    fun emitTwo(event: String, parameter1: Any, parameter2: Any) {
        socket.emit(event, parameter1, parameter2)
    }

    fun emitThree(
        event: String,
        parameter1: Any,
        parameter2: Any,
        parameter3: Any
    ) {
        socket.emit(event, parameter1, parameter2, parameter3)
    }
}
