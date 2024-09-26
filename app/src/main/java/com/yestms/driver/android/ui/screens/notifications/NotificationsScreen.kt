package com.yestms.driver.android.ui.screens.notifications

import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotificationsScreen(
    onBackPressed: () -> Unit,
    viewModel: NotificationsViewModel = hiltViewModel()
) {
    var sortBy by remember { mutableStateOf("new") }
    var dateFrom by remember { mutableStateOf<String?>(null) }
    var dateTo by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf<String?>(null) }
    val notifications = viewModel.notifications.collectAsLazyPagingItems()
    val refreshing by viewModel.isRefreshing.collectAsState()
    val refreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = viewModel::getNotifications
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.getNotifications(sort = sortBy, dateFrom = dateFrom, dateTo = dateTo, search = searchQuery)
        viewModel.connect()
        viewModel.shouldUpdate.collect {
            viewModel.getNotifications()
        }
    }

    BackHandler(onBack = onBackPressed)

    NotificationsScreenContent(
        refreshing = refreshing,
        refreshState = refreshState,
        notifications = notifications,

        sortBy = sortBy,
        dateFrom = dateFrom,
        dateTo = dateTo,
        searchQuery = searchQuery,

        onUpdateSort = { sortBy = it ?: "new" },
        onUpdateDateFrom = { dateFrom = it },
        onUpdateDateTo = { dateTo = it },
        onUpdateSearchQuery = { searchQuery = it },

        onClickDeleteAll = {
            viewModel.deleteNotifications()
            viewModel.getNotifications(sort = sortBy, dateFrom = dateFrom, dateTo = dateTo, search = searchQuery)
        },
        onClickDelete = { id ->
            viewModel.deleteNotification(id)
            viewModel.getNotifications(sort = sortBy, dateFrom = dateFrom, dateTo = dateTo, search = searchQuery)

        },
        onClickView = { id ->
            viewModel.viewNotification(id)
            viewModel.getNotifications(sort = sortBy, dateFrom = dateFrom, dateTo = dateTo, search = searchQuery)
        },
        onSearchClick = { sortByParam, dateFromParam, dateToParam, searchQueryParam ->

            sortBy = sortByParam ?: "new"
            dateFrom = dateFromParam.orEmpty()
            dateTo = dateToParam.orEmpty()
            searchQuery = searchQueryParam.orEmpty()

            viewModel.getNotifications(sort = sortBy, dateFrom = dateFrom, dateTo = dateTo, search = searchQuery)
        }
    )
}