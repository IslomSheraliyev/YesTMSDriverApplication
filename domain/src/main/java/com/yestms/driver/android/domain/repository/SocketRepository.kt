package com.yestms.driver.android.domain.repository

import com.yestms.driver.android.domain.model.socket.AddUserSocketModel
import io.socket.emitter.Emitter

interface SocketRepository {
    fun connect()
    fun disconnect()
    fun addUser(event: String, model: AddUserSocketModel)
    fun kickUser(event: String, userId: Int)
    fun changeForDashboard(event: String, dispatchers: List<Int>)
    fun sendNotice(event: String, dispatchers: List<Int>, titleNotice: String)
    fun renderDispatcherDashboard(event: String, listener: Emitter.Listener)
    fun sendIncident(
        event: String,
        dispatchers: List<Int>,
        titleNotice: String,
        description: String
    )
}
