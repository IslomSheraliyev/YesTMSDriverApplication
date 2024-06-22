package com.yestms.driver.android.ui.screens.notifications

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.launch

@Composable
fun NotificationsScreen(
    viewModel: NotificationsViewModel = hiltViewModel()
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val localCoroutineScope = rememberCoroutineScope()


    val deletedNotificationsCount = viewModel.notificationsCount.collectAsState()
    val notifications = viewModel.notifications.collectAsLazyPagingItems()

    LaunchedEffect(key1 = Unit) {
        viewModel.getNotices()
    }

    LaunchedEffect(key1 = viewModel.notificationsCount) {
        localCoroutineScope.launch {
            snackBarHostState.showSnackbar(
                message = "Error message"
            )
        }
    }

    NotificationsScreenContent(
        notifications = notifications,

        onClickDeleteAll = {
            viewModel.deleteAllNotifications()
        },
        onSearchClick = { sortBy, dateFrom, dateTo, searchQuery ->
            viewModel.getNotices(
                sort = sortBy,
                dateFrom = dateFrom,
                dateTo = dateTo,
                search = searchQuery
            )
        }
    )

}