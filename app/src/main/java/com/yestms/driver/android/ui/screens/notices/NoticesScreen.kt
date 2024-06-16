package com.yestms.driver.android.ui.screens.notices

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.yestms.driver.android.ui.dialogs.LoadingDialog

@Composable
fun NoticesScreen(
    navController: NavController,
    viewModel: NoticesViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getNotices()
    }

    NoticesScreenContent(
        notices = viewModel.notices.collectAsLazyPagingItems(),
        onSearchClick = { sortBy, createdDate, searchQuery ->
            viewModel.getNotices(
                sort = sortBy,
                dateFrom = createdDate,
                search = searchQuery
            )
        }
    )
}