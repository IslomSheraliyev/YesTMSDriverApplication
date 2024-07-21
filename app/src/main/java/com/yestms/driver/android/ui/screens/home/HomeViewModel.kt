package com.yestms.driver.android.ui.screens.home

import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.domain.usecase.socket.SocketChangeForDashboardUseCase
import com.yestms.driver.android.domain.usecase.socket.SocketSendNoticeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val socketChangeForDashboardUseCase: SocketChangeForDashboardUseCase,
    private val socketSendNoticeUseCase: SocketSendNoticeUseCase
) : BaseViewModel() {


}