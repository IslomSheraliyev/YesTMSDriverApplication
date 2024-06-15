package com.yestms.driver.android.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

abstract class BaseViewModel : ViewModel() {

    private val errorOtherChannel = Channel<Throwable>(Channel.BUFFERED)
    val errorOtherFlow = errorOtherChannel.receiveAsFlow()

    protected val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()


    private val handler = CoroutineExceptionHandler { _, exception ->
        errorProcess(exception)
    }

    val vmScope = viewModelScope + handler

    fun errorProcess(throwable: Throwable, f: ((t: Throwable) -> Unit)? = null) {
        vmScope.launch {
            errorOtherChannel.send(throwable)
            throwable.printStackTrace()
        }
    }
}

