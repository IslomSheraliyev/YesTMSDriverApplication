package com.yestms.driver.android.ui.screens.notices

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yestms.driver.android.core.BaseViewModel
import com.yestms.driver.android.domain.model.notices.NoticeModel
import com.yestms.driver.android.domain.usecase.notices.GetNoticesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticesViewModel @Inject constructor(
    private val getNoticesUseCase: GetNoticesUseCase
) : BaseViewModel() {

    private val _notices = MutableStateFlow<PagingData<NoticeModel>>(PagingData.empty())
    val notices: StateFlow<PagingData<NoticeModel>> get() = _notices

    fun getNotices(
        search: String? = null,
        sort: String? = null,
        dateTo: String? = null,
        dateFrom: String? = null
    ) = vmScope.launch {
        _isRefreshing.emit(true)

        _notices.emit(PagingData.empty())

        getNoticesUseCase(
            GetNoticesUseCase.Params(
                search = search,
                sort = sort,
                dateTo = dateTo,
                dateFrom = dateFrom
            )
        ).onSuccess { result ->
            _isRefreshing.emit(false)
            result.cachedIn(vmScope)
                .collectLatest { data ->
                    _notices.emit(data)
                }
        }.onFailure(::errorProcess)
    }
}
