package com.yestms.driver.android.data.repository

import com.google.gson.Gson
import com.yestms.driver.android.data.remote.socket.SocketManager
import com.yestms.driver.android.domain.model.socket.AddUserSocketModel
import com.yestms.driver.android.domain.repository.SocketRepository
import org.json.JSONObject
import javax.inject.Inject

class SocketRepositoryImpl @Inject constructor(
    private val socketManager: SocketManager
) : SocketRepository {

    override fun connect() {
        socketManager.connect()
    }

    override fun disconnect() {
        socketManager.disconnect()
    }

    override fun addUser(event: String, model: AddUserSocketModel) {
        socketManager.emit(event = event, data = JSONObject(Gson().toJson(model)))
    }

    override fun kickUser(event: String, userId: Int) {
        socketManager.emit(event = event, data = userId)
    }

    override fun changeForDashboard(event: String, dispatchers: List<Int>) {
        socketManager.emit(event = event, dispatchers)
    }

    override fun sendNotice(event: String, dispatchers: List<Int>, titleNotice: String) {
        socketManager.emitAll(event = event, dispatchers, titleNotice)
    }

    override fun renderDispatcherDashboard(event: String, onFetchData: () -> Unit) {
        socketManager.on(event = event, listener = { onFetchData() })
    }


}
