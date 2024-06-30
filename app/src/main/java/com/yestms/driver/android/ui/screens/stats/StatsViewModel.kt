package com.yestms.driver.android.ui.screens.stats

import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.domain.usecase.user.GetDriverDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val getDriverDetailsUseCase: GetDriverDetailsUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(StatsUIState())
    internal val uiState = _uiState.asStateFlow()

    fun getDetails(id: Int, period: String) = vmScope.launch {
        _isRefreshing.emit(true)
        getDriverDetailsUseCase(id, period)
            .onSuccess { result ->
                _uiState.update {
                    it.copy(
                        earnings = result.earn,
                        miles = result.allMiles
                    )
                }
                _isRefreshing.emit(false)
            }.onFailure {
                _isRefreshing.emit(false)
                errorProcess(it)
            }
    }
}

internal data class StatsUIState(
    val earnings: Int = 0,
    val miles: Int = 0,
)