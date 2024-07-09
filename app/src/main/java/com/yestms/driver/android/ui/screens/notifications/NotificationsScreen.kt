package com.yestms.driver.android.ui.screens.notifications

import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotificationsScreen(
    onBackPressed: () -> Unit,
    viewModel: NotificationsViewModel = hiltViewModel()
) {
    var sortBy by rememberSaveable { mutableStateOf<String?>(null) }
    var dateFrom by rememberSaveable { mutableStateOf<String?>(null) }
    var dateTo by rememberSaveable { mutableStateOf<String?>(null) }
    var searchQuery by rememberSaveable { mutableStateOf<String?>(null) }
    val notifications = viewModel.notifications.collectAsLazyPagingItems()
    val refreshing by viewModel.isRefreshing.collectAsState()
    val refreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = {
            viewModel.getNotifications()
        }
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.getNotifications(sortBy, dateFrom, dateTo, searchQuery)
    }

    BackHandler(
        onBack = onBackPressed
    )

    NotificationsScreenContent(
        refreshing = refreshing,
        refreshState = refreshState,
        notifications = notifications,
        onClickDeleteAll = {
            viewModel.deleteNotifications()
            viewModel.getNotifications(sortBy, dateFrom, dateTo, searchQuery)
        },
        onClickDelete = { id ->
            viewModel.deleteNotification(id)
            viewModel.getNotifications(sortBy, dateFrom, dateTo, searchQuery)
        },
        onClickView = { id ->
            viewModel.viewNotification(id)
            viewModel.getNotifications(sortBy, dateFrom, dateTo, searchQuery)
        },
        onSearchClick = { sortByParam, dateFromParam, dateToParam, searchQueryParam ->

            sortBy = sortByParam.orEmpty()
            dateFrom = dateFromParam.orEmpty()
            dateTo = dateToParam.orEmpty()
            searchQuery = searchQueryParam.orEmpty()

            viewModel.getNotifications(sortBy, dateFrom, dateTo, searchQuery)
        }
    )
}